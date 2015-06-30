package cn.duozhilin.restaurants.db;

import java.util.List;

import org.mongodb.morphia.DatastoreImpl;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class MongoDataStore extends DatastoreImpl {
    @SuppressWarnings("rawtypes")
	private List<Class> entityClasses;
 
    public MongoDataStore(Morphia morphia, MongoClient mongoClient, String dbName) {
        super(morphia, mongoClient, dbName);
    }
 
    @SuppressWarnings("rawtypes")
	public void setEntityClasses(List<Class> entityClasses) {
        this.entityClasses = entityClasses;
    }
 
    /**
     * 初始化相关映射类
     */
    @SuppressWarnings("rawtypes")
	public void init() {
        if (this.entityClasses != null && !this.entityClasses.isEmpty()) {
            for (Class entityClass : entityClasses) {
                this.getMapper().addMappedClass(entityClass);
            }
        }
    }
}