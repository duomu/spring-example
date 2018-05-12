package com.yll.springmvc.biz.lottery;

/**
 * Author：linlin.yang
 * Date：2018/4/27 19:34
 */
public interface IDrawLotteryHandler<T> {
    /**
     * 抽奖
     * @return
     */
    T drawLottery();
}
