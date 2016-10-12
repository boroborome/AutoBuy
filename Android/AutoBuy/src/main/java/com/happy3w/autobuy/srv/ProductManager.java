package com.happy3w.autobuy.srv;

import com.happy3w.autobuy.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class ProductManager {
    private static List<Product> products  =new ArrayList<Product>();
    static
    {
           products.add(new Product("YY-A","3个月",7.00));
           products.add(new Product("YY-B","6个月",8.00));
            products.add(new Product("YY-C","12个月",9.00));
    }
    public  Product[] getAllProducts()
    {
        return products.toArray(new Product[0]);
    }
}
