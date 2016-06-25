package NoSqlEval.Test;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import NoSqlEval.Mongo.MongoJdbcClient;
import NoSqlEval.SwapTrade.SwapTrade1;

public class TestInsert {


	public static void main(String[] args) throws Throwable{
		// TODO Auto-generated method stub
		UnitTestCase u = new UnitTestCase();

		//u.runIRS(10, "C://Download//lab//mongodb_data//swp.json");
		//u.runCCSUnitTest();
		//u.runCCS(10, "C://Download//lab//mongodb_data//xswp.json");
		MongoJdbcClient<SwapTrade1> cl = MongoJdbcClient.createInstance("192.168.88.130", 27017, "test", "", "");
		System.out.println(Calendar.getInstance().getTime()+" Insert trades begin");
		
		int total=0;
		for(int i=0;i<10 ;i++){
			int size=1000;
			List<SwapTrade1> swpLst = null;
			swpLst = u.runIRSLst(size);
			List<SwapTrade1> ccsswpLst = u.runCCSLst(size);
			swpLst.addAll(ccsswpLst);
			cl.insertDocumentList(swpLst, "swpJ");
			total+=size;
		}
		
		System.out.println(Calendar.getInstance().getTime()+" Inserted:"+total);
	}

}
