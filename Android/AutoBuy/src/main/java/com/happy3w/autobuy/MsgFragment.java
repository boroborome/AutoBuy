package com.happy3w.autobuy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.happy3w.autobuy.srv.VerifyCode;


/**
 * 消息主页。
 */
public class MsgFragment extends Fragment {
    private View view;
    Button buttonStart;
    Button buttonStop;
    Button buttonGet;
    Button buttonSend;
    EditText txtVerifyCode;
    Switch switchServer;
    MonitorServiceConnection monitorConnect = new MonitorServiceConnection();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.msg, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buttonStart = (Button) view.findViewById(R.id.buttonStart);
        btnStart_OnClickListener();
        buttonStop = (Button) view.findViewById(R.id.buttonStop);
        btnStop_OnClickListener();
        buttonGet = (Button) view.findViewById(R.id.buttonGet);
        btnGet_OnClickListener();
        buttonSend = (Button) view.findViewById(R.id.buttonSend);
        btnSend_OnClickListener();
        txtVerifyCode = (EditText) view.findViewById(R.id.txtVerifyCode);
        txtVerifyCode_OnKeyListener();
        registerReceiver();
        switchServer=(Switch)view.findViewById(R.id.switchServer);
        switchServer_onCheckedChange();
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

    private void btnStop_OnClickListener() {
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "onClick: stoping service");
                disconnectService();
            }
        });
    }

    private void btnGet_OnClickListener() {
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "onClick: get verifycode from service", Toast.LENGTH_LONG).show();
                Log.i(this.getClass().getName(), "onClick: get verifycode from service");
                MonitorService service = getMonitorService();
                if (service == null) {
                    Toast.makeText(getActivity(), "onClick: get service Not connected", Toast.LENGTH_LONG).show();
                    return;
                }
                boolean isNew = service.isNew();
                if (isNew) {
                    txtVerifyCode.setText("");
                    service.setIsKnown(true);

                    Toast.makeText(getActivity(), "has new verify code.", Toast.LENGTH_LONG).show();
                    ImageView img = (ImageView) view.findViewById(R.id.imgVerifyCode);
                    VerifyCode vc = new VerifyCode();
                    Bitmap bitmap = vc.getImg();
                    img.setImageBitmap(bitmap);
                    buttonGet.setBackgroundColor(Color.GRAY);
                    service.setNew(false);
                } else {
                    Toast.makeText(getActivity(), "not any new verify code.", Toast.LENGTH_LONG).show();
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
        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
    }

    private MonitorService getMonitorService() {
        if (monitorConnect.getMonitorService() == null) {
            connectService();
        }
        return monitorConnect.getMonitorService();
    }

    private void connectService() {
        Intent intent = new Intent(getActivity(), MonitorService.class);
        getActivity().startService(intent);
        Log.i(this.getClass().getName(), "start service");
        getActivity().bindService(intent, monitorConnect, getActivity().BIND_AUTO_CREATE);
        Log.i(this.getClass().getName(), "bind service");
    }

    private void disconnectService() {
        Intent intent = new Intent(getActivity(), MonitorService.class);
        getActivity().stopService(intent);
        Log.i(this.getClass().getName(), "stop service");
        getActivity().unbindService(monitorConnect);
        Log.i(this.getClass().getName(), "unbind service");
    }

    private void registerReceiver() {
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter(MonitorService.ActionVerifyCodeNew);
        BroadcastReceiver mItemViewListClickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent){
                Log.i(this.getClass().getName(), "BroadcastReceiver on receive.");
                if (!getActivity().hasWindowFocus()) {
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
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);
    }
    private void switchServer_onCheckedChange()
    {
        switchServer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                   connectService();
                } else {
                   disconnectService();
                }
            }
        });
    }
}

