/**
 * 
 */
package cn.duozhilin.restaurants.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.duozhilin.restaurants.bean.Address;
import cn.duozhilin.restaurants.bean.Restaurant;
import cn.duozhilin.restaurants.biz.RestaurantBiz;

/**
 * @author dzl
 * 
 */
public class RestaurantBizTest {
	private RestaurantBiz biz;

	@Before
	public void before() {
//		biz = new RestaurantBizImpl();
		ApplicationContext context = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		biz = (RestaurantBiz) context.getBean("restaurantBiz");
	}

	@Test
	public void testFindAll() {
		try {
			List<Restaurant> list = biz.findAll();
			for (Restaurant restaurant : list) {
				System.out.println(restaurant);
			}
		} catch (Error e) {}
	}
 
	@Test
	public void testFind() {
		List<Restaurant> list = biz.find(10, 10);
		assertTrue(list.size() == 10);
	}

	@Test
	public void testFindByPropertitysMapOfStringObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByPropertitysMapOfStringObjectIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByBuilding() {
		List<Restaurant> list = biz.findByBuilding("1028");
		for (Restaurant restaurant : list) {
			assertTrue(restaurant.getAddress().getBuilding().equals("1028"));
		}
	}

	@Test
	public void testFindByCoord() {
		List<Double> coord = new ArrayList<Double>();
		coord.add(-73.966032);
		coord.add(40.762832);
		List<Restaurant> list = biz.findByCoord(coord);
		for (Restaurant restaurant : list) {
			assertTrue(restaurant.getAddress().getCoord().equals(coord));
		}
	}

	@Test
	public void testFindByZipcode() {
		List<Restaurant> list = biz.findByZipcode("10065");
		for (Restaurant restaurant : list) {
			assertTrue(restaurant.getAddress().getZipcode().equals("10065"));
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
		List<Restaurant> list = biz.findByAddress(address);
		for (Restaurant restaurant : list) {
			assertTrue(restaurant.getAddress().equals(address));
		}
	}

	@Test
	public void testSave() {
		Restaurant restaurant = biz.findByBuilding("1028").get(0);
		restaurant.setName("this is a Test for biz.save");
		restaurant.set_id(null);
		biz.save(restaurant);

		List<Restaurant> list = biz.findByBuilding("1028");
		boolean flag = false;
		for (Restaurant result : list) {
			if (result.getName().equals("this is a Test for biz.save")) {
				flag = true;
			}
		}
		assertTrue(flag);
	}

	@Test
	public void testDelete() {
//		Restaurant restaurant = biz.find(0, 1).get(0);
//		ObjectId id = restaurant.get_id();
		ObjectId id = new ObjectId("558ba90f73584d02fcca475c");
		Restaurant restaurant = new Restaurant();
		restaurant.set_id(id);
		biz.delete(restaurant);

		Map<String, Object> propertitys = new HashMap<String, Object>();
		propertitys.put("_id", id);
		List<Restaurant> list = biz.findByPropertitys(propertitys);
		assertTrue(list.size() == 0);
	}

	@Test
	public void testUpdate() {
		Restaurant restaurant = biz.find(0, 1).get(0);
		ObjectId id = restaurant.get_id();
		restaurant.setName("test for biz.update");
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("_id", id);
		biz.update(restaurant, false, conditions);

		List<Restaurant> list = biz.findByPropertitys(conditions);
		boolean flag = false;
		for (Restaurant result : list) {
			if (result.getName().equals("test for biz.update")) {
				flag = true;
			}
		}

		assertTrue(flag);
	}

}
