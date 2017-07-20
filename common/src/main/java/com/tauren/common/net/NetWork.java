package com.tauren.common.net;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.orhanobut.logger.Logger;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Map;

public class NetWork {

    private HttpClient mClient;
    private Handler mHandler;
    private ArrayList<RequestProcessor> requestProcessors = new ArrayList<>();

    private  static class NetworkHolder {
        private static final NetWork INSTANCE  = new NetWork();
    }

    private NetWork(){
        mClient = new OKClient();
        NetWorkThread thread = new NetWorkThread("NetWork-Thread");
        thread.start();
        mHandler = new Handler(thread.getLooper(), thread);
    }

    public void setClient(HttpClient client) {
        mClient = client;
    }

    private class NetWorkThread extends HandlerThread implements Handler.Callback{

        NetWorkThread(String name) {
            super(name);
        }

        @Override
        public boolean handleMessage(Message msg) {
            if(msg == null || msg.obj == null){
                return false;
            }
            NetInfo info = (NetInfo) msg.obj;
            //通用处理
            for (RequestProcessor processor:requestProcessors) {
                processor.process(info);
            }
            //请求
            switch (info.requestType) {
                case NetConfig.GET:
                    mClient.get(info);
                    break;
                case NetConfig.POST:
                    mClient.post(info);
                    break;
                case NetConfig.UPLOAD:
                    break;
            }
            return true;

        }
    }

    public void addRequestProcessor(RequestProcessor processor) {
        if (processor != null) {
            requestProcessors.add(processor);
        }
    }

    public static NetWork getInstance() {
        return NetworkHolder.INSTANCE;
    }

    public void request(NetInfo info){
        Message msg = mHandler.obtainMessage();
        msg.obj = info;
        mHandler.sendMessage(msg);
    }

    public void get(String url, Map<String,Object> params, NetCallBack callBack, Class clasz) {
        request(new NetInfo(null,params,url, callBack, clasz, NetConfig.GET));
    }

    public void get(String url, Map<String,Object> params, NetCallBack callBack) {
        request(new NetInfo(null,params,url, callBack, String.class, NetConfig.GET));
    }


    public void post(String url, Map<String,Object> params, NetCallBack callBack, Class clasz) {
        request(new NetInfo(null,params,url, callBack, clasz, NetConfig.POST));
    }


}
