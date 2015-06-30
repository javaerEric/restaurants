package cn.duozhilin.restaurants.biz;

import java.util.List;
import java.util.Map;

import cn.duozhilin.restaurants.bean.Address;
import cn.duozhilin.restaurants.bean.Restaurant;

public interface RestaurantBiz {

	List<Restaurant> findAll();
	
	List<Restaurant> find(int offset, int length);

	List<Restaurant> findByPropertitys(Map<String, Object> propertitys);
	
	List<Restaurant> findByPropertitys(Map<String, Object> propertitys, int offset, int length);

	List<Restaurant> findByBuilding(String building);

	List<Restaurant> findByCoord(List<Double> coord);

	List<Restaurant> findByZipcode(String zipcode);

	List<Restaurant> findByAddress(Address address);

	void save(Restaurant restaurant);

	void delete(Restaurant restaurant);

	void update(Restaurant restaurant, boolean createIfMissing, Map<String, Object> conditions);
}
