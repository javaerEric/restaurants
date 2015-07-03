package cn.duozhilin.restaurants.biz;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import cn.duozhilin.restaurants.bean.Address;
import cn.duozhilin.restaurants.bean.Restaurant;

public interface RestaurantBiz {

	List<Restaurant> findAll();
	
	List<Restaurant> find(int offset, int length);

	List<Restaurant> findByPropertitys(Map<String, Object> propertitys);
	
	List<Restaurant> findByPropertitys(Map<String, Object> propertitys, int offset, int length);
	
	Restaurant findById(ObjectId id);
	
	List<Restaurant> findByName(String name);
	
	List<Restaurant> findByRestaurantId(String restaurantId);

	List<Restaurant> findByBuilding(String building);

	List<Restaurant> findByCoord(List<Double> coord);

	List<Restaurant> findByZipcode(String zipcode);

	List<Restaurant> findByAddress(Address address);
	
	List<Restaurant> findByStreet(String street);

	void save(Restaurant restaurant);
	
	void save(Iterable<Restaurant> entities);

	void delete(Restaurant restaurant);
	
	void delete(Map<String, Object> conditions);

	void update(Restaurant restaurant, boolean createIfMissing, Map<String, Object> conditions);
}
