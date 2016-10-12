package com.happy3w.autobuy.model;

/**
 * Created by Administrator on 2016/7/21.
 */
public class Product {
    public static final String CODE="code";
    public static final String NAME="name";
    public static final String INTEREST="interest";
    private String code;
    private String name;
    private double interest;
    public Product()
    {

    }
    public Product(String code,String name ,Double interest)
    {
        this.code=code;
        this.name=name;
        this.interest=interest;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}
