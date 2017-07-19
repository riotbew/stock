package com.tauren.common.net;

/**
 * Created by Tauren on 17/7/18.
 */

public interface NetCallBack<T> {

    public void onResponse(T result);
    public void onFailure(int code, String result);
}
