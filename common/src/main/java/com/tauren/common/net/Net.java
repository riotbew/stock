package com.tauren.common.net;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.ArrayList;
import java.util.Map;

public class Net {

    private HttpClient mClient;
    private Handler mHandler;
    ArrayList<RequestProcessor> requestProcessors = new ArrayList<>();

    private  static class NetworkHolder {
        private static final Net INSTANCE  = new Net();
    }

    private Net(){
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
                case Config.GET:
                    mClient.get(info);
                    break;
                case Config.POST:
                    mClient.post(info);
                    break;
                case Config.UPLOAD:
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

    public static Net getInstance() {
        return NetworkHolder.INSTANCE;
    }

    public void request(NetInfo info){
        Message msg = mHandler.obtainMessage();
        msg.obj = info;
        mHandler.sendMessage(msg);
    }

    public void get(String url, Map<String,Object> params, NetCallBack callBack, Class clasz) {
        request(new NetInfo(null,params,url, callBack, clasz, Config.GET));
    }

    public void get(String url, Map<String,Object> params, NetCallBack callBack) {
        request(new NetInfo(null,params,url, callBack, String.class, Config.GET));
    }


    public void post(String url, Map<String,Object> params, NetCallBack callBack, Class clasz) {
        request(new NetInfo(null,params,url, callBack, clasz, Config.POST));
    }


}
