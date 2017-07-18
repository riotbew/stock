package com.tauren.stock;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tauren.common.BaseActivity;
import com.tauren.stock.constant.BTC38URL;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.demo_btn_request)
    Button btn_request;
    @BindView(R.id.demo_et_input)
    EditText et_input;
    @BindView(R.id.demo_tv_result)
    TextView tv_result;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = intent.getData();
            if (uri != null) {
                String host = uri.getHost();
                String dataString = intent.getDataString();
                String id = uri.getQueryParameter("id");
                String path = uri.getPath();
                String path1 = uri.getEncodedPath();
                String queryString = uri.getQuery();
                Log.d("Jim", "host:"+host);
                Log.d("Jim", "dataString:" + dataString);
                Log.d("Jim", "id:" + id);
                Log.d("Jim", "path:" + path);
                Log.d("Jim", "path1:" + path1);
                Log.d("Jim", "queryString:" + queryString);
            }
        }
    }

    @OnClick(R.id.demo_btn_request)
    public void request() {
        String url = BTC38URL.api_host+BTC38URL.history+"?mk_type=btc&c=med";
        if (!et_input.getText().toString().equals("")) {
            url = et_input.getText().toString();
        }
        final Call _call = client.newCall(new Request.Builder().url(url).build());
        _call.enqueue(new Callback() {

            @Override
            public void onFailure(final Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_result.setText(call.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            tv_result.setText(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @OnClick({R.id.demo_btn_thread})
    public void click(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.demo_btn_thread:
                tv_result.setText(new StringBuilder("Thread.activeCount：").append(Thread.activeCount()).toString());
                break;
            default:
                break;
        }
    }
}
