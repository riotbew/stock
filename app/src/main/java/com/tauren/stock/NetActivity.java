package com.tauren.stock;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.tauren.common.BaseActivity;
import com.tauren.common.net.NetCallBack;
import com.tauren.common.net.NetWork;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tauren on 17/7/18.
 */

public class NetActivity extends BaseActivity {

    @BindView(R.id.net_result)
    TextView tv_result;

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
                NetWork.getInstance().get("http://q.10jqka.com.cn/index/index/board/all/field/zdf/order/desc/page/1/ajax/1/", null, new NetCallBack<String>() {
                    @Override
                    public void onResponse(String result) {
                        tv_result.setText(result);
                        Document doc = Jsoup.parse(result);
//                        doc.body().getElementsByTag()

                    }

                    @Override
                    public void onFailure(int code, String result) {
                        tv_result.setText(result);
                    }
                });
                break;
            case R.id.net_result:
                break;
        }
    }

}
