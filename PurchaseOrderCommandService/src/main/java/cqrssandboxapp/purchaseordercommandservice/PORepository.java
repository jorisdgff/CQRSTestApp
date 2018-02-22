package cqrssandboxapp.purchaseordercommandservice;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import cqrssandboxapp.articlecommandgeneric.events.ArticleInvalidOrderedEvent;
import cqrssandboxapp.events.AbstractPOEvent;
import cqrssandboxapp.events.POAmountIncreasedEvent;
import cqrssandboxapp.events.POCreatedEvent;
import cqrssandboxapp.generic.framework.AbstractEvent;
import cqrssandboxapp.generic.framework.AbstractRepository;
import cqrssandboxapp.generic.framework.Utils;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class PORepository extends AbstractRepository {

    private static PORepository instance;
    private MongoDatabase database;

    private PORepository(){

        MongoClient mongoClient = new MongoClient();
        database = mongoClient.getDatabase("purchaseordercommandservice");
    }

    public static PORepository getInstance() {

        if(instance == null){

            instance = new PORepository();
        }

        return instance;
    }

    public PurchaseOrder getPurchaseOrder(int number){

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setListener(this);

        MongoCollection<Document> collection = database.getCollection("po_" + number + "");

        for (Document doc : collection.find()) {

            String eventType = doc.getString("type");
            String contentAsJson = ((Document) doc.get("content")).toJson();

            if(eventType.equals("CREATED")){

                purchaseOrder.projectEvent(Utils.parseJsonToObject(contentAsJson, POCreatedEvent.class));
            }else if(eventType.equals("AMOUNTCHANGED")){

                purchaseOrder.projectEvent(Utils.parseJsonToObject(contentAsJson, POAmountIncreasedEvent.class));
            }

        }

        return purchaseOrder;

        /*Document doc = collection.find(eq("number", number)).first();
        PurchaseOrder purchaseOrder = null;

        if(doc != null){

            String json = doc.toJson();
            purchaseOrder = Utils.parseJsonToObject(json, PurchaseOrder.class);
        }else{

            purchaseOrder = new PurchaseOrder();
        }

        purchaseOrder.setListener(this);
        return purchaseOrder;*/
    }

    @Override
    public void handleEvent(AbstractEvent abstractEvent) {

        if(!(abstractEvent instanceof ArticleInvalidOrderedEvent)){

            AbstractPOEvent event = (AbstractPOEvent) abstractEvent;
            int purchaseOrderNumber = event.getPurchaseOrderNumber();

            MongoCollection<Document> collection = database.getCollection("po_" + purchaseOrderNumber);

            Document document = new Document("guid", event.getGuid())
                    .append("type", event.getEventType())
                    .append("content", Document.parse(Utils.parseObjectToJson(event)));

            collection.insertOne(document);
        }else{

            ArticleInvalidOrderedEvent e = (ArticleInvalidOrderedEvent) abstractEvent;
            MongoCollection<Document> collection = database.getCollection("po_" + e.getPurchaseOrderNumber());


            collection.deleteOne(eq("guid", e.getOriginalEventGuid()));
        }
    }
}