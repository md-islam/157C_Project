import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.List;

public class Console {
    public static void main(String[] args) {
        connectToMongo();
//        executeOneFunction();
//        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
//        MongoDatabase database = mongoClient.getDatabase("spotify");
//        MongoCollection<Document> collection = database.getCollection("US");
//        executeQuestionThree(collection);
    }

    private static void executeQuestionThree(MongoCollection<Document> collection) {
//        insert(collection);
//        update(collection);
        delete(collection);
    }

    private static void delete(MongoCollection<Document> collection) {
        System.out.println("Finding by ARTIST to make sure it actually exists : Example_ARTIST \n");
        FindIterable<Document> documents = collection.find(eq("artist", "Example_ARTIST"));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
        collection.deleteOne(eq("artist", "Example_ARTIST"));
        System.out.println("Find by ARTIST to make sure it is actually deleted : Example_ARTIST, if no results below, " +
                "then the data is deleted \n ");
        documents = collection.find(eq("artist", "Example_ARTIST"));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
    }

    private static void insert(MongoCollection<Document> collection) {
        Document document = new Document();
        document.append("title", "Example_TITLE");
        document.append("artist", "Example_ARTIST");
        document.append("tempo", 45);
        collection.insertOne(document);
        System.out.println(document);
        System.out.println("Finding this document to verify it's actually inserted");

        FindIterable<Document> documents = collection.find(eq("artist", "Example_ARTIST"));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
    }

    private static void update(MongoCollection<Document> collection) {
        collection.updateOne(eq("title", "Example_TITLE"), set("title", "NEW_UPDATED_FRESH_TITLE"));
        System.out.println("Finding this document to verify it's actually updated");

        FindIterable<Document> documents = collection.find(eq("artist", "Example_ARTIST"));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
    }


    private static void executeOneFunction() {
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoDatabase database = mongoClient.getDatabase("spotify");
        MongoCollection<Document> collection = database.getCollection("US");

//        db.US.find().sort({duration_ms:-1}).pretty().limit(1)

        FindIterable<Document> documents = collection.find().sort(Sorts.descending("duration_ms")).limit(1);
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
    }

    private static void connectToMongo() {
        MongoClient mongoClient = MongoClients.create("mongodb://3.17.167.23:27001");

        MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();
        for (String databaseName : databaseNames) {
            System.out.println(databaseName);
        }
    }
    
    
    private static void executeQueryOne(String countryCode, int danceability){
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoDatabase database = mongoClient.getDatabase("project");
        MongoCollection<Document> collection = database.getCollection("spotify");
        //Find danceability of a track greater than a specific value within a specific country
      
        FindIterable<Document> documents = collection.find(
        		new Document("danceability", new Document("$gte", danceability)
        		.append("country", countryCode)));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
    }
    

    private static void executeQueryTwo(String countryCode){
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoDatabase database = mongoClient.getDatabase("project");
        MongoCollection<Document> collection = database.getCollection("spotify");
     
        
        //Which artist is the most least popular in a specific country?
      
        FindIterable<Document> documents = collection.find(eq("country", countryCode))
        		.sort(Sorts.descending("popularity")).limit(1);
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
    }
    
    //test this might not work
    private static void executeQueryThree(String artist){
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoDatabase database = mongoClient.getDatabase("project");
        MongoCollection<Document> collection = database.getCollection("spotify");
     
       // For a given artist, which song is the most popular?

        FindIterable<Document> documents = collection.find
        		(new Document("artists",new Document("$in", artist)))
        		.sort(Sorts.descending("popularity")).limit(1);
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
    }
    private static void executeQueryFour(String artist){
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoDatabase database = mongoClient.getDatabase("project");
        MongoCollection<Document> collection = database.getCollection("spotify");
     
       // Which artist released most songs?

        FindIterable<Document> documents = collection.aggregate(
        	      Arrays.asList(
        	              Aggregates.match(Filters.eq("categories", "Bakery")),
        	              Aggregates.group("$stars", Accumulators.sum("count", 1))
        	      )
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
    }
    
    
    
    
}
