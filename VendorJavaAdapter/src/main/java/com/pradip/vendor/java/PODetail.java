package com.pradip.vendor.java;

import java.io.Serializable;

public class PODetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String po_Number;
	private String po_Text;
	private String vendor_Name;
	private String plant_Name;
	private String po_Date;
	public String getPo_Number() {
		return po_Number;
	}
	public void setPo_Number(String po_Number) {
		this.po_Number = po_Number;
	}
	public String getPo_Text() {
		return po_Text;
	}
	public void setPo_Text(String po_Text) {
		this.po_Text = po_Text;
	}
	public String getVendor_Name() {
		return vendor_Name;
	}
	public void setVendor_Name(String vendor_Name) {
		this.vendor_Name = vendor_Name;
	}
	public String getPlant_Name() {
		return plant_Name;
	}
	public void setPlant_Name(String plant_Name) {
		this.plant_Name = plant_Name;
	}
	public String getPo_Date() {
		return po_Date;
	}
	public void setPo_Date(String po_Date) {
		this.po_Date = po_Date;
	}
}
