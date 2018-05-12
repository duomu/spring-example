package com.yll.springmvc.biz.lottery;

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
public class DrawLotteryHandler implements IDrawLotteryHandler<Integer> {
    /**
     * 奖品，key为奖品等级，value为奖品个数
     */
    private Map<Integer, Integer> lotterys = new HashMap();

    /**
     * 存储已抽奖的随机编号
     */
    private Set<Integer> numbers = new HashSet<>();

    /**
     * 存储还未抽奖的随机编号
     */
    List<Integer> baseNumbers = new ArrayList<>();

    private static Random random;

    int hitCount = 0;
    int notHitCount = 0;

    /**
     * 获取单例，双重校验锁——线程安全，只在第一次调用单例的时候做同步，性能较高
     * @return
     */
    private static Random getRandom() {
        if (random == null) {
            synchronized (DrawLotteryHandler.class) {
                if (random == null) {
                    random = new Random();
                }
            }
        }

        return random;
    }

    @Override
    public Integer drawLottery() {
        //随机生成一个1000以内的正整数
        Random random = new Random();
//        random.ints(1, 1, 1001);
        Integer x = random.nextInt(100) + 1;

        if (numbers.contains(x)) {
            System.out.println("生成了一个重复的随机数：" + x);
        } else {
            System.out.println("生成了一个不重复的随机数：" + x);
            numbers.add(x);
        }

        return null;
    }


    public static void main(String[] args) {
        //抽奖流程
        //准备奖品
        //设置抽奖规则：给参与抽奖的人生成随机编号，每个编号可以映射到相应的奖品上（包括无奖）
        //抽奖人编号和奖品的映射规则：一等奖10个，二等奖20，三等奖30，四等奖40，抽奖人1000个，则编号x落在哪个范围就抽中哪个奖品
        //x属于[1,10],则抽中1个一等奖
        //x属于[11,30],则抽中1个二等奖
        //x属于[31,60],则抽中1个三等奖
        //x属于[61,100],则抽中1个四等奖
        //抽奖人每抽中一个奖品，就将该奖品的数量减1，当所有人都抽奖完毕之后，若剩余奖品数为0，则表示逻辑正确，数据是一致的。
        final int userCount = 1000;
        final int min = 1;
        final int max = userCount;


        DrawLotteryHandler t = new DrawLotteryHandler();
        //1.准备奖品
        Map<Integer, Integer> lotterys = t.initLottery();

        //2.初始化抽奖人编号（1~1000）
        List<Integer> drawNumbers = t.initDrawNumbers(min, max);

        int hitCount = 0;
        int notHitCount = 0;

        //3.1000个抽奖人依次抽奖
        for (int i = 1; i <= userCount; i++) {
            if (t.drawLottery(lotterys, drawNumbers)) {
                hitCount++;
            } else {
                notHitCount++;
            }
        }

        //4.验证数据一致性
        if (t.checkDrawLottery()) {
            System.out.println("所有奖品都已抽完，数据一致！共有"+hitCount+"个人抽中奖品");
        } else {
            System.out.println("还有奖品没有抽完，数据不一致！");
        }
    }

    private Map<Integer, Integer> initLottery() {
        Map<Integer, Integer> lotterys = new HashMap();
        for (int i = 1; i <= 4; i++) {
            lotterys.put(i, i * 10);
        }

        return lotterys;
    }

    private List<Integer> initDrawNumbers(int min, int max) {
        List<Integer> drawNumbers = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            drawNumbers.add(i);//编号x存储在链表的位置x-1上
        }

        return drawNumbers;
    }

    /**
     * 抽取奖品并更新奖品余量
     * @param drawNumbers
     */
    private boolean drawLottery(Map<Integer, Integer> lotterys, List<Integer> drawNumbers) {
        //1.随机抽取一个编号：随机打乱顺序，每次抽取第一个即可
        Integer x = getRandomNumber(drawNumbers);

        //2.判断当前抽奖人是否抽中奖品，若抽中，判断该奖品是否有剩余，若没有，则表示出现错误；若有剩余奖品，则将该奖品数量减1。
        return checkHitAndUpdateLottery(x);

    }

    private Integer getRandomNumber(List<Integer> drawNumbers) {
        Collections.shuffle(drawNumbers);
        Integer x = drawNumbers.get(0);
        drawNumbers.remove(0);
        return x;
    }

    /**
     * 判断是否抽中奖品，若抽中则更新奖品余量
     *
     * @return
     */
    private boolean checkHitAndUpdateLottery(Integer drawNumber) {
        Integer lotteryId = hitLottery(drawNumber);
        if (lotteryId != null) {
            hitCount++;
            Integer lotteryCount = lotterys.get(lotteryId);
            if (lotteryCount == null || lotteryCount.equals(0)) {
                System.out.println("出现错误，抽中的奖品等级剩余0个");
                return false;
            }

            if (lotteryCount - 1 == 0) {
                lotterys.remove(lotteryId);
            } else {
                lotterys.put(lotteryId, lotteryCount - 1);
            }

            System.out.println("抽中奖品！");
            return true;
        } else {
            notHitCount++;
            System.out.println("没有抽中奖品！");
            return false;
        }
    }

    private boolean checkDrawLottery() {
        if (lotterys.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断用户是否抽中奖品，若返回null表示
     * @param x
     * @return
     */
    private Integer hitLottery(int x) {
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
     * 不重复地获取指定范围内的随机整数，效率较低
     * @param min
     * @param max
     * @return
     */
    private Integer getDifferentRandomSimple(int min, int max) {
        //随机生成一个1000以内的正整数
        int count = 0;
        Integer x = null;
        do {
            if (x != null) {
                System.out.println("生成了一个重复的随机数：" + x);
            }

            x = this.getRandom(min, max);
            count++;
        } while (numbers.contains(x));

        System.out.println("经过" + count + "次，生成了一个不重复的随机数：" + x);
        numbers.add(x);

        return x;
    }

    /**
     * 不重复地获取指定范围内的随机整数，高效
     * @param min
     * @param max
     * @return
     */
    private Integer getDifferentRandom(int min, int max) {

        for (int i = min; i <= max; i++) {
            baseNumbers.add(i);//编号x存储在链表的位置x-1上
        }

        //随机打乱编号的顺序
        Collections.shuffle(baseNumbers);

        Integer x = baseNumbers.get(0);
        baseNumbers.remove(0);//将抽中的编号删除，以免抽中重复的编号

        return x;
    }

    /**
     * 获取指定范围的随机整数
     * @param min
     * @param max
     * @return
     */
    private Integer getRandom(int min, int max) {
        Random random = getRandom();
        Integer x = random.nextInt(max) % (max - min + 1) + min;//[min,max]-min——>[0,max-min],所以先随机生成0~max-min之间的正整数
        return x;
    }
}
