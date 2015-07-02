package cn.duozhilin.restaurants.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.duozhilin.restaurants.bean.Address;
import cn.duozhilin.restaurants.bean.Restaurant;
import cn.duozhilin.restaurants.biz.RestaurantBiz;
import cn.duozhilin.restaurants.dao.RestaurantDAO;

public class RestaurantBizImpl implements RestaurantBiz {
	private static final String BUILDING = "address.building";
	private static final String COORD = "address.coord";
	private static final String ZIPCODE = "address.zipcode";
	private static final String ADDRESS = "address";
	private RestaurantDAO dao;

	@Override
	public List<Restaurant> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Restaurant> find(int offset, int length) {
		return dao.find(offset, length);
	}

	@Override
	public List<Restaurant> findByPropertitys(Map<String, Object> propertitys) {
		return dao.findByPropertitys(propertitys);
	}

	@Override
	public List<Restaurant> findByPropertitys(Map<String, Object> propertitys, int offset, int length) {
		return dao.findByPropertitys(propertitys, offset, length);
	}

	@Override
	public List<Restaurant> findByBuilding(String building) {
		Map<String, Object> propertitys = new HashMap<String, Object>();
		propertitys.put(BUILDING, building);
		return dao.findByPropertitys(propertitys);
	}

	@Override
	public List<Restaurant> findByCoord(List<Double> coord) {
		Map<String, Object> propertitys = new HashMap<String, Object>();
		propertitys.put(COORD, coord);
		return dao.findByPropertitys(propertitys);
	}

	@Override
	public List<Restaurant> findByZipcode(String zipcode) {
		Map<String, Object> propertitys = new HashMap<String, Object>();
		propertitys.put(ZIPCODE, zipcode);
		return dao.findByPropertitys(propertitys);
	}

	@Override
	public List<Restaurant> findByAddress(Address address) {
		Map<String, Object> propertitys = new HashMap<String, Object>();
		propertitys.put(ADDRESS, address);
		return dao.findByPropertitys(propertitys);
	}

	@Override
	public void save(Restaurant restaurant) {
		dao.save(restaurant);
	}

	@Override
	public void delete(Restaurant restaurant) {
		dao.delete(restaurant);
	}

	@Override
	public void update(Restaurant restaurant, boolean createIfMissing, Map<String, Object> conditions) {
		dao.update(restaurant, createIfMissing, conditions);
	}

	public RestaurantDAO getDao() {
		return dao;
	}

	public void setDao(RestaurantDAO dao) {
		this.dao = dao;
	}
}
