package NoSqlEval.Test;

import NoSqlEval.Mongo.MongoJdbcClient;
import NoSqlEval.SwapTrade.SwapTrade1;

public class Test {


	public static void main(String[] args) throws Throwable{
		// TODO Auto-generated method stub
		UnitTestCase u = new UnitTestCase();

		u.runIRS(1, "C://Download//lab//mongodb_data//swp.json");
		//u.runCCSUnitTest();
		u.runCCS(1, "C://Download//lab//mongodb_data//xswp.json");
		
		SwapTrade1 swp = u.prepareRandomIRS(100000000,"USD","LIBOR",0.1);
		
		MongoJdbcClient<SwapTrade1> cl = MongoJdbcClient.createInstance("192.168.88.128", 27017, "test", "", "");
		
		
		cl.insertDocument(swp,"swpJ");
		
		System.exit(0);
	}

}
