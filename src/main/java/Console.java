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
				System.out.println("You have chosen to find the longest song");
				executeChoice6(collection);
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
				executeChoice8(collection, year);
				break;
			case 9:
				System.out.println("You have chosen to find the most popluar explicit song in a specific country");
				System.out.print("Enter the country code(Ex.US,HK,JP) where you would like to query from: ");
				String countryCode3 = in.nextLine();
				executeChoice9(collection, countryCode3);
				break;
			case 10:
				System.out.println("You have chosen to find songs released in 3 particular years");
				System.out.println("Enter year 1: ");
				int year1 = in.nextInt();
				System.out.println("Enter year 2: ");
				int year2 = in.nextInt();
				System.out.println("Enter year 3: ");
				int year3 = in.nextInt();
				executeChoice10(collection, year1, year2, year3);
				break;
			case 11:
				System.out.println("You have chosen to find songs that has at least 1 danceability");
				executeChoice11(collection);
				break;
			case 12:
				System.out.println("You have chosen to find a particular song's information in a given country");
				System.out.print("Enter the song's name: ");
				String song = in.nextLine();
				System.out.print("Enter the country code(Ex.US,HK,JP) where you would like to query from: ");
				String countryCode4 = in.nextLine();
				executeChoice12(collection, song, countryCode4);
				break;
			case 13:
				System.out.println("You have chosen to find songs that have a duration longer than a particular value");
				System.out.print("Enter the duration time(in miliseconds): ");
				int time = in.nextInt();
				executeChoice13(collection, time);
				break;
			case 14:
				System.out.println("You have chosen to find an artist's songs that have a tempo larger than a particular value");
				System.out.println("Enter the artist's name: ");
				String artistName3 = in.nextLine();
				System.out.println("Enter the minimum tempo value(integer): ");
				int tempo = in.nextInt();
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
				executeChoice15(collection, newArtist, song2, year4);
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
		System.out.printf("Operation %d: %s", 7, "Which song is the least popular for a given artist\n");
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
	
	//find song that has the longest duration
	private static void executeChoice6(MongoCollection<Document> collection) {

		FindIterable<Document> documents = collection.find().sort(new BasicDBObject("duration_ms", -1)).limit(1);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice7(MongoCollection<Document> collection, String artist) {

		FindIterable<Document> documents = collection.find(new BasicDBObject("artists", artist))
				.sort(new BasicDBObject("popularity", -1)).limit(1);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice8(MongoCollection<Document> collection, int year) {

		FindIterable<Document> documents = collection.find(eq("release_date", year));
		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice9(MongoCollection<Document> collection, String country) {

		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("explicit", 1));
		obj.add(new BasicDBObject("country", country));
		andQuery.put("$and", obj);
		FindIterable<Document> documents = collection.find(andQuery).sort(new BasicDBObject("popularity", 1)).limit(1);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice10(MongoCollection<Document> collection, int year1, int year2, int year3) {

		BasicDBObject inQuery = new BasicDBObject();
		List<Integer> list = new ArrayList<Integer>();
		list.add(year1);
		list.add(year2);
		list.add(year3);
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

	private static void executeChoice12(MongoCollection<Document> collection, String song, String country) {

		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("name", song));
		obj.add(new BasicDBObject("country", country));
		andQuery.put("$and", obj);
		FindIterable<Document> documents = collection.find(andQuery);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice13(MongoCollection<Document> collection, int time) {

		BasicDBObject getQuery = new BasicDBObject();
		getQuery.put("duration_ms", new BasicDBObject("$gt", time));
		FindIterable<Document> documents = collection.find(getQuery);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}
	}

	private static void executeChoice14(MongoCollection<Document> collection,String artist,int tempo) {

		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("artist", artist));
		obj.add(new BasicDBObject("tempo", new BasicDBObject("$gt", tempo)));
		andQuery.put("$and", obj);
		FindIterable<Document> documents = collection.find(andQuery);
		for (Document d : documents) {
			System.out.println(d.toJson());
		}

	}

	private static void executeChoice15(MongoCollection<Document> collection, String song, String artist,int year) {

		Document document = new Document();
		document.append("name", song);
		document.append("artist", artist);
		document.append("release_date", year);
		collection.insertOne(document);
		System.out.println("New song has been added");

	}

}
