package cn.duozhilin.restaurants.dao.impl;

import java.util.List;

import org.bson.Document;

import cn.duozhilin.restaurants.bean.Restaurant;
import cn.duozhilin.restaurants.bean.RestaurantMapper;
import cn.duozhilin.restaurants.dao.RestaurantDAO;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class RestaurantDAOImpl implements RestaurantDAO {

	private static final String HOST = "127.0.0.1";
	private static final int PORT = 27017;
	private static final String DB_NAME = "test";
	private static final String COLLECTION_NAME = "restaurants";

	public RestaurantDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<Restaurant> findAll() {
		MongoClient client = new MongoClient(HOST, PORT);
		MongoDatabase db = client.getDatabase(DB_NAME);
		MongoCollection<Document> restaurants = db.getCollection(COLLECTION_NAME);
		FindIterable<Document> iterable = restaurants.find().skip(7621).limit(10000);

		List<Restaurant> list = RestaurantMapper.getList(iterable);
		client.close();
		return list;
	}
}
