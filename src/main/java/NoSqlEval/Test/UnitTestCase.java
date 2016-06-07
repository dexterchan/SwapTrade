package NoSqlEval.Test;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

import NoSqlEval.SwapTrade.AssetFlow;
import NoSqlEval.SwapTrade.AssetLeg;
import NoSqlEval.SwapTrade.SwapTrade1;

public class UnitTestCase  {
	
	SwapTrade1 prepareRandomCCS(String type,double payntl, String payccy,String payindex, double paySpread,double recntl,String recccy,String recindex, double recSpread){
		double rate = 1.0;
		SwapTrade1 swp = new SwapTrade1();
		swp.setBook("USDSWP");
		swp.setLocation("LONDON");
		swp.setCustomer("HSBCHKH");
		Calendar c = Calendar.getInstance();
		swp.setStartDate(c.getTime());
		Calendar e = (Calendar)c.clone();
		e.add(Calendar.YEAR, 1);
		swp.setEndDate(e.getTime());
		swp.setSwapType(type);
		swp.setTradeStatus("VER");
		
				
		//prepare pay asset
		AssetLeg payLeg = new AssetLeg();
		payLeg.setCcy(payccy);
		payLeg.setIndex(payindex);
		payLeg.setNotional(payntl);
		payLeg.setPorR( "P");
		payLeg.setRate(0);
		payLeg.setSpread(paySpread);
		payLeg.setType("CCS");
		for(int i=0;i<2;i++){
			AssetFlow f = new AssetFlow();
			f.setCcy(payccy);
			double amt = payntl *( rate +paySpread/100)/100 /2;
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
			payLeg.insertCashflow(f);
		}
		swp.insertAsset(payLeg);
		
		AssetLeg recLeg = new AssetLeg();
		recLeg.setCcy(recccy);
		recLeg.setIndex(recindex);
		recLeg.setNotional(recntl);
		recLeg.setPorR("R");
		recLeg.setRate(0);
		recLeg.setSpread(recSpread);
		recLeg.setType("CCS");
		for(int i=0;i<2;i++){
			AssetFlow f = new AssetFlow();
			f.setCcy(recccy);
			double amt = payntl *( rate +paySpread/100)/100 /2;
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
			recLeg.insertCashflow(f);
		}
		swp.insertAsset(recLeg);
		
		
		return swp;
	}
	
	
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
		
		Random random  = new Random(System.currentTimeMillis());
		double rr = random.nextDouble();
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
			double amt = ntl * rate/100 /2 * (payFix?-1:1);
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
			double amt = ntl * rate/100 /2 * (payFix?1:-1);
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
	
	
	public void runIRSUnitTest() throws Throwable {
		// TODO Auto-generated method stub
		Random random  = new Random(System.currentTimeMillis());
		List<SwapTrade1> lst = new LinkedList<SwapTrade1>();
		double ntl = 100000000;
		SwapTrade1 swp = prepareRandomIRS(100000000,"USD","LIBOR",0.1);
		
		lst.add(swp);
		Gson gson = new Gson();
		
		// 2. Java object to JSON, and assign to a String
		String jsonInString = gson.toJson(lst);
		
		System.out.println(jsonInString);
		
	}
	
	public void runCCSUnitTest() throws Throwable {
		// TODO Auto-generated method stub
		Random random  = new Random(System.currentTimeMillis());
		List<SwapTrade1> lst = new LinkedList<SwapTrade1>();
		double usdntl = 100000000;
		double fxrate = 100 + random.nextGaussian()*10;
		double jpyntl=usdntl * fxrate;
		SwapTrade1 swp = this.prepareRandomCCS("CCSMTM", usdntl, "USD", "LIBOR", 0, jpyntl, "JPY", "LIBOR", 0.1);
		
		lst.add(swp);
		Gson gson = new Gson();
		
		// 2. Java object to JSON, and assign to a String
		String jsonInString = gson.toJson(lst);
		
		System.out.println(jsonInString);
		
	}
	public void runIRS(int num, String fileName) throws Throwable {
		// TODO Auto-generated method stub
		Random random = new Random(System.currentTimeMillis());
		List<SwapTrade1> lst = new LinkedList<SwapTrade1>();
		double basentl = 100000000;

		for( int i=0;i<num;i++){
			double ntl = basentl += random.nextGaussian() *  basentl;
			SwapTrade1 swp = prepareRandomIRS(ntl, "USD", "LIBOR", 0.1);
	
			lst.add(swp);
		}

		Gson gson = new Gson();

		Writer writer = new FileWriter (fileName);
		
		// 2. Java object to JSON, and assign to a String
		gson.toJson(lst,writer);
		writer.close();
		System.out.println("Total number of swp generated:"+lst.size());
		
	}
	
	public void runCCS(int num, String fileName) throws Throwable {
		// TODO Auto-generated method stub
		Random random = new Random(System.currentTimeMillis());
		List<SwapTrade1> lst = new LinkedList<SwapTrade1>();
		double basentl = 100000000;

		for( int i=0;i<num;i++){
			double direction=random.nextDouble();
			double usdntl = basentl;
			double fxrate = 100 + random.nextGaussian()*10;
			double jpyntl=usdntl * fxrate;
			SwapTrade1 swp=null;
			if(direction<0.5){
				swp = this.prepareRandomCCS("CCSMTM", usdntl, "USD", "LIBOR", 0, jpyntl, "JPY", "LIBOR", 0.1);
			}else{
				swp = this.prepareRandomCCS("CCSMTM", jpyntl, "JPY", "LIBOR", 0.1, usdntl, "USD", "LIBOR", 0);
			}
	
			lst.add(swp);
		}

		Gson gson = new Gson();

		Writer writer = new FileWriter (fileName);
		
		// 2. Java object to JSON, and assign to a String
		gson.toJson(lst,writer);
		writer.close();
		System.out.println("Total number of swp generated:"+lst.size());
		
	}
	
}
