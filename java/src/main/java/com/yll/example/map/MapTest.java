package com.yll.example.map;

import java.util.*;

/**
 * @author：linlin.yang
 * @date：2018/4/11 18:15
 */
public class MapTest {
    public static void main(String[] args) {
        System.out.println("-------HashMap---------");
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "user");
        map.put(null, null);
        map.put(2, null);
        map.forEach((key, value) -> System.out.println(key + "," + value));

        System.out.println("-------LinkedHashMap---------");
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1, "user");
        linkedHashMap.put(null, null);
        linkedHashMap.put(2, null);
        linkedHashMap.forEach((key, value) -> System.out.println(key + "," + value));

        System.out.println("-------TreeMap---------");
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "user");
//        treeMap.put(null, null);
        treeMap.put(2, null);
        treeMap.forEach((key, value) -> System.out.println(key + "," + value));

//        System.out.println("-------TreeSet---------");
//        TreeSet<Integer> treeSet = new TreeSet<>();
//        treeSet.add(null);
//        treeSet.add(1);
//        treeSet.forEach(item -> System.out.println(item));
        System.out.println("----------Hashtable-----------");
        Hashtable hashtable = new Hashtable();
        hashtable.put(1, ":uu");
//        hashtable.put(1, null);
//        hashtable.put(null, null);
        hashtable.forEach((k, v) -> System.out.println(k + "," + v));

        //HashMap的四种遍历方式
        Map<Integer, String> hashMap = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            hashMap.put(i, "item" + i);
        }

        long startTime = System.currentTimeMillis();
        traveserByKeySet(hashMap);
        long endTime = System.currentTimeMillis();
        System.out.println("for循环-keySet耗时：" + (endTime - startTime) + "ms");
        startTime = System.currentTimeMillis();
        traveserByEntrySet(hashMap);
        endTime = System.currentTimeMillis();
        System.out.println("for循环-entrySet耗时：" + (endTime - startTime) + "ms");
        startTime = System.currentTimeMillis();
        traveserByKeySetIter(hashMap);
        endTime = System.currentTimeMillis();
        System.out.println("迭代器-keySet耗时：" + (endTime - startTime) + "ms");
        startTime = System.currentTimeMillis();
        traveserByEntrySetIter(hashMap);
        endTime = System.currentTimeMillis();
        System.out.println("迭代器-entrySet耗时：" + (endTime - startTime) + "ms");
    }

    /**
     * for循环-keySet
     * @param map
     */
    public static void traveserByKeySet(Map<Integer, String> map) {
        for (Integer key : map.keySet()) {
//            System.out.println(key + "," + map.get(key));
            print(key + "," + map.get(key));
        }
    }

    /**
     * for循环-entrySet
     * @param map
     */
    public static void traveserByEntrySet(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + "," + entry.getValue());
            print(entry.getKey() + "," + entry.getValue());
        }
    }

    /**
     * 迭代器-keySet
     * @param map
     */
    public static void traveserByKeySetIter(Map<Integer, String> map) {
        Iterator<Integer> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            Integer key = iter.next();
//            System.out.println(key + "," + map.get(key));
            print(key + "," + map.get(key));
        }
    }

    /**
     * 迭代器-entrySet
     * @param map
     */
    public static void traveserByEntrySetIter(Map<Integer, String> map) {
        Iterator<Map.Entry<Integer, String>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, String> entry = iter.next();
//            System.out.println(entry.getKey() + "," + entry.getValue());
            print(entry.getKey() + "," + entry.getValue());
        }
    }

    private static void print(String s) {
        //some

    }

}
