package com.happy3w.autobuy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonStart;
    Button buttonStop;
    Button buttonGet;
    Button buttonSend;
    EditText txtVerifyCode;
    MonitorServiceConnection monitorConnect = new MonitorServiceConnection();

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
        btnStart_OnClickListener();
        buttonStop = (Button) findViewById(R.id.buttonStop);
        btnStop_OnClickListener();
        buttonGet = (Button) findViewById(R.id.buttonGet);
        btnGet_OnClickListener();
        buttonSend = (Button) findViewById(R.id.buttonSend);
        btnSend_OnClickListener();
        txtVerifyCode = (EditText) findViewById(R.id.txtVerifyCode);
        txtVerifyCode_OnKeyListener();
        registerReceiver();
    }

    private void btnStart_OnClickListener() {
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "onClick: starting service");
                connectService();
            }
        });
    }
    private void btnStop_OnClickListener()
    {
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "onClick: stoping service");
                disconnectService();
            }
        });
    }
    private void registerReceiver()
    {
        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(MainActivity.class.getName(), "BroadcastReceiver on receive.");
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
    private void btnGet_OnClickListener() {
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "onClick: get verifycode from service", Toast.LENGTH_LONG).show();
                Log.i(this.getClass().getName(), "onClick: get verifycode from service");
                MonitorService service = getMonitorService();
                if (service == null) {
                    Toast.makeText(MainActivity.this, "onClick: get service Not connected", Toast.LENGTH_LONG).show();
                    return;
                }
                boolean isNew = service.isNew();
                if (isNew) {
                    txtVerifyCode.setText("");
                    service.setIsKnown(true);

                    Toast.makeText(MainActivity.this, "has new verify code.", Toast.LENGTH_LONG).show();
                    ImageView img = (ImageView) findViewById(R.id.imgVerifyCode);
                    VerifyCode vc = new VerifyCode();
                    Bitmap bitmap = vc.getImg();
                    img.setImageBitmap(bitmap);
                    buttonGet.setBackgroundColor(Color.GRAY);
                    service.setNew(false);
                } else {
                    Toast.makeText(MainActivity.this, "not any new verify code.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void btnSend_OnClickListener() {
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerifyCode();
            }
        });
    }

    private void txtVerifyCode_OnKeyListener() {
        txtVerifyCode.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_ENTER == keyCode) {
                    sendVerifyCode();
                    return true;
                }
                return false;
            }
        });
    }

    private void sendVerifyCode() {
        Log.i(this.getClass().getName(), "onClick: send verifycode to service");
        VerifyCode vc = new VerifyCode();
        String result = vc.sendVerifyCode(txtVerifyCode.getText().toString());
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
    }

    private MonitorService getMonitorService() {
        if (monitorConnect.getMonitorService() == null) {
            connectService();
        }
        return monitorConnect.getMonitorService();
    }

    private void connectService() {
        Intent intent = new Intent(MainActivity.this, MonitorService.class);
        startService(intent);
        Log.i(this.getClass().getName(), "start service");
        bindService(intent, monitorConnect, BIND_AUTO_CREATE);
        Log.i(this.getClass().getName(), "bind service");
    }

    private void disconnectService() {
        Intent intent = new Intent(MainActivity.this, MonitorService.class);
        stopService(intent);
        Log.i(this.getClass().getName(), "stop service");
        unbindService(monitorConnect);
        Log.i(this.getClass().getName(), "unbind service");
    }
}
