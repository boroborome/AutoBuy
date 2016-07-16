package com.happy3w.autobuy.model;

import java.util.Date;

/**
 * Created by Administrator on 2016/7/10.
 */
public class PurchaseOrder {
	public final String ORDERID="orderid";
    public  final String  PRODUCT= "product";
    public  final String AMOUNT="amount";
    public  final String BUYTIME ="buytime";
	/**
	 * 主键
	 */
	private String orderid;
    private String product;
    private double amount;
    private Date buytime;

    public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getBuytime() {
        return buytime;
    }

    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }
}
