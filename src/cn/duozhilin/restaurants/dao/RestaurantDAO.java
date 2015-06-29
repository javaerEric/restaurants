package cn.duozhilin.restaurants.dao;

import java.util.List;

import cn.duozhilin.restaurants.bean.Address;
import cn.duozhilin.restaurants.bean.Restaurant;

public interface RestaurantDAO {

	List<Restaurant> findAll();

	List<Restaurant> findByBuilding(String building);

	List<Restaurant> findByCoord(List<Double> coord);

	List<Restaurant> findByZipcode(String zipcode);

	List<Restaurant> findByAddress(Address address);

	void save(Restaurant restaurant);

	void delete(Restaurant restaurant);

	void update(Restaurant restaurant, boolean createIfMissing, String condition, Object value);
}
