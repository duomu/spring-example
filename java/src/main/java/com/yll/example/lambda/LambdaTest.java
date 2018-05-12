package com.yll.example.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author：linlin.yang
 * @date：2018/4/11 16:12
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 4, 2, 6, 1, 9);
        //实现降序排列
        //代替匿名内部类
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.intValue() - o1.intValue();
            }
        });

        Collections.sort(list, (o1, o2) -> o2.intValue() - o1.intValue());

        //引用方法
        Collections.sort(list, LambdaTest::descCompare);

        //迭代集合
        for (Integer val : list) {
            System.out.println(val);
        }

        list.forEach(val -> System.out.println(val));

        new Thread(() -> {System.out.println("test a sub thread");});
    }

    private static int descCompare(Integer o1, Integer o2) {
        return o2.intValue() - o1.intValue();
    }
}
