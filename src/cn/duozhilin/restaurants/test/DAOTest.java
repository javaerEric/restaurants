package cn.duozhilin.restaurants.test;

import java.util.List;

import cn.duozhilin.restaurants.bean.Restaurant;
import cn.duozhilin.restaurants.dao.RestaurantDAO;
import cn.duozhilin.restaurants.dao.impl.RestaurantDAOImpl;

public class DAOTest {
	private RestaurantDAO dao;
	
	public static void main(String[] args) {
		DAOTest test = new DAOTest();
		test.dao  = new RestaurantDAOImpl();
		List<Restaurant> findAll = test.dao.findAll();
		for (Restaurant restaurant : findAll) {
			System.out.println(restaurant);
		}
	}

}
