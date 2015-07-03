package cn.duozhilin.restaurants.dao;

import java.util.List;
import java.util.Map;

import org.mongodb.morphia.query.UpdateOperations;

import cn.duozhilin.restaurants.bean.Restaurant;

public interface RestaurantDAO {
	
	List<Restaurant> findAll();

	List<Restaurant> find(int offset, int length);

	List<Restaurant> findByPropertitys(Map<String, Object> propertitys);
	
	List<Restaurant> findByPropertitys(Map<String, Object> propertitys, int offset, int length);

	void save(Restaurant restaurant);

	void save(Iterable<Restaurant> entities);
	
	void delete(Restaurant restaurant);
	
	void delete(Map<String, Object> conditions);

	void update(Restaurant restaurant, boolean createIfMissing, Map<String, Object> conditions);

	void update(UpdateOperations<Restaurant> ops, boolean createIfMissing, Map<String, Object> conditions, boolean isAll);

	UpdateOperations<Restaurant> getUpdateOperations();

}
