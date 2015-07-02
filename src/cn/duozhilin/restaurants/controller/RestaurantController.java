package cn.duozhilin.restaurants.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			
//			String result = JSON.serialize(list.get(0));
			String result = list.toString();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public RestaurantBiz getRestaurantBiz() {
		return restaurantBiz;
	}

	public void setRestaurantBiz(RestaurantBiz restaurantBiz) {
		this.restaurantBiz = restaurantBiz;
	}
}
