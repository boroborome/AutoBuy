package com.happy3w.autobuy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/16.
 */
public class AlarmUtil {
    public static AlarmManager getAlarmManager(Context ctx) {
        return (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
    }

    /**
     * 指定时间后进行更新赛事信息(有如闹钟的设置)
     * 注意: Receiver记得在manifest.xml中注册
     *
     * @param ctx
     */
    public static void sendUpdateBroadcast(Context ctx) {
        Log.i("score", "send to start update broadcase,delay time :" + 60000);

        AlarmManager am = getAlarmManager(ctx);
        // 60秒后将产生广播,触发UpdateReceiver的执行,这个方法才是真正的更新数据的操作主要代码
        Intent i = new Intent(ctx, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, i, 0);
        am.set(AlarmManager.RTC, System.currentTimeMillis() + 60000, pendingIntent);
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
    public static void repeat(Context ctx){
        Intent intent =new Intent(ctx, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, 0);

        //开始时间
        long firstime= SystemClock.elapsedRealtime();

        AlarmManager am = getAlarmManager(ctx);;
        //60秒一个周期，不停的发送广播
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 0, 5 * 1000, pendingIntent);
    }
}