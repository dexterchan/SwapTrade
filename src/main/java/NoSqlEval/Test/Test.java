package NoSqlEval.Test;

public class Test {


	public static void main(String[] args) throws Throwable{
		// TODO Auto-generated method stub
		UnitTestCase u = new UnitTestCase();

		u.runIRS(99999, "C://Download//lab//mongodb_data//swp.json");
		//u.runCCSUnitTest();
		u.runCCS(99999, "C://Download//lab//mongodb_data//xswp.json");
		System.exit(0);
	}

}
