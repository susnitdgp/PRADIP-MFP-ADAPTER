package com.pradip.vendor.java;

import java.io.Serializable;

public class ChartData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String pay_Status;
private String amount;
public String getPay_Status() {
	return pay_Status;
}
public void setPay_Status(String pay_Status) {
	this.pay_Status = pay_Status;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
}
