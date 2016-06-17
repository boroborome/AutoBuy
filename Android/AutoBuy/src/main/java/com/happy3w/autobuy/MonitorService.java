package com.happy3w.autobuy;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class MonitorService extends Service {
    private  String TAG="MonitorService";
    public static final String ActionVerifyCodeNew = "VerifyCodeNew";
    MediaPlayer player;
    IBinder binder = new MonitorServiceBinder();
    /*
    *秒级时间戳。
     */
    private long verifyCodeTime = 0;
    private boolean isNew=false;
    private boolean isKnown = false;
    private AlarmManager manager;
    public AlarmManager getManager() {
        if (manager == null) {
            manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        return manager;
    }
    public boolean isNew()
    {
        return isNew;
    }
    public void setNew(boolean value) {
        isNew = value;
    }

    public boolean isKnown() {
        return isKnown;
    }

    public void setIsKnown(boolean isKnown) {
        if (this.isKnown != isKnown && isKnown) {
            stopMusic();
        }
        this.isKnown = isKnown;
    }

    public MonitorService() {
    }
    //定义内容类继承Binder
    public class MonitorServiceBinder extends Binder {
        //返回本地服务
        MonitorService getService(){
            return MonitorService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    @Override
    public void onCreate() {
        Toast.makeText(this, "My Service created", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onCreate");
        player = MediaPlayer.create(this, R.raw.braincandy);
        player.setLooping(false);
        AlarmUtil.repeat(this);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "My Service Stoped", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onDestroy");
        player.stop();
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        Toast.makeText(this, "My Service Start", Toast.LENGTH_LONG).show();
       // timer.schedule(task, 5000, 5000);
        alarmByNewFlag();

        return super.onStartCommand(intent, flags, startId);
    }
    private void alarmByNewFlag()
    {
        Toast.makeText(this,"every 5second to get IsNew Flag.",Toast.LENGTH_LONG).show();
        Log.i(TAG, "onStart");

        isNew = checkVerifyCodeIsNew();
        if(isNew)
        {
            Toast.makeText(this, "play start", Toast.LENGTH_LONG).show();
            player.start();
            isKnown = false;
        }

        if (!isKnown) {
            Intent intentService = new Intent(ActionVerifyCodeNew);
            this.sendBroadcast(intentService);
        }
    }
    private void stopMusic() {
        Toast.makeText(this, "play stop", Toast.LENGTH_LONG).show();
        player.stop();
        try {
            player.prepare();
        } catch (IOException e) {
            Log.e(TAG,"player prepare.",e);
        }
    }
    private boolean checkVerifyCodeIsNew(){
        VerifyCode vc = new VerifyCode();
        long newVerifyCode = vc.getVerifyCodeTime();//-verifyCodeTime;

        if (newVerifyCode <= 0) {
            return false;
        }
        else if (verifyCodeTime != newVerifyCode) {
            verifyCodeTime = newVerifyCode;
            return true;
        }
        // verifyCode没有变化时，是否新的标记保持原样，等用户读取后，设置为false
        return isNew;
    }
}
