package com.tauren.common.net;

/**
 * Created by Tauren on 17/7/18.
 */

public abstract class ResultProcessor {

    public abstract boolean errorProcess();

    public abstract <Model> void successProcess(boolean responseFromCache);

}
