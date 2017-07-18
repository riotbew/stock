package com.tauren.common.net;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class Net {

    HttpClient mClient;
    private Handler mHandler;

    private  static class NetworkHolder {
        private static final Net INSTANCE  = new Net();
    }

    private Net(){
        mClient = new HttpClient();
        NetWorkThread thread = new NetWorkThread("NetWork-Thread");
        thread.start();
        mHandler = new Handler(thread.getLooper(), thread);
    }

    class NetWorkThread extends HandlerThread implements Handler.Callback{

        public NetWorkThread(String name) {
            super(name);
        }

        @Override
        public boolean handleMessage(Message msg) {
            if(msg == null || msg.obj == null){
                return false;
            }
            NetInfo info = (NetInfo) msg.obj;
            //通用处理
            return false;

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


}
