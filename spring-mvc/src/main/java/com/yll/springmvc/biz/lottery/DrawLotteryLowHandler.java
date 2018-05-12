package com.yll.springmvc.biz.lottery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 有等级的抽奖活动，如一等10个、二等20个、三等30个、四等40个，抽奖人数1000个，保持每个人的中奖率相等（10%）
 * 基本思想：由于一等奖、二等奖、三等奖、四等奖分别有10个、20个、30个、40个，总共100个奖品，抽奖人有1000个，
 * 为保证每个人中奖概率都是1/10，给每个参与抽奖的人随机生成一个1~1000之间的整数编号x，规定随机编号x落在某个区间，则抽中对应的奖项，
 * 只要保证生成随机编号的过程是随机的，那么每个人抽中奖的概率就是相同的。
 * 若x落在1~10，则抽中一等奖
 * 若x落在11~30，则抽中二等奖
 * 若x落在31~60，则抽中三等奖
 * 若x落在61~100，则抽中四等
 * 若x落在101~1000，则未抽中奖品
 *
 * 要保证经过1000次抽奖，所有奖品都被抽取完了。
 * @author：linlin.yang
 * @date：2018/4/27 19:25
 */
public class DrawLotteryLowHandler extends AbstractDrawLotteryHandler{
//    public static final Logger logger = LoggerFactory.getLogger(DrawLotteryLowHandler.class);

    /**
     * 存储已抽奖的随机编号
     */
    private List<Integer> drawedNumbers;

    public DrawLotteryLowHandler(Map<Integer, Integer> lotterys, int initLotteryCount, int userCount) {
        this.lotterys = lotterys;
        this.initLotteryCount = initLotteryCount;
        this.userCount = userCount;
        this.drawedNumbers = new ArrayList<>();
    }

    @Override
    protected Integer generateDrawNumber() {
        //随机生成一个1000以内的正整数
        int count = 0;
        Integer x = null;
        do {
            if (x != null) {
                logger.info("生成了一个重复的随机数：" + x);
            }

            x = this.getRandom(1, userCount);
            count++;
        } while (drawedNumbers.contains(x));

        logger.info("经过" + count + "次，生成了一个不重复的随机数：" + x);
        drawedNumbers.add(x);

        return x;
    }

//    @Override
//    public Integer drawLottery() {
//        //1.随机抽取一个编号：随机打乱顺序，每次抽取第一个即可
//        Integer drawNumber = generateDrawNumber(drawedNumbers, 1, userCount);
//
//        //2.判断当前抽奖人是否抽中奖品，若抽中，判断该奖品是否有剩余，若没有，则表示出现错误；若有剩余奖品，则将该奖品数量减1。
//        return hitLottery(drawNumber);
//    }
//
//    /**
//     * 获取一个不重复的随机编号，效率较低
//     * @param min
//     * @param max
//     * @return
//     */
//    private Integer generateDrawNumber(List<Integer> drawedNumbers, int min, int max) {
//        //随机生成一个1000以内的正整数
//        int count = 0;
//        Integer x = null;
//        do {
//            if (x != null) {
//                System.out.println("生成了一个重复的随机数：" + x);
//            }
//
//            x = this.getRandom(min, max);
//            count++;
//        } while (drawedNumbers.contains(x));
//
//        System.out.println("经过" + count + "次，生成了一个不重复的随机数：" + x);
//        drawedNumbers.add(x);
//
//        return x;
//    }

    /**
     * 获取指定范围的随机整数
     * @param min
     * @param max
     * @return
     */
    private Integer getRandom(int min, int max) {
        Random random = getRandomSingelton();
        Integer x = random.nextInt(max) % (max - min + 1) + min;//[min,max]-min——>[0,max-min],所以先随机生成0~max-min之间的正整数
        return x;
    }

}
