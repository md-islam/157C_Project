import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class Console {
    public static void main(String[] args) {
//        connectToMongo();
//        executeOneFunction();
//        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
//        MongoDatabase database = mongoClient.getDatabase("spotify");
//        MongoCollection<Document> collection = database.getCollection("US");
//        executeQuestionThree(collection);
        runProgram();
    }

    private static void runProgram() {
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoDatabase database = mongoClient.getDatabase("spotify");
        MongoCollection<Document> collection = database.getCollection("spotify");
        Scanner in = new Scanner(System.in);
        displayChoices();
        boolean keepAsking = true;
        while (keepAsking) {
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    executeChoice1(collection);
                    break;
                case 2:
                    executeChoice2(collection);
                    break;
                case 3:
                    executeChoice3(collection);
                    break;
                case 4:
                    executeChoice4(collection);
                    break;
                case 5:
                    executeChoice5(collection);
                    break;
                case 6:
                    executeChoice6(collection);
                    break;
                case 7:
                    executeChoice7(collection);
                    break;
                case 8:
                    executeChoice8(collection);
                    break;
                case 9:
                    executeChoice9(collection);
                    break;
                case 10:
                    executeChoice10(collection);
                    break;
                case 11:
                    executeChoice11(collection);
                    break;
                case 12:
                    executeChoice12(collection);
                    break;
                case 13:
                    executeChoice13(collection);
                    break;
                case 14:
                    executeChoice14(collection);
                    break;
                case 15:
                    executeChoice15(collection);
                    break;
                default:
                    System.out.println("Invalid choice !");
                    break;
            }
            System.out.println("Want to execute another query ? (Y/N)");
            String yesNoInput = in.next();
            keepAsking = yesNoInput.equalsIgnoreCase("Y") ||
                    yesNoInput.equalsIgnoreCase("yes");
            if (keepAsking) {
                displayChoices();
            }
        }
    }

    private static void displayChoices() {
        System.out.printf("Operation %d: %s", 1, "Find danceability of a song track greater than a specific value within a specific country\n");
        System.out.printf("Operation %d: %s", 2, "Which artist is the most/least popular?\n");
        System.out.printf("Operation %d: %s", 3, "For a given artist, which song is the most popular?\n");
        System.out.printf("Operation %d: %s", 4, "Which artist released most songs?\n");
        System.out.printf("Operation %d: %s", 5, "How many songs are listed in Spotify?\n");
        System.out.printf("Operation %d: %s", 6, "Which song has the longest duration?\n");
        System.out.printf("Operation %d: %s", 7, "Which song is the least popular for Artist Ignacio Corsini\n");
        System.out.printf("Operation %d: %s", 8, "Find songs that are released in 2000\n");
        System.out.printf("Operation %d: %s", 9, "Which explicit song is the most/least popular in a given country?\n");
        System.out.printf("Operation %d: %s", 10, "Find songs that released between 2000 and 2002\n");
        System.out.printf("Operation %d: %s", 11, "Find a song that has at least 1 danceability\n");
        System.out.printf("Operation %d: %s", 12, "For a particular song, find its popularity(in a particular country)\n");
        System.out.printf("Operation %d: %s", 13, "Find songs that has duration of longer than 100000 seconds\n");
        System.out.printf("Operation %d: %s", 14, "Find songs that has tempo of longer than 70 for a particular artist(Maria Konopnicka)\n");
        System.out.printf("Operation %d: %s", 15, "Insert a particular song information\n");
        System.out.println("Enter a number for the operation you want to do");
    }

    private static void executeChoice7(MongoCollection<Document> collection) {
    	MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoDatabase database = mongoClient.getDatabase("spotify");
        MongoCollection<Document> collections = database.getCollection("spotify");
        
        FindIterable<Document> documents = collections.find(new BasicDBObject("artists","Ignacio Corsini")).sort(new BasicDBObject("duration_ms",-1)).limit(1);
        
        
    }

    private static void executeChoice6(MongoCollection<Document> collection) {
    	MongoClient mongoClient = MongoClients.create("mongodb://172.31.17.203:27001");
        MongoDatabase database = mongoClient.getDatabase("spotify");
        MongoCollection<Document> collections = database.getCollection("spotify");
        
        FindIterable<Document> documents = collections.find().sort(new BasicDBObject("duration_ms",-1)).limit(1);
        
        

    }

    private static void executeChoice5(MongoCollection<Document> collection) {
    	 

    }

    private static void executeChoice4(MongoCollection<Document> collection) {
    	

    }

    private static void executeChoice3(MongoCollection<Document> collection) {
    	

    }

    private static void executeChoice2(MongoCollection<Document> collection) {
    	 

    }

    private static void executeChoice8(MongoCollection<Document> collection) {
    	 MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
         MongoDatabase database = mongoClient.getDatabase("spotify");
         MongoCollection<Document> collections = database.getCollection("spotify");
    	
    	
    	  FindIterable<Document> documents = collections.find(eq("release_date", 2000));
         for (Document d : documents) {
              System.out.println(d.toJson());
          }
    	
    }

    private static void executeChoice9(MongoCollection<Document> collection) {
    	MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoDatabase database = mongoClient.getDatabase("spotify");
        MongoCollection<Document> collections = database.getCollection("spofity");
        
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("explicit", 1));
        obj.add(new BasicDBObject("country", "AD"));
        andQuery.put("$and", obj);
        FindIterable<Document> documents = collections.find(andQuery).sort(new BasicDBObject("popularity",1)).limit(1);
       for (Document d : documents) {
             System.out.println(d.toJson());
         }
        
    	
    }

    private static void executeChoice10(MongoCollection<Document> collection) {
    	
    	MongoClient mongoClient = MongoClients.create("mongodb://172.31.17.203:27001");
        MongoDatabase database = mongoClient.getDatabase("spotify");
        MongoCollection<Document> collections = database.getCollection("spotify");
    	
        BasicDBObject inQuery = new BasicDBObject();
             List<Integer> list = new ArrayList<Integer>();
             list.add(2000);
             list.add(2001);
             list.add(2002);
             inQuery.put("release_date", new BasicDBObject("$in", list));
             FindIterable<Document> documents = collections.find(inQuery);
             for (Document d : documents) {
                      System.out.println(d.toJson());
                  }
        
    }

    private static void executeChoice11(MongoCollection<Document> collection) {
    	
    	MongoClient mongoClient = MongoClients.create("mongodb://172.31.17.203:27001");
        MongoDatabase database = mongoClient.getDatabase("spotify");
        MongoCollection<Document> collections = database.getCollection("spotify");
    	
        BasicDBObject getQuery = new BasicDBObject();
        getQuery.put("danceability", new BasicDBObject("$gt", 1));
        
        FindIterable<Document> documents = collections.find(getQuery).limit(1);
        
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
     
        
    }

    private static void executeChoice12(MongoCollection<Document> collection) {
    	
    	
    	MongoClient mongoClient = MongoClients.create("mongodb://172.31.17.203:27001");
        MongoDatabase database = mongoClient.getDatabase("spotify");
        MongoCollection<Document> collections = database.getCollection("spotify");
        
        BasicDBObject andQuery = new BasicDBObject();
           List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
            obj.add(new BasicDBObject("name", "Lady of the Evening"));
            obj.add(new BasicDBObject("country", "AD"));
            andQuery.put("$and", obj);
            FindIterable<Document> documents = collections.find(andQuery);
           for (Document d : documents) {
                 System.out.println(d.toJson());
             }
        
       
        
    	

    }

    private static void executeChoice13(MongoCollection<Document> collection) {
    	MongoClient mongoClient = MongoClients.create("mongodb://172.31.17.203:27001");
        MongoDatabase database = mongoClient.getDatabase("spotify");
        MongoCollection<Document> collections = database.getCollection("spotify");
    	
        BasicDBObject getQuery = new BasicDBObject();
        getQuery.put("duration_ms", new BasicDBObject("$gt", 100000));
        FindIterable<Document> documents = collections.find(getQuery).limit(10);
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
    }

    private static void executeChoice14(MongoCollection<Document> collection) {
    		
    	MongoClient mongoClient = MongoClients.create("mongodb://172.31.17.203:27001");
        MongoDatabase database = mongoClient.getDatabase("spotify");
        MongoCollection<Document> collections = database.getCollection("spotify");
        
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
         obj.add(new BasicDBObject("artist", "Maria Konopnicka"));
         obj.add(new BasicDBObject("tempo", new BasicDBObject("$gt", 70)));
         andQuery.put("$and", obj);
         FindIterable<Document> documents = collections.find(andQuery);
         for (Document d : documents) {
               System.out.println(d.toJson());
           }
        
    }

    private static void executeChoice15(MongoCollection<Document> collection) {
    	MongoClient mongoClient = MongoClients.create("mongodb://172.31.17.203:27001");
        MongoDatabase database = mongoClient.getDatabase("spotify");
        MongoCollection<Document> collections = database.getCollection("spotify");
        
        Document document = new Document();
        document.append("name", "Faded");
        document.append("artist", "Alan Walker");
        document.append("release_date", 2015);
        collections.insertOne(document);
        
    }

    private static void executeChoice1(MongoCollection<Document> collection) {

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
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();
        for (String databaseName : databaseNames) {
            System.out.println(databaseName);
        }
    }


    private static void executeQueryOne(String countryCode, int danceability) {
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


    private static void executeQueryTwo(String countryCode) {
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
    private static void executeQueryThree(String artist) {
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoDatabase database = mongoClient.getDatabase("project");
        MongoCollection<Document> collection = database.getCollection("spotify");

        // For a given artist, which song is the most popular?

        FindIterable<Document> documents = collection.find
                (new Document("artists", new Document("$in", artist)))
                .sort(Sorts.descending("popularity")).limit(1);
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
    }
//    private static void executeQueryFour(String artist){
//        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
//        MongoDatabase database = mongoClient.getDatabase("project");
//        MongoCollection<Document> collection = database.getCollection("spotify");
//
//       // Which artist released most songs?
//
//        FindIterable<Document> documents = collection.aggregate(
//        	      Arrays.asList(
//        	              Aggregates.match(Filters.eq("categories", "Bakery")),
//        	              Aggregates.group("$stars", Accumulators.sum("count", 1))
//        	      )
//        for (Document d : documents) {
//            System.out.println(d.toJson());
//        }
//    }
    
   

}
