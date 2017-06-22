package com.tauren.common;

/**
 * @author AwesomeJim  E-MAIL:AwesomeJim@foxmail.com
 */
public class Network {

    private  static class NetworkHolder {
        private static final Network INSTANCE  = new Network();
    }

    private Network(){

    }

    public static Network getInstance() {
        return NetworkHolder.INSTANCE;
    }

}
