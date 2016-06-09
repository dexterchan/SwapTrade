package NoSqlEval.Mongo;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoJdbcClient <T>{
	private MongoJdbcClient(String hostname, int port){
		_client = new MongoClient(hostname,port);
		
	}
	private MongoDatabase db=null;
	private MongoClient _client=null;
	private boolean authenticate(String username, String pwd){
		
		return true;
	}
	
	public static MongoJdbcClient createInstance(String hostname, int port, String dbName, String username, String pwd) throws Exception{
		MongoJdbcClient cl = new MongoJdbcClient(hostname,port);
		cl.db = cl._client.getDatabase(dbName);
		
		return cl;
	}
	
	public void insertDocument (T obj, String collection){
		//ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		//Map<String, Object> map = mapper.convertValue(obj, T);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").create();
		String jsonStr=gson.toJson(obj);
		Map<String,Object> map = new HashMap<String,Object>();
		map = (Map<String,Object>) gson.fromJson(jsonStr, map.getClass());
		
		MongoCollection<Document> c = db.getCollection(collection);
		if(c==null){
			db.createCollection(collection);
			c = db.getCollection(collection);
		}
		
		Document d = new Document(map);
		c.insertOne(d);
	}
	
	
	
}
