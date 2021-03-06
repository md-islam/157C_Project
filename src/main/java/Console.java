import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
		MongoClient mongoClient = MongoClients.create("mongodb://ec2-3-17-167-23.us-east-2.compute.amazonaws.com:27001"); // need to change this
		MongoDatabase database = mongoClient.getDatabase("spotify");
		MongoCollection<Document> collection = database.getCollection("spotify");
		Scanner in = new Scanner(System.in);
		displayChoices();
		System.out.println();
		System.out.println();
		boolean keepAsking = true;
		while (keepAsking) {

			int choice = in.nextInt();
			in.nextLine();
			switch (choice) {
			case 1:
				System.out.print("You have chosen to find the energy of songs within a specific country \n");
				System.out.print("Enter the country code(Ex.US,HK,JP) where you would like to query from: \n");
				String countryCode = in.next();
				in.nextLine();
				System.out.println("Enter a decimal number between 0 and 1 of energy");
				double energy = in.nextDouble();
				in.nextLine();
				executeChoice1(collection, countryCode, energy);
				break;
			case 2:
				System.out.print("You have chosen to find the most popular artist in a specific country \n");
				System.out.print("Enter the country code(Ex.US,HK,JP) where you would like to query from: \n");
				String countryCode2 = in.next();
				in.nextLine();
				executeChoice2(collection, countryCode2);
				break;
			case 3:
				System.out.print("You have chosen to find the most popular song by an artist's name\n");
				System.out.print("Enter the name of the artist: ");
				String artistName = in.nextLine();
				executeChoice3(collection, artistName);
				break;
			case 4:
				System.out.print("You have chosen to update the title of a song\n");
				System.out.print("Enter the name of the song: ");
				String songName = in.nextLine();
				System.out.print("Enter the name of the new song: ");
				String newSongName = in.nextLine();
				executeChoice4(collection, songName, newSongName);
				break;
			case 5:
				System.out.print("You have chosen to delete a song from the database\n");
				System.out.print("Enter the name of the song: ");
				String songNameDelete = in.nextLine();
				executeChoice5(collection, songNameDelete);
				break;
			case 6:
				System.out.println("You have chosen to find the longest song in a specific release year");
				System.out.println("Enter the year: ");
				int year2 = in.nextInt();
				executeChoice6(collection, year2);
				break;
			case 7:
				System.out.println("You have chosen to find the least popular song for a particular artist");
				System.out.println("Enter the name of the artist: ");
				String artist = in.nextLine();
				executeChoice7(collection, artist);
				break;
			case 8:
				System.out.println("You have chosen to find songs that released in a particular year");
				System.out.println("Enter the year: ");
				int year = in.nextInt();
				in.nextLine();
				executeChoice8(collection, year);
				break;
			case 9:
				System.out.println("You have chosen to find the most popular explicit song in a specific country \n");
				System.out.print("Enter the country code(Ex.US,HK,JP) where you would like to query from: \n");
				String countryCode3 = in.next();
				in.nextLine();
				executeChoice9(collection, countryCode3);
				break;
			case 10:
				System.out.println("You have chosen to find songs that has instrumentalness of a certain value");
				System.out.println("Enter instrumentalness(bewteen 0 and 1): ");
				double instrumental = in.nextDouble();
				in.nextLine();
				executeChoice10(collection, instrumental);
				break;
			case 11:
				System.out.println("You have chosen to find songs that is danceable to");
				executeChoice11(collection);
				break;
			case 12:
				System.out.println("You have chosen to find a particular song's information in a given country");
				System.out.print("Enter the song's name: ");
				String song = in.nextLine();
				System.out.print("Enter the country code(Ex.US,HK,JP) where you would like to query from: ");
				String countryCode4 = in.next();
				in.nextLine();
				executeChoice12(collection, song, countryCode4);
				break;
			case 13:
				System.out.println("You have chosen to find songs that have a duration longer than a particular value");
				System.out.print("Enter the duration time(in miliseconds): ");
				int time = in.nextInt();
				in.nextLine();
				executeChoice13(collection, time);
				break;
			case 14:
				System.out.println("You have chosen to find an artist's songs that have a tempo larger than a particular value");
				System.out.println("Enter the artist's name: ");
				String artistName3 = in.nextLine();
				System.out.println("Enter the minimum tempo value(integer): ");
				int tempo = in.nextInt();
				in.nextLine();
				executeChoice14(collection, artistName3, tempo);
				break;
			case 15:
				System.out.println("You have chosen to insert a new song information");
				System.out.println("Enter the song's name: ");
				String song2 = in.nextLine();
				System.out.println("Enter the artist's name: ");
				String newArtist = in.nextLine();
				System.out.println("Enter the song's release year: ");
				int year4 = in.nextInt();
				in.nextLine();
				executeChoice15(collection, song2, newArtist, year4);
				break;
			default:
				System.out.println("Invalid choice !");
				break;
			}
			System.out.println("Want to execute another query ? (Y/N)");
			String yesNoInput = in.next();
			in.nextLine();
			keepAsking = yesNoInput.equalsIgnoreCase("Y") || yesNoInput.equalsIgnoreCase("yes");
			if (keepAsking) {
				displayChoices();
			}
		}
	}

	private static void displayChoices() {
		System.out.printf("Operation %d: %s", 1,
				"Find names of song tracks greater than a specific value of energy within a specific country\n");
		System.out.printf("Operation %d: %s", 2, "Which artist is the most popular in a specific country?\n");
		System.out.printf("Operation %d: %s", 3, "For a given artist, which song is the most popular??\n");
		System.out.printf("Operation %d: %s", 4, "Update the database with a new song title\n");
		System.out.printf("Operation %d: %s", 5, "Enter a song name to delete all songs that has the same name as the entered one\n");
		System.out.printf("Operation %d: %s", 6, "Which song has the longest duration in a specific release year?\n");
		System.out.printf("Operation %d: %s", 7, "Which song is the least popular for a given artist\n");
		System.out.printf("Operation %d: %s", 8, "Find songs that are released in a particular year\n");
		System.out.printf("Operation %d: %s", 9, "Which explicit song is the most popular in a given country?\n");
		System.out.printf("Operation %d: %s", 10, "Find songs that has instrumentalness value of a certain value\n");
		System.out.printf("Operation %d: %s", 11, "Find a song that is danceable to\n");
		System.out.printf("Operation %d: %s", 12,
				"For a particular song, find its popularity(in a particular country)\n");
		System.out.printf("Operation %d: %s", 13, "Find a song of particular duration (in milliseconds)\n");
		System.out.printf("Operation %d: %s", 14,
				"Find songs that has tempo greater than certain value for a particular artist\n");
		System.out.printf("Operation %d: %s", 15, "Insert a particular song information\n");
		System.out.println("Enter a number for the operation you want to do");
	}

	// Find danceability of a track greater than a specific value within a specific
	// country
	private static void executeChoice1(MongoCollection<Document> collection, String countryCode, double energy) {
		// Find danceability of a track greater than a specific value within a specific
		// country

		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("country", countryCode));
		obj.add(new BasicDBObject("energy", new BasicDBObject("$gte", energy)));
		andQuery.put("$and", obj);
		FindIterable<Document> documents = collection.find(andQuery).limit(10)
                .projection(new Document("name",1)
                .append("artists",1)
                .append("energy",1));
		for (Document d : documents) {
			System.out.println(d.toJson());
		}
		System.out.println();
	}

	// Which artist is the most popular in a specific country?
	private static void executeChoice2(MongoCollection<Document> collection, String countryCode) {
        FindIterable<Document> documents = collection.find(eq("country", countryCode))
                .sort(new BasicDBObject("popularity", -1)).limit(1)
                .projection(new Document("name",1)
                .append("artists",1)
                .append("country",1));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
        System.out.println();
    }

	// For a given artist, which song is the most popular?
	private static void executeChoice3(MongoCollection<Document> collection, String artist) {
        FindIterable<Document> documents = collection.find(new BasicDBObject("artists", artist))
                .sort(new BasicDBObject("popularity", -1)).limit(1)
                .projection(new Document("name",1)
                .append("artists",1)
                .append("popularity",1));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
        System.out.println();
    }

	// update name of song title
	private static void executeChoice4(MongoCollection<Document> collection, String name, String newName) {
        System.out.println("Finding this document before we update");
        FindIterable<Document> documents = collection.find(eq("name", name))
                .projection(new Document("name",1)
                .append("artists",1)).limit(10);
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
        System.out.println();
        System.out.println("Now let's update....");
        collection.updateMany(eq("name", name), set("name", newName));
        System.out.println("Finding this document to verify it's actually updated");
        documents = collection.find(eq("name", newName))
                .projection(new Document("name",1)
                .append("artists",1)).limit(10);
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
        System.out.println();
    }

	// delete
	private static void executeChoice5(MongoCollection<Document> collection, String songName) {
		System.out.println("Finding the document before we delete");
		FindIterable<Document> documents = collection.find(eq("name", songName));
		for (Document d : documents) {
			System.out.println(d.toJson());
		}
		System.out.println();
		System.out.println("Now let's delete");
		collection.deleteMany(eq("name", songName));
		System.out.println("Printing documents below after delete");
		documents = collection.find(eq("name", songName));
		for (Document d : documents) {
			System.out.println(d.toJson());
		}
	}
	
	//find song that has the longest duration
    private static void executeChoice6(MongoCollection<Document> collection, int year) {
        FindIterable<Document> documents = collection.find(eq("release_date", year)).sort(new BasicDBObject("duration_ms", -1)).limit(1)
                .projection(new Document("name",1)
                .append("artists",1)
                .append("duration_ms",1));            
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
        System.out.println();
    }
	
	//Which song is the least popular for a given artist
    private static void executeChoice7(MongoCollection<Document> collection, String artist) {
        FindIterable<Document> documents = collection.find(new BasicDBObject("artists", artist))
                .sort(new BasicDBObject("popularity", -1)).limit(1)
                .projection(new Document("name",1)
                .append("artists",1)
                .append("popularity",1));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
        System.out.println();
    }

    private static void executeChoice8(MongoCollection<Document> collection, int year) {
        FindIterable<Document> documents = collection.find(eq("release_date", year)).limit(10)
                .projection(new Document("name",1)
                .append("artists",1)
                .append("release_date",1));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
        System.out.println();
    }

    private static void executeChoice9(MongoCollection<Document> collection, String country) {
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("explicit", 1));
        obj.add(new BasicDBObject("country", country));
        andQuery.put("$and", obj);
        FindIterable<Document> documents = collection.find(andQuery).sort(new BasicDBObject("popularity", -1)).limit(1)
                .projection(new Document("name",1)
                .append("explicit",1)
                .append("popularity",1)
                .append("country",1));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
        System.out.println();
    }

    private static void executeChoice10(MongoCollection<Document> collection, double instrumental) {        
        FindIterable<Document> documents = collection.find(eq("instrumentalness", instrumental)).limit(10)
                .projection(new Document("name",1)
                .append("artists",1)
                .append("instrumentalness",1));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
    }

    private static void executeChoice11(MongoCollection<Document> collection) {
        BasicDBObject getQuery = new BasicDBObject();
        getQuery.put("danceability", new BasicDBObject("$gte", 0.7));
        FindIterable<Document> documents = collection.find(getQuery).limit(1)
                .projection(new Document("name",1)
                .append("artists",1)
                .append("danceability",1));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
        System.out.println();
    }

    private static void executeChoice12(MongoCollection<Document> collection, String song, String country) {

        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("name", song));
        obj.add(new BasicDBObject("country", country));
        andQuery.put("$and", obj);
        FindIterable<Document> documents = collection.find(andQuery)
                .projection(new Document("name",1)
                .append("artists",1)
                .append("popularity",1)
                .append("country",1));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
        System.out.println();
    }

    private static void executeChoice13(MongoCollection<Document> collection, int time) {

        BasicDBObject getQuery = new BasicDBObject();
        getQuery.put("duration_ms", new BasicDBObject("$gt", time));
        FindIterable<Document> documents = collection.find(getQuery).limit(10)
                .projection(new Document("name",1)
                .append("artists",1)
                .append("duration_ms",1));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
        System.out.println();
    }

    private static void executeChoice14(MongoCollection<Document> collection,String artist,int tempo) {

        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("artists", artist));
        obj.add(new BasicDBObject("tempo", new BasicDBObject("$gte", tempo)));
        andQuery.put("$and", obj);
        FindIterable<Document> documents = collection.find(andQuery).limit(2)
                .projection(new Document("name",1)
                .append("artists",1)
                .append("tempo",1));
        for (Document d : documents) {
            System.out.println(d.toJson());
        }
        System.out.println();
    }

	private static void executeChoice15(MongoCollection<Document> collection, String song, String artist,int year) {
		System.out.println("Before inserting a document, here are the current documents for this artist");
		FindIterable<Document> documents = collection.find(new BasicDBObject("artists", artist));
		for (Document d : documents) {
			System.out.println(d.toJson());
		}
		System.out.println();
		Document document = new Document();
		document.append("name", song);
		document.append("artists", artist);
		document.append("release_date", year);
		collection.insertOne(document);
		System.out.println("New song has been added");
		System.out.println("Now let's print below with the new document added ....");
		documents = collection.find(new BasicDBObject("artists", artist));
		for (Document d : documents) {
			System.out.println(d.toJson());
		}
		System.out.println();
	}

}
