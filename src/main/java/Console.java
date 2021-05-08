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
		MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017"); // need to change this
		MongoDatabase database = mongoClient.getDatabase("spotify");
		MongoCollection<Document> collection = database.getCollection("spotify");
		Scanner in = new Scanner(System.in);
		displayChoices();
		boolean keepAsking = true;
		while (keepAsking) {

			int choice = in.nextInt();
			switch (choice) {
			case 1:
				System.out.print("You have chosen to find the danceability of songs within a specific country");
				System.out.print("Enter the country code(Ex.US,HK,JP) where you would like to query from: ");
				String countryCode = in.nextLine();
				System.out.println("Enter a decimal number between 0 and 1 of danceability");
				double danceability = 0;
				executeChoice1(collection, countryCode, danceability);
				break;
			case 2:
				System.out.print("You have chosen to find the most popular artist in a specific country");
				System.out.print("Enter the country code(Ex.US,HK,JP) where you would like to query from: ");
				String countryCode2 = in.nextLine();
				executeChoice2(collection, countryCode2);
				break;
			case 3:
				System.out.print("You have chosen to find the most popular song by an artist's name");
				System.out.print("Enter the name of the artist: ");
				String artistName = in.nextLine();
				executeChoice3(collection, artistName);
				break;
			case 4:
				System.out.print("You have chosen to update the title of a song");
				System.out.print("Enter the name of the song: ");
				String songName = in.nextLine();
				System.out.print("Enter the name of the new song: ");
				String newSongName = in.nextLine();
				executeChoice4(collection, songName, newSongName);
				break;
			case 5:
				System.out.print("You have chosen to delete a song from the database");
				System.out.print("Enter the name of the song: ");
				String songNameDelete = in.nextLine();
				executeChoice5(collection, songNameDelete);
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
			keepAsking = yesNoInput.equalsIgnoreCase("Y") || yesNoInput.equalsIgnoreCase("yes");
			if (keepAsking) {
				displayChoices();
			}
		}
	}

	private static void displayChoices() {
		System.out.printf("Operation %d: %s", 1,
				"Find danceability of a song track greater than a specific value within a specific country\n");
		System.out.printf("Operation %d: %s", 2, "Which artist is the most popular in a specific country?\n");
		System.out.printf("Operation %d: %s", 3, "For a given artist, which song is the most popular??\n");
		System.out.printf("Operation %d: %s", 4, "Update the database with a new song title\n");
		System.out.printf("Operation %d: %s", 5, "Delete a song from the database\n");
		System.out.printf("Operation %d: %s", 6, "Which song has the longest duration?\n");
		System.out.printf("Operation %d: %s", 7, "Which song is the least popular for Artist Ignacio Corsini\n");
		System.out.printf("Operation %d: %s", 8, "Find songs that are released in 2000\n");
		System.out.printf("Operation %d: %s", 9, "Which explicit song is the most/least popular in a given country?\n");
		System.out.printf("Operation %d: %s", 10, "Find songs that released between 2000 and 2002\n");
		System.out.printf("Operation %d: %s", 11, "Find a song that has at least 1 danceability\n");
		System.out.printf("Operation %d: %s", 12,
				"For a particular song, find its popularity(in a particular country)\n");
		System.out.printf("Operation %d: %s", 13, "Find songs that has duration of longer than 100000 seconds\n");
		System.out.printf("Operation %d: %s", 14,
				"Find songs that has tempo of longer than 70 for a particular artist(Maria Konopnicka)\n");
		System.out.printf("Operation %d: %s", 15, "Insert a particular song information\n");
		System.out.println("Enter a number for the operation you want to do");
	}

	// Find danceability of a track greater than a specific value within a specific
	// country
	private static void executeChoice1(MongoCollection<Document> collection, String countryCode, double danceability) {
		// Find danceability of a track greater than a specific value within a specific
		// country
		FindIterable<Document> documents = collection
				.find(new Document("danceability", new Document("$gte", danceability).append("country", countryCode)));
		for (Document d : documents) {
			System.out.println(d.toJson());
		}
	}

	// Which artist is the most popular in a specific country?
	private static void executeChoice2(MongoCollection<Document> collection, String countryCode) {
		FindIterable<Document> documents = collection.find(eq("country", countryCode))
				.sort(Sorts.descending("popularity")).limit(1);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}
	}

	// For a given artist, which song is the most popular?
	private static void executeChoice3(MongoCollection<Document> collection, String artist) {
		FindIterable<Document> documents = collection.find(new Document("artists", new Document("$in", artist)))
				.sort(Sorts.descending("popularity")).limit(1);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}
	}

	// update name of song title
	private static void executeChoice4(MongoCollection<Document> collection, String title, String newTitle) {
		collection.updateOne(eq("name", title), set("title", newTitle));
		System.out.println("Finding this document to verify it's actually updated");
		FindIterable<Document> documents = collection.find(eq("title", newTitle));
		for (Document d : documents) {
			System.out.println(d.toJson());
		}
	}

	// delete
	private static void executeChoice5(MongoCollection<Document> collection, String songName) {
		FindIterable<Document> documents = collection.find(eq("name", songName));
		collection.deleteOne(eq("name", songName));
		for (Document d : documents) {
			System.out.println(d.toJson());
		}
	}

	private static void executeChoice6(MongoCollection<Document> collection) {

		FindIterable<Document> documents = collection.find().sort(new BasicDBObject("duration_ms", -1)).limit(1);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice7(MongoCollection<Document> collection) {

		FindIterable<Document> documents = collection.find(new BasicDBObject("artists", "Ignacio Corsini"))
				.sort(new BasicDBObject("duration_ms", -1)).limit(1);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice8(MongoCollection<Document> collection) {

		FindIterable<Document> documents = collection.find(eq("release_date", 2000));
		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice9(MongoCollection<Document> collection) {

		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("explicit", 1));
		obj.add(new BasicDBObject("country", "AD"));
		andQuery.put("$and", obj);
		FindIterable<Document> documents = collection.find(andQuery).sort(new BasicDBObject("popularity", 1)).limit(1);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice10(MongoCollection<Document> collection) {

		BasicDBObject inQuery = new BasicDBObject();
		List<Integer> list = new ArrayList<Integer>();
		list.add(2000);
		list.add(2001);
		list.add(2002);
		inQuery.put("release_date", new BasicDBObject("$in", list));
		FindIterable<Document> documents = collection.find(inQuery);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice11(MongoCollection<Document> collection) {

		BasicDBObject getQuery = new BasicDBObject();
		getQuery.put("danceability", new BasicDBObject("$gt", 1));

		FindIterable<Document> documents = collection.find(getQuery).limit(1);

		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice12(MongoCollection<Document> collection) {

		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("name", "Lady of the Evening"));
		obj.add(new BasicDBObject("country", "AD"));
		andQuery.put("$and", obj);
		FindIterable<Document> documents = collection.find(andQuery);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice13(MongoCollection<Document> collection) {

		BasicDBObject getQuery = new BasicDBObject();
		getQuery.put("duration_ms", new BasicDBObject("$gt", 100000));
		FindIterable<Document> documents = collection.find(getQuery).limit(10);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}
	}

	private static void executeChoice14(MongoCollection<Document> collection) {

		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("artist", "Maria Konopnicka"));
		obj.add(new BasicDBObject("tempo", new BasicDBObject("$gt", 70)));
		andQuery.put("$and", obj);
		FindIterable<Document> documents = collection.find(andQuery);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice15(MongoCollection<Document> collection) {

		Document document = new Document();
		document.append("name", "Faded");
		document.append("artist", "Alan Walker");
		document.append("release_date", 2015);
		collection.insertOne(document);

	}

}
