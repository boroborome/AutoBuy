package com.happy3w.autobuy.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import com.happy3w.autobuy.AlarmReceiver;

/**
 * Created by Administrator on 2016/6/16.
 */
public class AlarmUtil {
    public static AlarmManager getAlarmManager(Context ctx) {
        return (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
    }

    /**
     * 取消定时执行(有如闹钟的取消)
     *
     * @param ctx
     */
    public static void cancelUpdateBroadcast(Context ctx) {
        AlarmManager am = getAlarmManager(ctx);
        Intent i = new Intent(ctx, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, i, 0);
        am.cancel(pendingIntent);
    }
    /*
    *定时发布广播。
     */
    public static void setTime(Context ctx){
        Intent intent =new Intent(ctx, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, 0);
        //开始时间
        long firstime= System.currentTimeMillis();
        AlarmManager am = getAlarmManager(ctx);;
        //5秒一个周期，不停的发送广播
       // am.setRepeating(AlarmManager.RTC_WAKEUP, firstime, 5, pendingIntent);
        am.set(AlarmManager.RTC_WAKEUP,firstime+5,pendingIntent);
    }
}