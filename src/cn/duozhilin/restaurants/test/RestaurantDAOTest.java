package cn.duozhilin.restaurants.test;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import cn.duozhilin.restaurants.bean.Address;
import cn.duozhilin.restaurants.bean.Restaurant;
import cn.duozhilin.restaurants.dao.RestaurantDAO;
import cn.duozhilin.restaurants.dao.impl.RestaurantDAOImpl;

public class RestaurantDAOTest {
	private RestaurantDAO dao;

	@Before
	public void before() {
		dao = new RestaurantDAOImpl();
	}

	@Test
	public void testFindAll() {
		List<Restaurant> list = dao.findAll();
		for (Restaurant restaurant : list) {
			System.out.println(restaurant);
		}
	}

	@Test
	public void testFindByBuilding() {
		List<Restaurant> list = dao.findByBuilding("1028");
		for (Restaurant restaurant : list) {
			System.out.println(restaurant);
		}
	}
	
	@Test
	public void testFindByCoord() {
		List<Double> coord = new ArrayList<Double>();
		coord.add(-73.966032);
		coord.add(40.762832);
		List<Restaurant> list = dao.findByCoord(coord);
		for (Restaurant restaurant : list) {
			System.out.println(restaurant);
		}
	}
	
	@Test
	public void testFindByZipcode() {
		List<Restaurant> list = dao.findByZipcode("10065");
		for (Restaurant restaurant : list) {
			System.out.println(restaurant);
		}
	}
	
	@Test
	public void testFindByAddress() {
		List<Double> coord = new ArrayList<Double>();
		coord.add(-73.966032);
		coord.add(40.762832);
		
		Address address = new Address();
		address.setBuilding("1028");
		address.setCoord(coord);
		address.setStreet("3 Avenue");
		address.setZipcode("10065");
		List<Restaurant> list = dao.findByAddress(address);
		for (Restaurant restaurant : list) {
			System.out.println(restaurant);
		}
	}

	@Test
	public void testSave() {
		Restaurant restaurant = dao.findByBuilding("1028").get(0);
		restaurant.setName("this is a Test document1");
		restaurant.set_id(null);
		dao.save(restaurant);
		
		List<Restaurant> list = dao.findByBuilding("1028");
		for (Restaurant result : list) {
			System.out.println(result);
		}
	}
	
	@Test
	public void testDelete() {
		Restaurant restaurant = new Restaurant();
		restaurant.set_id(new ObjectId("559106b9039bf81be44fb073"));
		dao.delete(restaurant);
		
		List<Restaurant> list = dao.findByBuilding("1028");
		for (Restaurant result : list) {
			System.out.println(result);
		}
	}
	
	@Test
	public void testUpdate() {
		Restaurant restaurant = dao.findByZipcode("10065").get(0);
		restaurant.setName("update test!");		
		dao.update(restaurant, false, "address.zipcode", "10065");
		List<Restaurant> list = dao.findByZipcode("10065");
		for (Restaurant result : list) {
			System.out.println(result);
		}
	}
	
}
