package NoSqlEval.Mongo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import NoSqlEval.SwapTrade.SwapTrade1;

public class MongoJdbcClient <T>{
	private MongoJdbcClient(String hostname, int port){
		_client = new MongoClient(hostname,port);
		
	}
	private MongoDatabase db=null;
	private MongoClient _client=null;
	String dbName;
	private boolean authenticate(String username, String pwd){
		
		return true;
	}
	
	public static MongoJdbcClient createInstance(String hostname, int port, String _dbName, String username, String pwd) throws Exception{
		MongoJdbcClient cl = new MongoJdbcClient(hostname,port);
		cl.dbName=_dbName;
		cl.db = cl._client.getDatabase(cl.dbName);
		
		return cl;
	}
	
	private Map<String,Object> helpConvertObject(T obj){
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").create();
		String jsonStr=gson.toJson(obj);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map = (Map<String,Object>) gson.fromJson(jsonStr, map.getClass());
		return map;
	}
	public void insertDocumentList ( List<T> lst, String collection){
		List<Document > docBufferLst = new LinkedList<Document>();
		MongoCollection<Document> c = db.getCollection(collection);
		if(c==null){
			db.createCollection(collection);
			c = db.getCollection(collection);
		}
		
		for(T obj: lst){
			Map<String,Object> mBuffer= helpConvertObject(obj);
			Document d = new Document(mBuffer);
			docBufferLst.add(d);
			
		}
		c.insertMany(docBufferLst);
		
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
	public List<SwapTrade1> findSwap(String collection, Document filter){
		List<SwapTrade1> lst =null;
//		FindIterable<Document> iterable = db.getCollection(collection).find(filter);
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").create();
//		//for( MongoCursor<Document> d=iterable.iterator();d<iterable.e
//		iterable.forEach((Block<Document>) document -> {
//			//String strObj = document.getString("_id");
//			document.remove("_id");
//			String json = document.toJson();
//			SwapTrade1 trade = gson.fromJson(json, SwapTrade1.class);
//		    lst.add(trade);
//		});
		DB db = _client.getDB(dbName);
		DBCollection dbC = db.getCollection(collection); 
		JacksonDBCollection<SwapTrade1, String> coll = JacksonDBCollection.wrap(dbC, SwapTrade1.class,
		        String.class);
		
		lst=coll.find(DBQuery.is("tradeStatus", "NEW")).toArray();
		
		return lst;
	}
	
	public void saveSwap(String collection, List<SwapTrade1> docLst){
		
		DB db = _client.getDB(dbName);
		DBCollection dbC = db.getCollection(collection); 
		JacksonDBCollection<SwapTrade1, String> coll = JacksonDBCollection.wrap(dbC, SwapTrade1.class,
		        String.class);
		
		for(SwapTrade1 s : docLst){
			coll.save(s);
		}
		
	}
	
	
	
}
