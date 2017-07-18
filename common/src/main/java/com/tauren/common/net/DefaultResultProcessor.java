package com.tauren.common.net;

/**
 * doNoThind
 */

public class DefaultResultProcessor extends ResultProcessor {

    private NetInfo netInfo;

    public DefaultResultProcessor(NetInfo netInfo) {
        this.netInfo = netInfo;
    }

    @Override
    public boolean errorProcess() {
        return false;
    }

    @Override
    public <Model> void successProcess(boolean responseFromCache) {

    }
}
