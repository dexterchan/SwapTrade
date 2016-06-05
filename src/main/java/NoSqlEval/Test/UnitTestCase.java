package NoSqlEval.Test;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import NoSqlEval.SwapTrade.SwapTrade1;
import junit.framework.TestCase;

public class UnitTestCase extends TestCase {

	
	SwapTrade1 prepareTrade(){
		SwapTrade1 swp = new SwapTrade1();
		
		
		
		return swp;
	}
	
	@Override
	protected void runTest() throws Throwable {
		// TODO Auto-generated method stub
		super.runTest();
		
		SwapTrade1 swp = prepareTrade();
		
		List<SwapTrade1> lst = new LinkedList<SwapTrade1>();
		lst.add(swp);
		
		Gson gson = new Gson();
		

		
		
		// 2. Java object to JSON, and assign to a String
		String jsonInString = gson.toJson(lst);
		
	}

}
