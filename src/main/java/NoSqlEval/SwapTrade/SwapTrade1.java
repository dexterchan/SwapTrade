package NoSqlEval.SwapTrade;

import java.util.Date;
import java.util.List;

public class SwapTrade1 {
	String _id;
	String location;
	String book;
	String customer;
	String TradeStatus;
	
	String SwapType;
	Date StartDate;
	Date EndDate;
	
	List<AssetLeg> assets;

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

	public String getTradeStatus() {
		return TradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
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

	public List<AssetLeg> getAssets() {
		return assets;
	}

	public void setAssets(List<AssetLeg> assets) {
		this.assets = assets;
	}
	
	
}
