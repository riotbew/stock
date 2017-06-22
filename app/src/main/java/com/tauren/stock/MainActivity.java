package com.tauren.stock;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tauren.common.BaseActivity;
import com.tauren.common.BindUtils;
import com.tauren.common.BindView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    private static final String TAG = "qqivjn";

    @BindView(id = R.id.demo_btn_request, click = true)
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindUtils.initBindView(this);
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(new Request.Builder().url("http://www.cnblogs.com/Fndroid/p/5354644.html").build());
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (tv != null) {
                    Log.i(TAG, "tv is not null " + response.toString());
                    Log.i(TAG, response.body().string());
                } else {
                    Log.i(TAG, "tv is null");
                }
            }
        });
    }
}
