package com.tauren.common.net;

import android.text.TextUtils;

import com.tauren.common.utils.MD5Coder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class NetInfo<T> {
    private Map<String, String> headers;
    private Map<String, Object> params;
    public String url;
    public ResultProcessor resultProcessor;
    public NetCallBack<T> callBack;
    public String response;
    public Class<T> resultModel;
    public String requestKey;
    public int requestType;
    public boolean enableCache = false;

    public NetInfo(Map<String, String> headers, Map<String, Object> params, String url, NetCallBack<T> callBack, Class resultModel, int requestType) {
        this.headers = headers;
        this.params = params;
        this.url = url;
        this.callBack = callBack;
        this.resultModel = resultModel;
        this.requestType = requestType;
        this.requestKey = getRequestKey();
        this.resultProcessor = getDefaultProcessor();
    }

    private String getRequestKey() {
        if (TextUtils.isEmpty(requestKey)) {
            List<String> paramList = new ArrayList<>();
            if (params != null) {
                Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
                Map.Entry<String, Object> entry;
                while (it.hasNext()) {
                    entry = it.next();
                    if (!(entry.getValue() instanceof File)) {
                        paramList.add(entry.getKey() + "=" + entry.getValue());
                    }
                }
            }
            Collections.sort(paramList);
            StringBuilder builder = new StringBuilder();
            builder.append(url);
            if (paramList.size() > 0) {
                for (int i = 0; i < paramList.size(); i++) {
                    builder.append(paramList.get(i));
                }
            }
            requestKey = MD5Coder.md5(builder.toString());
        }
        return requestKey;
    }

    private ResultProcessor getDefaultProcessor() {
        return new DefaultResultProcessor(this);
    }

    public void setResultProcessor(ResultProcessor processor) {
        this.resultProcessor = processor;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
