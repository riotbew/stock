package com.tauren.stock.constant;

/**
 * @author AwesomeJim  E-MAIL:AwesomeJim@foxmail.com
 */
public class BTC38URL {

    public final static String day = "http://www.btc38.com/trade/getTradeDayLine.php?mk_type=CNY&coinname=";
    public final static String hour = "http://www.btc38.com/trade/getTradeTimeLine.php?mk_type=CNY&coinname=";
    public final static String min_5 = "http://www.btc38.com/trade/getTrade5minLine.php?mk_type=CNY&coinname=";
    public final static String trades = "http://api.btc38.com/v1/ticker.php?mk_type=cny&c=";    //返回最新报价
    public final static String depth = "http://api.btc38.com/v1/depth.php?mk_type=cny&c=";    //返回当前市场深度（委托挂单），其中 asks 是委卖单, bids 是委买单。返回30条。
    public final static String history = "http://api.btc38.com/v1/trades.php?mk_type=cny&c=";    //返回系统支持的历史成交记录，默认返回最新30条。tid=100 指定返回条数 最多500条

}
