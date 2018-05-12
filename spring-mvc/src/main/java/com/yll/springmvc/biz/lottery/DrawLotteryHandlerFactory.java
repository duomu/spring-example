package com.yll.springmvc.biz.lottery;

import java.util.Map;

/**
 * 工厂模式
 * @author：linlin.yang
 * @date：2018/4/28 17:25
 */
public class DrawLotteryHandlerFactory {

    public static IDrawLotteryHandler getHandler(Class clazz, Map<Integer, Integer> lotterys, final int initLotteryCount, final int userCount) {
        if (clazz.getName().contains("DrawLotteryHighHandler")) {
            return getHighHandler(lotterys, initLotteryCount, userCount);
        } else {
            return getLowHandler(lotterys, initLotteryCount, userCount);
        }
    }
    public static IDrawLotteryHandler getHighHandler(Map<Integer, Integer> lotterys, final int initLotteryCount, final int userCount) {
        return new DrawLotteryHighHandler(lotterys, initLotteryCount, userCount).buildDrawNumbers();
    }

    public static IDrawLotteryHandler getLowHandler(Map<Integer, Integer> lotterys, final int initLotteryCount, final int userCount) {
        return new DrawLotteryLowHandler(lotterys, initLotteryCount, userCount);
    }
}
