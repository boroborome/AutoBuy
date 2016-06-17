package com.happy3w.autobuy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button buttonStart;
    Button buttonStop;
    Button buttonGet;
    Button buttonSend;
    MonitorServiceConnection  monitorConnect =new MonitorServiceConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);
        buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonSend = (Button) findViewById(R.id.buttonSend);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "onClick: starting service");
                connectService();
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "onClick: stoping service");
               disconnectService();
            }
        });
        addOnClickListenerToButtonGet();
        addOnClickListenerToButtonSend();

        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!MainActivity.this.hasWindowFocus()) {
                    return;
                }
                MonitorService service = getMonitorService();
                if (service == null) {
                    return;
                }
                boolean isNew = service.isNew();
                service.setIsKnown(true);
                buttonGet.setBackgroundColor(isNew ? Color.GREEN : Color.GRAY);
            }
        }, new IntentFilter(MonitorService.ActionVerifyCodeNew));
    }

    private void addOnClickListenerToButtonGet() {
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"onClick: get verifycode from service", Toast.LENGTH_LONG).show();
                Log.i(this.getClass().getName(), "onClick: get verifycode from service");
                MonitorService service = getMonitorService();
                if (service == null) {
                    Toast.makeText(MainActivity.this,"onClick: get service Not connected", Toast.LENGTH_LONG).show();
                    return;
                }
                boolean isNew = service.isNew();
                if (isNew) {
                    service.setIsKnown(true);

                    Toast.makeText(MainActivity.this, "has new verify code.", Toast.LENGTH_LONG).show();
                    ImageView img = (ImageView) findViewById(R.id.imgVerifyCode);
                    VerifyCode vc = new VerifyCode();
                    Bitmap bitmap = vc.getImg();
                    img.setImageBitmap(bitmap);
                    buttonGet.setBackgroundColor(Color.GRAY);
                    service.setNew(false);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"not any new verify code.",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void addOnClickListenerToButtonSend() {
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "onClick: send verifycode to service");
                EditText txt = (EditText) findViewById(R.id.txtVerifyCode);
                VerifyCode vc = new VerifyCode();
                String result = vc.sendVerifyCode(txt.getText().toString());
                Toast.makeText(MainActivity.this,result, Toast.LENGTH_LONG).show();
            }
        });
    }

    private MonitorService getMonitorService() {
        if (monitorConnect.getMonitorService() == null) {
            connectService();
        }
        return monitorConnect.getMonitorService();
    }

    private void connectService()
    {
        Intent intent = new Intent(MainActivity.this, MonitorService.class);
        startService(intent);
        Log.i(this.getClass().getName(), "start service");
        bindService(intent, monitorConnect, BIND_AUTO_CREATE);
        Log.i(this.getClass().getName(), "bind service");
    }
    private void disconnectService()
    {
        Intent intent = new Intent(MainActivity.this, MonitorService.class);
        stopService(intent);
        Log.i(this.getClass().getName(), "stop service");
        unbindService(monitorConnect);
        Log.i(this.getClass().getName(), "unbind service");
    }
}
