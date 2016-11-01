package com.happy3w.autobuy.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/10.
 */
public class UserOrder {
	public static final String ORDERID="orderid";
    public static final String  PRODUCT= "product";
    public static final String AMOUNT="amount";
    public static final String BUYTIME ="buytime";
    public static final String TASK="task";
    public static final String EndTime="endtime";
    private Map<String,String> items  =new HashMap<String,String>();
    public void put(String key,String value)
    {
    	items.put(key, value);
    }
    public String get(String key)
    {
    	return items.get(key);
    }
    public Map<String,String> getItems()
    {
    	return items;
    }
    
	/**
	 * 主键
	 */
	private String orderid;
    private String product;
    private double amount;
    private Date buytime;
    private String rate;
	private String task;

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

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	public void setTask(String task)
	{
		this.task=task;
	}
	public String getTask() {
		// TODO Auto-generated method stub
		return this.task;
	}
	public static String getEndtime() {
		return EndTime;
	}
    
}
