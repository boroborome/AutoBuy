package com.happy3w.autobuy;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/15.
 */
public class MonitorServiceConnection implements ServiceConnection {
    private MonitorService monitorService;
    public MonitorService getMonitorService()
    {
        return monitorService;
    }
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        monitorService =((MonitorService.MonitorServiceBinder)(service)).getService();
        Log.i(monitorService.getClass().getName(), "monitor service connected");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        monitorService=null;
        Log.i(monitorService.getClass().getName(), "monitor service disconnected");
    }
}
