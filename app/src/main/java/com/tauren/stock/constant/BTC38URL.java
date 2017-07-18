package com.tauren.stock.constant;

/**
 * @author AwesomeJim  E-MAIL:AwesomeJim@foxmail.com
 */
public class BTC38URL {

    //www
    public final static String getTradeDayLine = "trade/getTradeDayLine.php?mk_type=CNY&coinname=";
    public final static String getTradeTimeLine = "trade/getTradeTimeLine.php?mk_type=CNY&coinname=";
    public final static String getTrade5minLine = "trade/getTrade5minLine.php?mk_type=CNY&coinname=";

    //api
    public final static String ticker = "v1/ticker.php?mk_type=cny&c=";    //返回最新报价
    public final static String depth = "v1/depth.php?mk_type=cny&c=";    //返回当前市场深度（委托挂单），其中 asks 是委卖单, bids 是委买单。返回30条。
    public final static String history = "v1/trades.php";    //mk_type=cny&c= 返回系统支持的历史成交记录，默认返回最新30条。tid=100 指定返回条数 最多500条

    public final static String api_host = "http://api.btc38.com/";
    public final static String www_host = "http://www.btc38.com/";


}
