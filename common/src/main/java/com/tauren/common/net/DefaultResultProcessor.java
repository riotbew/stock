package com.tauren.common.net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.orhanobut.logger.Logger;

/**
 * doNoThind
 */

public class DefaultResultProcessor extends ResultProcessor {

    private NetInfo netInfo;

    private Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            handleCallBack();
            return false;
        }
    });

    public DefaultResultProcessor(NetInfo netInfo) {
        this.netInfo = netInfo;
    }

    @Override
    public boolean errorProcess() {
        Logger.i("err request:"+netInfo.url);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            handleCallBack();
        } else {
            mHandler.sendEmptyMessage(-1);
        }
        return false;
    }

    @Override
    public void successProcess(boolean responseFromCache) {
        Logger.i("err request:"+netInfo.url);

        if (Looper.myLooper() == Looper.getMainLooper()) {
            handleCallBack();
        } else {
            mHandler.sendEmptyMessage(-1);
        }
    }

    private <Model>  void handleCallBack() {
        Class<Model> resultModel = netInfo.resultModel;
        try {
            if (resultModel == String.class) {
                netInfo.callBack.onResponse(netInfo.response);
            } else {
                Model result = JSON.parseObject(netInfo.response, resultModel);
                netInfo.callBack.onResponse(result);
            }
            Logger.i("request success: "+netInfo.url+"\n"+netInfo.response);
        } catch (Exception e) {
            netInfo.callBack.onFailure(0, e.toString());
        }
    }
}
