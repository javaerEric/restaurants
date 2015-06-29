package cn.duozhilin.restaurants.bean;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("restaurants")
public class Restaurant {
	@Id
	private ObjectId _id;

	private Address address;

	private String borough;

	private String cuisine;

	private List<Grade> grades;

	private String name;

	private String restaurant_id;

	public Restaurant() {
	}

	public Restaurant(ObjectId _id, Address address, String borough, String cuisine, List<Grade> grades, String name,
			String restaurant_id) {
		this._id = _id;
		this.address = address;
		this.borough = borough;
		this.cuisine = cuisine;
		this.grades = grades;
		this.name = name;
		this.restaurant_id = restaurant_id;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getBorough() {
		return borough;
	}

	public void setBorough(String borough) {
		this.borough = borough;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	@Override
	public String toString() {
		return "Restaurant [_id=" + _id + ", address=" + address + ", borough=" + borough + ", cuisine=" + cuisine
				+ ", grades=" + grades + ", name=" + name + ", restaurant_id=" + restaurant_id + "]";
	}

}
