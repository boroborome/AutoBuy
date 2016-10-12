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
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.happy3w.autobuy.model.Product;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.srv.OrderManager;
import com.happy3w.autobuy.srv.ProductManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuyFragment extends Fragment {
    private View view;
    private Button btnConfirm;
    private Button btnCancel;
    private OnBuyListener buyListener;
    private ListView listView;
    private String selectedProduct;
    private ProductManager productManager = new ProductManager();

    public BuyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
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
        initListView();
        listView_OnItemClick();
    }

    private void initListView() {
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getProducts(),
                R.layout.buy_pro_item, new String[]{Product.CODE, Product.NAME, Product.INTEREST},
                new int[]{R.id.procode, R.id.proname, R.id.prointerest});
        // 设置适配器
        listView.setAdapter(adapter);
    }

    private List<Map<String, String>> getProducts() {
        List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
        Product[] products = productManager.getAllProducts();
        for (Product product : products) {
            Map<String, String> item = new HashMap<String, String>();
            item.put(product.CODE, product.getCode());
            item.put(product.NAME, product.getName());
            item.put(product.INTEREST, String.valueOf(product.getInterest()));
            datas.add(item);
        }
        return datas;
    }

    private void listView_OnItemClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String> item= (Map<String, String>) parent.getItemAtPosition(position);
                        selectedProduct =item.get(Product.CODE);
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
                                              OrderManager manager = new OrderManager();
                                             String result= manager.commit(getOrder());
                                              Toast.makeText(getActivity(),result, Toast.LENGTH_LONG).show();
                                             // buyListener.onBuyConfirm();
                                          }
                                      }
        );
    }

    private PurchaseOrder getOrder() {
        PurchaseOrder order = new PurchaseOrder();
        double amount = Double.valueOf(((EditText) view.findViewById(R.id.txtAmount)).getText().toString());
        order.setAmount(amount);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateText = ((EditText) view.findViewById(R.id.txtDate)).getText().toString();
        Date date = null;
        try {
            date = format.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setBuytime(date);
        order.setProduct(selectedProduct);
        return order;
    }

    public interface OnBuyListener {
        public void onBuyCancel();

        public void onBuyConfirm();
    }
}
