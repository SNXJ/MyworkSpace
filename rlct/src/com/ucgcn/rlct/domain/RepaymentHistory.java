package com.ucgcn.rlct.domain;

public class RepaymentHistory {
	private String time;
	private String capital;//本金
	private String interestsServer;//利息加服务费
	private String totalRepayment;//共计还息
	private boolean repayState;
	private boolean dealFinish;//是否交易完成
	
	
	public RepaymentHistory(String time, String capital,
			String interestsServer, String totalRepayment, boolean repayState,
			boolean dealFinish) {
		super();
		this.time = time;
		this.capital = capital;
		this.interestsServer = interestsServer;
		this.totalRepayment = totalRepayment;
		this.repayState = repayState;
		this.dealFinish = dealFinish;
	}

	public boolean isDealFinish() {
		return dealFinish;
	}

	public void setDealFinish(boolean dealFinish) {
		this.dealFinish = dealFinish;
	}

	public boolean isRepayState() {
		return repayState;
	}
	public void setRepayState(boolean repayState) {
		this.repayState = repayState;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getInterestsServer() {
		return interestsServer;
	}
	public void setInterestsServer(String interestsServer) {
		this.interestsServer = interestsServer;
	}
	public String getTotalRepayment() {
		return totalRepayment;
	}
	public void setTotalRepayment(String totalRepayment) {
		this.totalRepayment = totalRepayment;
	}
	
}
