package com.tauren.stock;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tauren.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author AwesomeJim  E-MAIL:AwesomeJim@foxmail.com
 */
public class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.okhttp_demo,R.id.action_demo,R.id.scheme_demo,R.id.webview_demo,R.id.net_demo})
    public void clicked(View view) {
        int id = view.getId();
        Intent intent;
        switch (id) {
            case R.id.okhttp_demo:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.action_demo:
                intent = new Intent();
//                intent.addCategory("com.tauren.stock.category.1a");
//                intent.addCategory("com.tauren.stock.category.2b");
                intent.setAction("com.tauren.stock.action");
                startActivity(intent);
                break;
            case R.id.scheme_demo:
                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("demo://jim-qiu/main?id=1001"));
                startActivity(intent);
                break;
            case R.id.webview_demo:
                intent = new Intent(this, WebActivity.class);
                intent.putExtra(WebActivity.EXTRA_URL,"https://riot-qiu.coding.me");
                startActivity(intent);
                break;
            case R.id.net_demo:
                startActivity(new Intent(this, NetActivity.class));
        }
    }

}
