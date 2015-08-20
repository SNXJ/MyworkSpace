package com.ucgcn.rlct.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MyBankCard implements Serializable {
	
	private String bankIcon;//银行卡图标
	private String bankName;//银行名称
	private String bankCardNumber;//银行卡号码
	
	private boolean checkState;//验证状态  | 成功/失败

	public String getBankIcon() {
		return bankIcon;
	}

	public void setBankIcon(String bankIcon) {
		this.bankIcon = bankIcon;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	public boolean isCheckState() {
		return checkState;
	}

	public void setCheckState(boolean checkState) {
		this.checkState = checkState;
	}




	
}
