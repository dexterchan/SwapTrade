package NoSqlEval.Test;

import java.util.Calendar;
import java.util.List;

import org.bson.Document;

import NoSqlEval.Mongo.MongoJdbcClient;
import NoSqlEval.SwapTrade.SwapTrade1;

public class TestFindUpdate {
	public static void main(String[] args) throws Throwable{
		
		MongoJdbcClient<SwapTrade1> cl = MongoJdbcClient.createInstance("192.168.88.130", 27017, "test", "", "");
		Document filter = new Document("tradeStatus", "NEW");
		List<SwapTrade1> lst = cl.findSwap("swpJ", filter);
		
		System.out.println("Filtered by new state:"+lst.size());
		Thread.sleep(5000);
		System.out.println("Change state from new to done");
		
		UnitTestCase u = new UnitTestCase();
		u.changeTradeStatus(lst, "DONE");
		
		System.out.println(Calendar.getInstance().getTime()+" Update begin of "+lst.size()+" trades");
		cl.saveSwap("swpJ",lst);
		System.out.println(Calendar.getInstance().getTime()+"Update completed");
	}
}
