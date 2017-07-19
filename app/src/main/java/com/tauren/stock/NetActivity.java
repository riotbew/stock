package com.tauren.stock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.tauren.common.BaseActivity;
import com.tauren.common.net.Net;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tauren on 17/7/18.
 */

public class NetActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.net_request,R.id.net_result})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.net_request:
                Net.getInstance();
                break;
            case R.id.net_result:
                break;
        }
    }

}
