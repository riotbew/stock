package com.tauren.common.net;

/**
 * Created by Tauren on 17/7/18.
 */

public interface HCallBack<T> {

    public void onResponse(T result);
    public void onFailure(T result);
}
