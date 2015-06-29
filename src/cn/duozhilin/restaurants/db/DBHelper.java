package cn.duozhilin.restaurants.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DBHelper {
	public static MongoDatabase getDB(String dbName, String host, int port) {
		try {
			MongoClient client = new MongoClient(host, port);
			MongoDatabase db = client.getDatabase(dbName);
			return db;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
