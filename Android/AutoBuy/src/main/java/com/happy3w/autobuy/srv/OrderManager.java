package com.happy3w.autobuy.srv;

import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/7/10.
 */
public class OrderManager {
    private ServerContext server  =new ServerContext();
    private String page="order/insert.php";

    /**
     * 提交订单。
     * @param po 购买订单。
     * @return 提交结果。
     */
    public String commit(PurchaseOrder po){
        JSONObject obj = new JSONObject();
        try {
            obj.put(po.AMOUNT, po.getAmount());
            SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            obj.put(po.BUYTIME,format.format(po.getBuytime()));
            obj.put(po.PRODUCT,po.getProduct());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url=server.getServiceUrl()+page;
        String result=HttpUtil.sendPost(url,"order="+obj.toString());
        return result;
    }
}
