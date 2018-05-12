package com.yll.example.enumt;

/**
 * @author：linlin.yang
 * @date：2018/4/10 21:06
 */
public class Test {
    public static void main(String[] args) {
        SimpleColor[] colors = SimpleColor.values();
        for (SimpleColor color : colors) {
            System.out.print(color+" ");
        }
        System.out.println();

        System.out.println(ComplexColor.from(1));
    }
}
