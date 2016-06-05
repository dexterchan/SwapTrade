package NoSqlEval.Test;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import NoSqlEval.SwapTrade.AssetFlow;
import NoSqlEval.SwapTrade.AssetLeg;
import NoSqlEval.SwapTrade.SwapTrade1;
import junit.framework.TestCase;

public class UnitTestCase  {
	
	
	SwapTrade1 prepareRandomIRS(double ntl, String ccy,String index, double rate){
		SwapTrade1 swp = new SwapTrade1();
		swp.setBook("USDSWP");
		swp.setLocation("LONDON");
		swp.setCustomer("HSBCHKH");
		Calendar c = Calendar.getInstance();
		swp.setStartDate(c.getTime());
		Calendar e = (Calendar)c.clone();
		e.add(Calendar.YEAR, 1);
		swp.setEndDate(e.getTime());
		swp.setSwapType("IRS");
		swp.setTradeStatus("VER");
		
		double rr = Math.random();
		boolean payFix=rr>0.5?true:false;
		
		
		//prepare Fix asset
		AssetLeg FixLeg = new AssetLeg();
		FixLeg.setCcy(ccy);
		FixLeg.setIndex("Fixed");
		FixLeg.setNotional(ntl);
		FixLeg.setPorR( payFix?"P":"R");
		FixLeg.setRate(rate);
		FixLeg.setSpread(0);
		FixLeg.setType("IRS");
		for(int i=0;i<2;i++){
			AssetFlow f = new AssetFlow();
			f.setCcy(ccy);
			double amt = ntl * rate /2 * (payFix?-1:1);
			f.setAmount(amt);
			Calendar st = Calendar.getInstance();
			st.setTime(swp.getStartDate());
			Calendar et = Calendar.getInstance();
			et.setTime(swp.getStartDate());
			st.add(Calendar.MONTH, 6*i);
			et.add(Calendar.MONTH, 6*(i+1));
			f.setStartDate(st.getTime());
			f.setEndDate(et.getTime());
			f.setPayEnd(true);
			FixLeg.insertCashflow(f);
		}
		swp.insertAsset(FixLeg);
		
		AssetLeg fltLeg = new AssetLeg();
		fltLeg.setCcy(ccy);
		fltLeg.setIndex(index);
		fltLeg.setNotional(ntl);
		fltLeg.setPorR(payFix?"R":"P");
		fltLeg.setRate(0);
		fltLeg.setSpread(0);
		fltLeg.setType("IRS");
		for(int i=0;i<2;i++){
			AssetFlow f = new AssetFlow();
			f.setCcy(ccy);
			double amt = ntl * rate /2 * (payFix?1:-1);
			f.setAmount(amt);
			Calendar st = Calendar.getInstance();
			st.setTime(swp.getStartDate());
			Calendar et = Calendar.getInstance();
			et.setTime(swp.getStartDate());
			st.add(Calendar.MONTH, 6*i);
			et.add(Calendar.MONTH, 6*(i+1));
			f.setStartDate(st.getTime());
			f.setEndDate(et.getTime());
			f.setPayEnd(true);
			fltLeg.insertCashflow(f);
		}
		swp.insertAsset(fltLeg);
		
		
		return swp;
	}
	
	
	public void runTest() throws Throwable {
		// TODO Auto-generated method stub
		
		SwapTrade1 swp = prepareRandomIRS(100000000,"USD","LIBOR",0.1);
		
		List<SwapTrade1> lst = new LinkedList<SwapTrade1>();
		lst.add(swp);
		
		Gson gson = new Gson();
		

		
		
		// 2. Java object to JSON, and assign to a String
		String jsonInString = gson.toJson(lst);
		
		System.out.println(jsonInString);
		
	}

	
}
