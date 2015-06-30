package cn.duozhilin.restaurants.dao.impl;

import java.util.List;
import java.util.Map;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import cn.duozhilin.restaurants.bean.Restaurant;
import cn.duozhilin.restaurants.dao.RestaurantDAO;

import com.mongodb.MongoClient;

public class RestaurantDAOImpl implements RestaurantDAO {
	private Datastore datastore;

	public RestaurantDAOImpl() {
		Morphia morphia = new Morphia();
		morphia.mapPackage("cn.duozhilin.restaurants.bean");
		datastore = morphia.createDatastore(new MongoClient("127.0.0.1", 27017), "test");
		datastore.ensureIndexes();
	}
	
	@Override
	public List<Restaurant> findAll() {
		return datastore.find(Restaurant.class).asList();
	}
	

	@Override
	public List<Restaurant> find(int offset, int length) {
		return datastore.find(Restaurant.class).offset(offset).limit(length).asList();
	}
	
	@Override
	public List<Restaurant> findByPropertitys(Map<String, Object> propertitys) {
		Query<Restaurant> query = datastore.find(Restaurant.class);
		getQuery(query, propertitys);
		return query.asList();
	}

	@Override
	public List<Restaurant> findByPropertitys(Map<String, Object> propertitys, int offset, int length) {
		Query<Restaurant> query = datastore.find(Restaurant.class);
		getQuery(query, propertitys);
		return query.offset(offset).limit(length).asList();
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
	public void update(Restaurant restaurant, boolean createIfMissing, Map<String, Object> conditions) {
		Query<Restaurant> query = datastore.createQuery(Restaurant.class);
		getQuery(query, conditions);
		datastore.updateFirst(query, restaurant, createIfMissing);
	}
	
	private Query<Restaurant> getQuery(Query<Restaurant> query, Map<String, Object> conditions){
		for (String key : conditions.keySet()) {
			query.filter(key, conditions.get(key));
		}
		return query;
	}


	public Datastore getDatastore() {
		return datastore;
	}


	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}
}
