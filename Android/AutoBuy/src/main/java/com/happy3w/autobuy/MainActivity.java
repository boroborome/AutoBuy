package com.happy3w.autobuy;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements BuyFragment.OnBuyListener {
    private RadioGroup btmRadioGroup;
    private Fragment msgFragment;
    private  Fragment buyFragment;


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
        initView();
    }

    private void initView() {
        btmRadioGroup = (RadioGroup) findViewById(R.id.btmRadioGroup);
        msgFragment = new MsgFragment();
        buyFragment=new BuyFragment();
        onChecked_RadioGroup();
    }

    private void onChecked_RadioGroup() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, msgFragment)
                .commit();
        btmRadioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // TODO Auto-generated method stub
                        switch (checkedId) {
                            case R.id.rbMain: {
                                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, MainActivity.this.msgFragment)
                                        .commit();
                                break;
                            }
                            case R.id.rbBuy: {
                                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, MainActivity.this.buyFragment).commit();
                                break;
                            }
                            case R.id.rbTest: {
                                Fragment frg=new TestFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, frg).commit();
                                break;
                            }
                            default:
                                break;
                        }
                    }
                });
    }

    @Override
    public void onBuyCancel() {

    }

    @Override
    public void onBuyConfirm() {

    }
}
