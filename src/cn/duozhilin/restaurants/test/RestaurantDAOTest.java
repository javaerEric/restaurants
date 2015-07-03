package cn.duozhilin.restaurants.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.duozhilin.restaurants.bean.Restaurant;
import cn.duozhilin.restaurants.dao.RestaurantDAO;

public class RestaurantDAOTest {
	private RestaurantDAO dao;

	@Before
	public void before() {
//		biz = new RestaurantBizImpl();
		ApplicationContext context = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		dao = (RestaurantDAO) context.getBean("restaurantDAO");
	}

	@Test
	public void testFindAll() {
		List<Restaurant> list = dao.findAll();
		for (Restaurant restaurant : list) {
			System.out.println(restaurant);
		}
	}
	
	@Test
	public void testFind() {
		List<Restaurant> list = dao.find(0, 10);
		for (Restaurant restaurant : list) {
			System.out.println(restaurant);
		}
	}

	@Test
	public void testSave() {
		Map<String, Object> propertitys = new HashMap<String, Object>();
		propertitys.put("address.building", "1028");
		Restaurant restaurant = dao.findByPropertitys(propertitys).get(0);
		restaurant.setName("this is a Test for testSave.");
		restaurant.set_id(null);
		dao.save(restaurant);
		
		List<Restaurant> list = dao.findByPropertitys(propertitys);
		boolean flag = false;
		for (Restaurant result : list) {
			if(result.getName().equals("this is a Test for testSave.")){
				flag = true;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testDelete() {
//		Restaurant restaurant = new Restaurant();
//		restaurant.set_id(new ObjectId("559106b9039bf81be44fb073"));
//		dao.delete(restaurant);
//		
//		List<Restaurant> list = dao.findByBuilding("1028");
//		for (Restaurant result : list) {
//			System.out.println(result);
//		}
	}
	
	@Test
	public void testUpdate() {
		List<Restaurant> list = dao.find(6,3);
		for (Restaurant result : list) {
			System.out.println(result);
		}
		Restaurant restaurant = list.get(0);
		restaurant.setName("this is test for update.dao4!");	
		restaurant.set_id(null);
//		restaurant.setAddress(new Address());
//		restaurant.setBorough(null);
		System.out.println("**************");
		Map<String, Object> condition = new HashMap<String, Object>();
//		condition.put("address.zipcode", "10065");
		dao.update(restaurant, false, condition);
		list = dao.find(0,3);
		for (Restaurant result : list) {
			System.out.println(result);
		}
	}
	
}
