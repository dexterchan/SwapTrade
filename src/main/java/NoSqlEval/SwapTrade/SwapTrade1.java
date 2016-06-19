package NoSqlEval.SwapTrade;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SwapTrade1 {
	
	public enum TradeStatusEnum{NEW,DONE,VER,MAT};
	String _id;
	String location;
	String book;
	String customer;
	TradeStatusEnum TradeStatus;
	
	String SwapType;
	Date StartDate;
	Date EndDate;
	
	List<AssetLeg> assets;
	
	public SwapTrade1(){
		assets = new LinkedList<AssetLeg>();
	}
	
	public void insertAsset(AssetLeg f){
		assets.add(f);
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	

	public TradeStatusEnum getTradeStatus() {
		return TradeStatus;
	}

	public void setTradeStatus(TradeStatusEnum tradeStatus) {
		TradeStatus = tradeStatus;
	}

	public String getSwapType() {
		return SwapType;
	}

	public void setSwapType(String swapType) {
		SwapType = swapType;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}

	
	
	
}
