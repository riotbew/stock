package com.tauren.common.net;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CookieJar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Tauren on 17/7/19.
 */

public class OKClient extends HttpClient{
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("charset=utf-8");
    private OkHttpClient mClient;
    private OkHttpClient.Builder mClientBuilder = new OkHttpClient().newBuilder();
    private ICallBack clientCallBack;

    private OkHttpClient getClient() {
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

    @Override
    public void post(NetInfo info) {
        getClient().newCall(postPackageInfo(info)).enqueue(getCommonCallBack(info));
    }

    @Override
    public void get(NetInfo info) {
        Logger.i("Start request1:"+info.url);
        getClient().newCall(getPackageInfo(info)).enqueue(getCommonCallBack(info));
    }

    private synchronized Request postPackageInfo(NetInfo info) {
        if (info == null)
            return null;

        Map<String, String> headers = info.getHeaders();
        Map<String, Object> params = info.getParams();
        Request.Builder builder = new Request.Builder();
        builder.url(info.url);
        Set<String> keySet = headers.keySet();
        for (String key:keySet) {
            builder.addHeader(key, headers.get(key));
        }
        keySet = params.keySet();
        StringBuilder tempParams = new StringBuilder();
        int pos = 0;
        Object tmp;
        for (String key : keySet) {
            if (pos > 0) {
                tempParams.append("&");
            }
            tmp = params.get(key);
            try {
                if (tmp instanceof String)
                    tempParams.append(String.format("%s=%s", key, URLEncoder.encode((String) tmp, "utf-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            pos++;
        }
        builder.post(RequestBody.create(MEDIA_TYPE_JSON, tempParams.toString()));
        return builder.build();
    }

    private synchronized Request getPackageInfo(NetInfo info) {
        StringBuilder url = new StringBuilder(info.url);
        url.append("?");
        Map params = info.getParams();
        Set<String> keySet = params.keySet();
        for (String key: keySet) {
            url.append(key).append("=").append(params.get(key)).append("&");
        }
        url.deleteCharAt(url.length()-1);
        Request.Builder builder = new Request.Builder();
        builder.url(url.toString());
        return builder.build();
    }

    private Callback getCommonCallBack(final NetInfo info) {
        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                info.response = call.toString();
                if (clientCallBack == null) {
                    //dosomething
                } else {
                    clientCallBack.fail(info, call, e);
                }
                info.resultProcessor.errorProcess();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                info.response = response.body().string();
                if (clientCallBack == null) {
                    //dosomething
                } else {
                    clientCallBack.success(info, call, response);
                }
                info.resultProcessor.successProcess(false);
            }
        };
    }

    public void setClientCallBack(ICallBack callBack) {
        clientCallBack = callBack;
    }

    public interface ICallBack {
        void success(NetInfo info, Call call, Response response);
        void fail(NetInfo info, Call call, IOException e);
    }
}
