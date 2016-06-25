package com.happy3w.autobuy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/16.
 */
public class AlarmReceiver extends BroadcastReceiver {
    private  static String TAG="AlarmReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
      Log.i(TAG,"received.");
        Intent intentService = new Intent(context, MonitorService.class);
        context.startService(intentService);
      //  Toast.makeText(context, "repeating alarm",Toast.LENGTH_LONG).show();
    }

}