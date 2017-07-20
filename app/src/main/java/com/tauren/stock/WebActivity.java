package com.tauren.stock;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends FragmentActivity {

    public static final String EXTRA_URL = "url";
    public static final String EXTRA_HEAD = "head";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_PARAMS = "params";

    protected WebView mWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hybrid);
        initView();
    }

    private void initView() {
        mWebView = (WebView)findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //这里可以添加应用内scheme协议跳转
                //解析scheme
                Uri uri = Uri.parse(url);
                if (url.indexOf("http") != -1) {
                    try {
                        view.loadUrl(url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                // 打电话
                else if (url.indexOf("tel://") != -1) {
                    final String number = url.substring("tel://".length());
//                    NetConfig.callPhoneByNumber(h5Activity, number);
                    return true;
                } else if (url.indexOf("tel:") != -1) {
                    final String number = url.substring("tel:".length());
//                    NetConfig.callPhoneByNumber(h5Activity, number);
                    return true;
                } else if (uri.getHost().equals("jim-qiu")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    return true;
                }
                // 其他跳转方式
                else {
                    view.loadUrl(url);
                    //如果不需要其他对点击链接事件的处理返回true，否则返回false
                    return false;
                }
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            final String url = intent.getStringExtra(EXTRA_URL);
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl(url);
                }
            });
        }
    }


}
