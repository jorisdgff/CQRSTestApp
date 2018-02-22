package cqrssandboxapp.purchaseorderqueryservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import cqrssandboxapp.generic.domain.Article;
import cqrssandboxapp.generic.domain.PurchaseOrder;
import cqrssandboxapp.generic.framework.Utils;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class PODBManager {

    private static PODBManager instance;
    private ObjectMapper mapper;
    private MongoCollection<Document> collection;

    private PODBManager(){

        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("poqueryservice");
        collection = database.getCollection("purchaseorders");
    }

    public PurchaseOrder find(int purchaseOrderNumber){

        Document doc = collection.find(eq("purchaseOrderNumber", purchaseOrderNumber)).first();
        PurchaseOrder po = Utils.parseJsonToObject(doc.toJson(), PurchaseOrder.class);
        return po;
    }

    public void registerPO(PurchaseOrder purchaseOrder){

        Document doc = Document.parse(Utils.parseObjectToJson(purchaseOrder));
        collection.insertOne(doc);
    }

    public void setPOAmount(int poNumber, int amount){

        collection.updateOne(eq("purchaseOrderNumber", poNumber), set("amountOrdered", amount));
    }

    public void addArticleToPO(int purchaseOrderNumber, Article article){

        Document doc = Document.parse(Utils.parseObjectToJson(article));
        collection.updateOne(eq("purchaseOrderNumber", purchaseOrderNumber), set("article", doc));
    }

    public static PODBManager getInstance() {

        if(instance == null){

            instance = new PODBManager();
        }

        return instance;
    }
}
