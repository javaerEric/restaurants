package cn.duozhilin.restaurants.dao.impl;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import cn.duozhilin.restaurants.bean.Address;
import cn.duozhilin.restaurants.bean.Restaurant;
import cn.duozhilin.restaurants.dao.RestaurantDAO;

import com.mongodb.MongoClient;

public class RestaurantDAOImpl implements RestaurantDAO {
	private Datastore datastore;
	private String BUILDING = "address.building";
	private String COORD = "address.coord";
	private String ZIPCODE = "address.zipcode";
	private String ADDRESS = "address";

	// @Override
	// public List<Restaurant> findAll() {
	// MongoClient client = new MongoClient("127.0.0.1", 27017);
	// MongoDatabase db = client.getDatabase("test");
	// MongoCollection<Document> restaurants = db.getCollection("restaurants");
	//
	// FindIterable<Document> iterable = restaurants.find().limit(1);
	// List<Restaurant> list = RestaurantMapper.getList(iterable);
	// client.close();
	// return list;
	// }

	public RestaurantDAOImpl() {
		Morphia morphia = new Morphia();

		morphia.mapPackage("com.eastcom.beans");

		datastore = morphia.createDatastore(new MongoClient("127.0.0.1", 27017), "test");

		datastore.ensureIndexes();

	}

	@Override
	public List<Restaurant> findAll() {
		return datastore.find(Restaurant.class).asList();
	}

	@Override
	public List<Restaurant> findByBuilding(String building) {
		return datastore.find(Restaurant.class).filter(BUILDING, building).asList();
	}

	@Override
	public List<Restaurant> findByCoord(List<Double> coord) {
		return datastore.find(Restaurant.class).filter(COORD, coord).asList();
	}

	@Override
	public List<Restaurant> findByZipcode(String zipcode) {
		return datastore.find(Restaurant.class).filter(ZIPCODE, zipcode).asList();
	}

	@Override
	public List<Restaurant> findByAddress(Address address) {
		return datastore.find(Restaurant.class).filter(ADDRESS, address).asList();
	}

	@Override
	public void save(Restaurant restaurant) {
		datastore.save(restaurant);
	}

	@Override
	public void delete(Restaurant restaurant) {
		datastore.delete(restaurant);
	}

	@Override
	public void update(Restaurant restaurant, boolean createIfMissing, String condition, Object value) {
		Query<Restaurant> query = datastore.createQuery(Restaurant.class).filter(condition, value);
		datastore.updateFirst(query, restaurant, createIfMissing);
	}

}
