package cqrssandboxapp.articlecommandservice;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import cqrssandboxapp.articlecommandgeneric.events.ArticleCreatedEvent;
import cqrssandboxapp.articlecommandgeneric.events.ArticleDescriptionChangedEvent;
import cqrssandboxapp.generic.framework.AbstractEvent;
import cqrssandboxapp.generic.framework.AbstractRepository;
import cqrssandboxapp.generic.framework.Utils;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class ArticleRepository extends AbstractRepository {

    private static ArticleRepository instance;

    private MongoCollection<Document> collection;

    private ArticleRepository(){

        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("articlecommandservice");
        collection = database.getCollection("articles");
    }

    public static ArticleRepository getInstance() {

        if(instance == null){

            instance = new ArticleRepository();
        }

        return instance;
    }

    public Article getArticle(int number){

        Document doc = collection.find(eq("articleNumber", number)).first();
        Article article = null;

        if(doc != null){

            String json = doc.toJson();
            article = Utils.parseJsonToObject(json, Article.class);
        }else{

            article = new Article();
        }

        article.setListener(this);
        return article;
    }

    @Override
    public void handleEvent(AbstractEvent event) {

        if(event instanceof ArticleCreatedEvent){

            ArticleCreatedEvent createdEvent = (ArticleCreatedEvent) event;
            saveNewArticle(createdEvent);
        }else if(event instanceof ArticleDescriptionChangedEvent){

            ArticleDescriptionChangedEvent descriptionChangedEvent = (ArticleDescriptionChangedEvent) event;
            updateArticleDescription(descriptionChangedEvent);
        }
    }

    private void saveNewArticle(ArticleCreatedEvent event){

        Article article = new Article(event.getArticleNumber(), event.getDescription(), event.isHistorical());

        Document doc = Document.parse(Utils.parseObjectToJson(event));
        collection.insertOne(doc);
    }

    private void updateArticleDescription(ArticleDescriptionChangedEvent event){

        collection.updateOne(eq("number", event.getArticleNumber()), set("description", event.getNewDescription()));
    }
}