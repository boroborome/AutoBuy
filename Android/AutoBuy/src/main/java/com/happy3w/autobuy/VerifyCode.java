package com.happy3w.autobuy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;

/**
 * Created by Administrator on 2016/6/13.
 */
public class VerifyCode {
    private ServerContext server = new ServerContext();

    public long getVerifyCodeTime() {
        long code = 0;
        try {
            ServerContext srv = new ServerContext();
            String result = HttpUtil.sendPost(srv.getVerifyCodeIsNewUrl(), null);
            if(!result.isEmpty()&&!result.trim().isEmpty()) {
                code = Long.parseLong(result);
            }
        } catch (Exception e) {
            Log.e("VerifyCode", e.getMessage(), e);
        }
        return code;
    }

    public Bitmap getImg() {
        try {
            ServerContext srv = new ServerContext();
            InputStream inputStream = HttpUtil.getImageViewInputStream(srv.getVerifyCodeImgUrl());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (Exception e) {
            Log.i(e.getMessage(), e.toString());
        }
        return null;
    }

    public String sendVerifyCode(String verifyCode) {
        String result = HttpUtil.sendPost(server.getVerifyCodeIdentify(), "verifycode=" + verifyCode);
        return result;
    }
}
