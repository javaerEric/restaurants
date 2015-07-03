package cn.duozhilin.restaurants.dao.impl;

import java.util.List;
import java.util.Map;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import cn.duozhilin.restaurants.bean.Restaurant;
import cn.duozhilin.restaurants.dao.RestaurantDAO;

public class RestaurantDAOImpl implements RestaurantDAO {
	private Datastore datastore;

//	public RestaurantDAOImpl() {
//		Morphia morphia = new Morphia();
//		morphia.mapPackage("cn.duozhilin.restaurants.bean");
//		datastore = morphia.createDatastore(new MongoClient("127.0.0.1", 27017), "test");
//		datastore.ensureIndexes();
//	}
	
	@SuppressWarnings("rawtypes")
	public RestaurantDAOImpl(Morphia morphia, Datastore datastore, List<Class> classes) {
		this.datastore = datastore;
		try {
			for (Class clazz : classes) {
				morphia.map(clazz);
			}
			datastore.ensureCaps();
			datastore.ensureIndexes();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		Query<Restaurant> query = getQuery( propertitys);
		return query.asList();
	}

	@Override
	public List<Restaurant> findByPropertitys(Map<String, Object> propertitys, int offset, int length) {
		Query<Restaurant> query = getQuery(propertitys);
		return query.offset(offset).limit(length).asList();
	}

	@Override
	public void save(Restaurant restaurant) {
		datastore.save(restaurant);
	}
	
	@Override
	public void save(Iterable<Restaurant> entities) {
		datastore.save(entities);
	}
	
	@Override
	public void delete(Restaurant restaurant) {
		datastore.delete(restaurant);
	}
	
	@Override
	public void delete(Map<String, Object> conditions) {
		Query<Restaurant> query = getQuery(conditions);
		datastore.delete(query);
	}

	@Override
	public void update(Restaurant restaurant, boolean createIfMissing, Map<String, Object> conditions) {
		Query<Restaurant> query = getQuery(conditions);
		datastore.updateFirst(query, restaurant, createIfMissing);
	}
	
	@Override
	public void update(UpdateOperations<Restaurant> ops, boolean createIfMissing, Map<String, Object> conditions, boolean isAll) {
		Query<Restaurant> query = getQuery(conditions);
		if(isAll){
			datastore.update(query, ops, createIfMissing);
		}else{
			datastore.updateFirst(query, ops, createIfMissing);
		}
		
	}
	
	private Query<Restaurant> getQuery(Map<String, Object> conditions){
		Query<Restaurant> query = datastore.createQuery(Restaurant.class);
		for (String key : conditions.keySet()) {
			query.filter(key, conditions.get(key));
		}
		return query;
	}
	
	@Override
	public UpdateOperations<Restaurant> getUpdateOperations(){
		UpdateOperations<Restaurant> ops =  datastore.createUpdateOperations(Restaurant.class);
		return ops;
	}


	public Datastore getDatastore() {
		return datastore;
	}


	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

}
