package cn.duozhilin.restaurants.bean;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class RestaurantMapper {

	@SuppressWarnings("unchecked")
	public static List<Restaurant> getList(Iterable<Document> iterable) {
		List<Restaurant> list = new ArrayList<Restaurant>();
		int i = 0;
		for (Document document : iterable) {
			System.out.println(++i);
			Restaurant restaurant = new Restaurant();
			restaurant.set_id(document.getObjectId("_id"));

			Address address = new Address();
			Document addDocument = document.get("address", Document.class);
			address.setBuilding(addDocument.getString("building"));
			address.setCoord(addDocument.get("coord", ArrayList.class));
			address.setStreet(addDocument.getString("street"));
			address.setZipcode(addDocument.getString("zipcode"));
			restaurant.setAddress(address);

			restaurant.setBorough(document.getString("borough"));
			restaurant.setCuisine(document.getString("cuisine"));

			List<Document> gradeDocuments = document.get("grades", ArrayList.class);
			List<Grade> grades = new ArrayList<Grade>();
			if (null != gradeDocuments) {
				for (Document gradeDocument : gradeDocuments) {
					Grade grade = new Grade();
					grade.setDate(gradeDocument.getDate("date"));
					grade.setGrade(gradeDocument.getString("grade"));
					grade.setScore(gradeDocument.getInteger("score", 0));
					grades.add(grade);
				}
			}
			restaurant.setGrades(grades);

			restaurant.setName(document.getString("name"));
			restaurant.setRestaurant_id(document.getString("restaurant_id"));

			list.add(restaurant);
		}
		return list;
	}
}
