package cn.duozhilin.restaurants.db;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class MorphiaInit {

	@SuppressWarnings("rawtypes")
	public MorphiaInit(Morphia morphia, Datastore datastore, List<Class> classes) {
		try {
			for (Class clazz : classes) {
				morphia.map(clazz);
			}
			datastore.ensureCaps();
			datastore.ensureIndexes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}