package com.yll.util;

import com.yll.util.GoodUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author：linlin.yang
 * @date：2018/3/23 10:58
 */
public class TestCase<E> {
    /**
     * 一组测试用例
     */
    List<Case<E>> cases;
    /**
     * 测试用例实际输出结果，key是测试用例的id
     */
    Map<Integer, E> result;
    List<E> input;
    List<E> expectOutput;

    //测试单个测试用例

    //测试一组测试用例


    <T> void printResult(List<T> input,List<T> expectOutput,List<T> yourOutput) {
        boolean result = checkPass(input, expectOutput);
        if (result) {
            printPassInfo();
        } else {
            printNotPassInfo(input, expectOutput, yourOutput);
        }
    }

    <T> boolean checkPass(List<T> input,List<T> expectOutput) {
        List<T> yourOutput = func(input);
        return checkEquals(expectOutput, yourOutput);
    }

    <T> List<T> func(List<T> input){
        List<T> output = new ArrayList<T>();

        return output;
    }

    <T> void printPassInfo() {
        System.out.println("pass !");
    }

    <T> void printNotPassInfo(List<T> input, List<T> expectOutput, List<T> yourOutput) {
        System.out.println("not pass !");
        System.out.println("测试用例：" + GoodUtil.toString(input));
        System.out.println("期望结果：" + GoodUtil.toString(expectOutput));
        System.out.println("你的结果：" + GoodUtil.toString(yourOutput));

    }


    <T> boolean checkEquals(List<T> list1, List<T> list2) {
        if (list1 == null && list2 == null) {
            return true;
        } else if (list1 == null && list2 != null){
            return false;
        } else if (list1 != null && list2 == null) {
            return false;
        }

        if (list1.size() != list2.size()) {
            return false;
        }

        boolean result = true;
        for (T val1 : list1) {
            for (T val2 : list2) {
                if (!val1.equals(val2)) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    class Case<T> {
        Integer id;
        T input;
        T expectOutput;
        public Case() {

        }

        public Case(Integer id, T input, T expectOutput) {
            this.id = id;
            this.input = input;
            this.expectOutput = expectOutput;
        }
    }
}
