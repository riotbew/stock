package com.tauren.common.net;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

public abstract class HttpClient {

    public abstract void post(NetInfo info);

    public abstract void get(NetInfo info);
}
