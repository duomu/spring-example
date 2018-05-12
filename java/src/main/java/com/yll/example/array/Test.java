package com.yll.example.array;

/**
 * @author：linlin.yang
 * @date：2018/4/10 21:23
 */
public class Test {
    public static void main(String[] args) {
        //一维数组
        int[] array;//声明
        int array1[];//声明
        int[] array2 = new int[10];//声明的同时实例化，元素默认值为0
        Integer[] array3 = new Integer[5];//声明的同时实例化，元素默认值为null
        int[] array4 = {1, 2, 3, 4, 5};//声明的同时实例化

        for (int v : array2) {
            System.out.println(v);
        }
        System.out.println("-------------");

        for (Integer v : array3) {
            System.out.println(v);
        }

        //二维数组
        int[][] darray;//声明
        int darray1[][];//声明
        int[][] darray2 = new int[2][3];//声明的同时实例化，元素默认值为0
        int[][] darray3 = {{}, {}};//声明的同时实例化

        Object[] objects = new Object[5];
        objects[0] = 1;
        objects[1] = "hello";
        objects[2] = 0.0;
        objects[3] = null;
        objects[4] = 10;
        for (Object obj : objects) {
            System.out.println(obj);
        }
    }
}
