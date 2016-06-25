package NoSqlEval.SwapTrade;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SwapTrade1 {
	
	public enum TradeStatusEnum{NEW,DONE,VER,MAT};
	
	private String id;

	@ObjectId
	@JsonProperty("_id")
	public String getId() {
		return id;
	}

	@ObjectId
	@JsonProperty("_id")
	public void setId(String id) {
		this.id = id;
	}

	String location;
	String book;
	String customer;
	String tradeStatus;
	
	String swapType;
	Date startDate;
	Date endDate;
	
	List<AssetLeg> assets;
	
	public SwapTrade1(){
		assets = new LinkedList<AssetLeg>();
	}
	
	public void insertAsset(AssetLeg f){
		assets.add(f);
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
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getSwapType() {
		return swapType;
	}

	public void setSwapType(String swapType) {
		this.swapType = swapType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<AssetLeg> getAssets() {
		return assets;
	}

	public void setAssets(List<AssetLeg> assets) {
		this.assets = assets;
	}

	
	
	
	
}
