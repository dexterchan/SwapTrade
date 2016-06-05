package NoSqlEval.SwapTrade;

import java.util.List;

public class AssetLeg {
	String PorR; //Pay or receive
	String type;
	String ccy;
	double notional;
	String index;
	double rate;
	double spread;
	
	List<AssetFlow> cashflows;

	public String getPorR() {
		return PorR;
	}

	public void setPorR(String porR) {
		PorR = porR;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public double getNotional() {
		return notional;
	}

	public void setNotional(double notional) {
		this.notional = notional;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getSpread() {
		return spread;
	}

	public void setSpread(double spread) {
		this.spread = spread;
	}

	public List<AssetFlow> getCashflows() {
		return cashflows;
	}

	public void setCashflows(List<AssetFlow> cashflows) {
		this.cashflows = cashflows;
	}
	
}
