package cn.duozhilin.restaurants.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.duozhilin.restaurants.bean.Restaurant;
import cn.duozhilin.restaurants.biz.RestaurantBiz;

@Controller
public class RestaurantController {

	@Autowired
	private RestaurantBiz restaurantBiz;

	@RequestMapping("/find.action")
	public void getRestaurant(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "_id", required = true) String id) {
		try {
			List<Restaurant> list = restaurantBiz.findByZipcode("10065");

			// String result = JSON.serialize(list.get(0));
			String result = list.toString();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/get.action")
	public void getRestaurant(HttpServletRequest request, HttpServletResponse response, int offset, int length, Restaurant restaurant) {
		try {
			List<Restaurant> list;
			Map<String, Object> condition = buildCondition(restaurant);
			System.out.println(condition);
			if (length == 0) {
				list = restaurantBiz.findByPropertitys(condition);
			} else {
				list = restaurantBiz.findByPropertitys(condition, offset, length);
			}
			String result = list.toString();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/save.action")
	public void saveRestaurant(HttpServletRequest request, HttpServletResponse response, Restaurant restaurant) {
		String result = null;
		if (null != restaurant) {
			ObjectId id = new ObjectId();
			restaurant.set_id(id);
			// 检查合法性
			restaurantBiz.save(restaurant);
			result = restaurant.toString();
		}else{
			result = "{\"errorMsg\":\"保存的对象不能为空\"}";
		}
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 构造查询条件
	 * 
	 * @param restaurant
	 */
	private Map<String, Object> buildCondition(Restaurant restaurant) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != restaurant) {
			if (null != restaurant.get_id()) {
				map.put("_id", restaurant.get_id());
			}
			if (null != restaurant.getBorough()) {
				map.put("borough", restaurant.getBorough());
			}
			if (null != restaurant.getCuisine()) {
				map.put("cuisine", restaurant.getCuisine());
			}
			if (null != restaurant.getName()) {
				map.put("name", restaurant.getName());
			}
			if (null != restaurant.getRestaurant_id()) {
				map.put("restaurant_id", restaurant.getRestaurant_id());
			}
			if (null != restaurant.getAddress()) {
				// TODO 获取Address的条件
			}
			if (null != restaurant.getGrades()) {
				// TODO 构造对应的条件
			}
		}
		return map;
	}

	public RestaurantBiz getRestaurantBiz() {
		return restaurantBiz;
	}

	public void setRestaurantBiz(RestaurantBiz restaurantBiz) {
		this.restaurantBiz = restaurantBiz;
	}
}
