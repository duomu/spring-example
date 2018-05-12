package com.yll.springmvc.biz.lottery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：linlin.yang
 * @date：2018/4/28 15:58
 */
public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        int scale = 500000;
        long startTime = System.currentTimeMillis();
        testDrawLotteryHandler(DrawLotteryHighHandler.class, scale);
        long endTime = System.currentTimeMillis();
        System.out.println("DrawLotteryHighHandler spend time：" + (endTime - startTime) + "ms");


        startTime = System.currentTimeMillis();
        testDrawLotteryHandler(DrawLotteryLowHandler.class, scale);
        endTime = System.currentTimeMillis();
        System.out.println("DrawLotteryLowHandler spend time：" + (endTime - startTime) + "ms");

    }

    private static void testDrawLotteryHandler(Class clazz, int scale) {
        //获取抽奖工具
        IDrawLotteryHandler drawLotteryHandler = getDrawLotteryHandler(clazz, scale);

        //测试抽奖
        batchDraw(drawLotteryHandler, scale);
    }

    public static IDrawLotteryHandler getDrawLotteryHandler(Class clazz, int scale) {
        //1.准备奖品：key为奖品等级，value为奖品个数
        Map<Integer, Integer> lotterys = new HashMap<>();
        for (int i = 1; i <= 4; i++) {
            lotterys.put(i, i * 10);
        }

        //2.准备抽奖人信息，1000个人参与抽奖
        final int initLotteryCount = DrawLotteryHighHandler.count(lotterys);
        final int userCount = scale;

        //3.实例化抽奖工具
        IDrawLotteryHandler drawLotteryHandler = DrawLotteryHandlerFactory.getHandler(clazz, lotterys, initLotteryCount, userCount);

        return drawLotteryHandler;
    }

    public static void batchDraw(IDrawLotteryHandler handler, int drawCount) {
        for (int i = 1; i <= drawCount; i++) {
            //抽奖
            Integer lotteryId = (Integer) handler.drawLottery();

            //输出抽奖结果
            printResult(lotteryId);
        }

    }

    private static void printResult(Integer lotteryId) {
        if (lotteryId == null) {
            logger.info("没有抽中奖品");
        } else {
            logger.info("抽中奖品，lotteryId=" + lotteryId);
        }
    }
}
