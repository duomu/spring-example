package com.yll.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author：linlin.yang
 * @date：2018/3/8 17:03
 */
public class GetDiff {

    public static void main(String[] args) {
//        System.out.println(diff("abcde", "cfd"));
//        String[] array1 = {"a", "b", "c", "d", "e"};
//        String[] array2 = {"c", "f", "d"};
//        String[] diff = minus(array1, array2);
//        for (String s : diff) {
//            System.out.print(s + " ");
//        }

        List<String> words = getWordsSimple("i   am     very very      good");
        for (String w : words) {
            System.out.print(w + ",");
        }
    }

    public static String diff(String s, String t) {
        if (s == null || s.length() == 0) {
            return t;
        } else if (t == null || t.length() == 0) {
            return s;
        }

        String longString = null;
        String shortString = null;
        if (s.length() > t.length()) {
            longString = s;
            shortString = t;
        } else {
            longString = t;
            shortString = s;
        }

        StringBuffer stringBuffer = new StringBuffer();
        int i = 0, j = 0;
        while (i < longString.length() && j < shortString.length()) {
            if (longString.charAt(i) != shortString.charAt(j)) {
                stringBuffer.append(longString.charAt(i));
                i++;
            } else {
                i++;
                j++;
            }
        }

        while (i < longString.length()) {
            stringBuffer.append(longString.charAt(i));
            i++;
        }

        return stringBuffer.toString();
    }

    public static String diff2(String s, String t) {
        if (s == null || s.length() == 0) {
            return t;
        } else if (t == null || t.length() == 0) {
            return s;
        }

        String longString = null;
        String shortString = null;
        if (s.length() > t.length()) {
            longString = s;
            shortString = t;
        } else {
            longString = t;
            shortString = s;
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < longString.length(); i++) {
            for (int j = 0; j < shortString.length(); j++) {
                if (longString.charAt(i) != shortString.charAt(j)) {

                }
            }
        }
        int i = 0, j = 0;
        while (i < longString.length() && j < shortString.length()) {
            if (longString.charAt(i) != shortString.charAt(j)) {
                stringBuffer.append(longString.charAt(i));
                i++;
            } else {
                i++;
                j++;
            }
        }

        while (i < longString.length()) {
            stringBuffer.append(longString.charAt(i));
            i++;
        }

        return stringBuffer.toString();
    }

    //求两个数组的差集
    public static String[] minus(String[] arr1, String[] arr2) {
        LinkedList<String> list = new LinkedList<String>();
        LinkedList<String> history = new LinkedList<String>();
        String[] longerArr = arr1;
        String[] shorterArr = arr2;
        //找出较长的数组来减较短的数组
        if (arr1.length > arr2.length) {
            longerArr = arr2;
            shorterArr = arr1;
        }
        for (String str : longerArr) {
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        for (String str : shorterArr) {
            if (list.contains(str)) {
                history.add(str);
                list.remove(str);
            } else {
                if (!history.contains(str)) {
                    list.add(str);
                }
            }
        }

        String[] result = {};
        return list.toArray(result);
    }

    public static List<String> getWordsByRegex(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        String[] words = s.split("\\s");
        return Arrays.asList(words);
    }

    public static List<String> getWordsSimple(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        List<String> words = new ArrayList<String>();
        StringBuffer word = new StringBuffer();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                word.append(s.charAt(i));
            } else {
                if (!word.toString().matches("\\s*")) {
                    words.add(word.toString());
                }
                word.setLength(0);//清空字符串
            }
        }

        words.add(word.toString());

        return words;
    }
}
