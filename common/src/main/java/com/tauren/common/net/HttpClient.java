package com.tauren.common.net;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

public class HttpClient {
    private OkHttpClient mClient;
    private OkHttpClient.Builder mClientBuilder = new OkHttpClient().newBuilder();

    public OkHttpClient getClient() {
        if (mClient == null) {
            synchronized(HttpClient.class) {
                if (mClient == null)
                    mClient = mClientBuilder.build();
            }
        }
        return mClient;
    }

    public void setCookieJar(CookieJar cookieJar) {
        if (mClientBuilder != null && cookieJar != null) {
            mClientBuilder.cookieJar(cookieJar);
            mClient = null;
        }

    }

    public void post() {

    }

    public void get() {

    }
}
