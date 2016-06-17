package com.happy3w.autobuy;

/**
 * Created by Administrator on 2016/6/13.
 */
public class ServerContext {
    public  String getServiceUrl()
    {
        return "http://192.168.3.7:8190/autobuy/";
    }
    public String getVerifyCodeIsNewUrl()
    {
        return this.getServiceUrl()+"isnew.php";
    }
    public String getVerifyCodeImgUrl()
    {
        return this.getServiceUrl()+"upload/verifycode.jpg";
    }
    public String getVerifyCodeIdentify()
    {
        return  this.getServiceUrl()+"identify.php";
    }
}
