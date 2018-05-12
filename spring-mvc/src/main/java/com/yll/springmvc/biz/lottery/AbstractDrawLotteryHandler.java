package com.yll.springmvc.biz.lottery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * 模板模式
 * @author：linlin.yang
 * @date：2018/4/28 15:29
 */
public abstract class AbstractDrawLotteryHandler implements IDrawLotteryHandler<Integer> {

    public static final Logger logger = LoggerFactory.getLogger(AbstractDrawLotteryHandler.class);
    /**
     * 奖品
     */
    protected Map<Integer, Integer> lotterys;

    /**
     * 抽奖人数
     */
    protected int userCount;

    /**
     * 奖品总数
     */
    protected int initLotteryCount;

    /**
     * 已抽中的奖品数量
     */
    protected int hitCount = 0;

    /**
     * 未抽中的奖品数量
     */
    protected int notHitCount = 0;

    private static Random random;

    @Override
    public Integer drawLottery() {
        //1.随机抽取一个编号：随机打乱顺序，每次抽取第一个即可
        Integer drawNumber = generateDrawNumber();

        //2.判断当前抽奖人是否抽中奖品，若抽中，判断该奖品是否有剩余，若没有，则表示出现错误；若有剩余奖品，则将该奖品数量减1。
        return generateLottery(drawNumber);
    }

    /**
     * 两种抽奖算法的主要不同在于生成随机数的逻辑不同，所以写成抽象方法，让子类实现具体逻辑
     * @return
     */
    protected abstract Integer generateDrawNumber();

    public Integer generateLottery(Integer drawNumber) {
        Integer lotteryId = hitLottery(drawNumber);
        if (lotteryId != null) {
            hitCount++;
            Integer lotteryCount = lotterys.get(lotteryId);
            if (lotteryCount == null || lotteryCount.equals(0)) {
                logger.error("出现错误，抽中的奖品等级剩余0个");
                return null;
            }

            if (lotteryCount - 1 == 0) {
                lotterys.remove(lotteryId);
            } else {
                lotterys.put(lotteryId, lotteryCount - 1);
            }
        } else {
            notHitCount++;
        }

        if (!checkDataConsistence()) {
            logger.error("抽奖出错，数据不一致！");
            return null;
        }

        return lotteryId;
    }

    /**
     * 获取单例，双重校验锁——线程安全，只在第一次调用单例的时候做同步，性能较高
     * @return
     */
    public static Random getRandomSingelton() {
        if (random == null) {
            synchronized (DrawLotteryHighHandler.class) {
                if (random == null) {
                    random = new Random();
                }
            }
        }

        return random;
    }

    /**
     * 判断用户是否抽中奖品，若返回null表示未抽中奖品，否则表示抽中奖品。
     * @param x
     * @return
     */
    protected Integer hitLottery(int x) {
        if (x >= 1 && x <= 10) {
            return 1;
        } else if (x >= 11 && x <= 30) {
            return 2;
        } else if (x >= 31 && x <= 60) {
            return 3;
        } else if (x >= 61 && x <= 100) {
            return 4;
        } else {
            return null;
        }
    }

    /**
     * 校验数据一致性：奖品总数x，当抽取了y个奖品后，要剩余x-y个奖品
     * @return
     */
    private boolean checkDataConsistence() {
        return count(lotterys) == initLotteryCount - hitCount;
    }

    public static int count(Map<Integer, Integer> lotterys) {
        if (lotterys == null || lotterys.size() == 0) {
            return 0;
        }

        int count = 0;
        Iterator<Map.Entry<Integer, Integer>> it = lotterys.entrySet().iterator();
        while (it.hasNext()) {
            count += it.next().getValue();
        }

        return count;
    }


}
