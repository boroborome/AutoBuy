package com.happy3w.autobuy.model;

import java.util.Date;

/**
 * Created by Administrator on 2016/7/10.
 */
public class PurchaseOrder {
    public  final String  PRODUCT= "product";
    public  final String AMOUNT="amount";
    public  final String BUYTIME ="buytime";
    private String product;
    private double amount;
    private Date buytime;

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
