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
public class DrawLotteryHighHandler extends AbstractDrawLotteryHandler {
//    public static final Logger logger = LoggerFactory.getLogger(DrawLotteryHighHandler.class);

    /**
     * 存储还未抽奖的随机编号
     */
    private List<Integer> toDrawNumbers;

    public DrawLotteryHighHandler(Map<Integer, Integer> lotterys, int initLotteryCount, int userCount) {
        this.lotterys = lotterys;
        this.initLotteryCount = initLotteryCount;
        this.userCount = userCount;
    }

    public DrawLotteryHighHandler buildDrawNumbers() {
        initDrawNumbers(1, userCount);
        return this;
    }

    private void initDrawNumbers(int min, int max) {
        this.toDrawNumbers = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            toDrawNumbers.add(i);
        }
    }

    @Override
    protected Integer generateDrawNumber() {
        Collections.shuffle(toDrawNumbers);
        Integer x = toDrawNumbers.get(0);
        toDrawNumbers.remove(0);
        return x;
    }

//    @Override
//    public Integer drawLottery() {
//        //1.随机抽取一个编号：随机打乱顺序，每次抽取第一个即可
//        Integer drawNumber = generateDrawNumber(toDrawNumbers);
//
//        //2.判断当前抽奖人是否抽中奖品，若抽中，判断该奖品是否有剩余，若没有，则表示出现错误；若有剩余奖品，则将该奖品数量减1。
//        return hitLottery(drawNumber);
//    }
//
//    /**
//     * 获取一个不重复的随机编号，效率较高
//     * @param drawNumbers
//     * @return
//     */
//    private Integer generateDrawNumber(List<Integer> drawNumbers) {
//        Collections.shuffle(drawNumbers);
//        Integer x = drawNumbers.get(0);
//        drawNumbers.remove(0);
//        return x;
//    }
}
