package com.yll.example.enumt;

import java.util.HashMap;
import java.util.Map;

/**
 * Author：linlin.yang
 * Date：2018/4/10 21:09
 */
public enum ComplexColor {
    RED("红色", 1),
    BLACK("黑色", 2),
    GREEN("绿色", 3),
    YELLOW("黄色", 4),
    WHITE("白色", 5);

    private String name;
    private int code;

    private static HashMap<Integer, ComplexColor> map = new HashMap<Integer, ComplexColor>();

    static {
        for (ComplexColor color : values()) {
            map.put(color.code, color);
        }
    }

    ComplexColor(String name, int code) {
        this.name = name;
        this.code = code;
    }


    /**
     * 根据code获取描述
     * @param code
     * @return
     */
    public static String from(int code) {
        return map.get(code) == null ? null : map.get(code).name;
    }
}
