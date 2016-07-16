package com.happy3w.autobuy;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.srv.OrderManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuyFragment extends Fragment {
    private View view;
    private Button btnConfirm;
    private Button btnCancel;
    private OnBuyListener buyListener;
    private ListView listView;
    private String product;

    public BuyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        buyListener = (OnBuyListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.buy, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnCancel_OnClick();
        btnConfirm = (Button) view.findViewById(R.id.btnConfirm);
        btnConfirm_OnClick();
        listView = (ListView) view.findViewById(R.id.listView);
        listView_OnItemClick();
    }

    private void listView_OnItemClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                product = (String) parent.getItemAtPosition(position);
            }
        });
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                product = ((EditText) parent.getItemAtPosition(position)).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                product = null;
            }
        });
    }

    private void btnCancel_OnClick() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyListener.onBuyCancel();
            }
        });
    }


    private void btnConfirm_OnClick() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              OrderManager manager  =new OrderManager();
                                              manager.commit(getOrder());
                                              buyListener.onBuyConfirm();
                                          }
                                      }
        );
    }

    private PurchaseOrder getOrder() {
        PurchaseOrder order = new PurchaseOrder();
        double amount = Double.valueOf(((EditText) view.findViewById(R.id.txtAmount)).getText().toString());
        order.setAmount(amount);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateText = ((EditText) view.findViewById(R.id.txtDate)).getText().toString();
        Date date = null;
        try {
            date = format.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setBuytime(date);
        order.setProduct(product);
       return order;
    }

    public interface OnBuyListener {
        public void onBuyCancel();

        public void onBuyConfirm();
    }
}
