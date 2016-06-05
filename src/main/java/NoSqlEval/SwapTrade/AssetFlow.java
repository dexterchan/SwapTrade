package NoSqlEval.SwapTrade;

import java.util.Date;

public class AssetFlow {
	Date StartDate;
	Date EndDate;
	boolean isPayEnd;
	String ccy;
	double amount;
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
	public boolean isPayEnd() {
		return isPayEnd;
	}
	public void setPayEnd(boolean isPayEnd) {
		this.isPayEnd = isPayEnd;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
