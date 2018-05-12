package com.yll.string;

/**
 * 判断字符串是否是数字
 * @author：linlin.yang
 * @date：2018/3/5 10:25
 */
public class CheckNumeric {
    /**
     * 使用ASCII码
     * @param s
     * @return
     */
    public static boolean isNumericByASCII(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            if (ch < 48 || ch > 57) {
                return false;
            }
        }

        return true;
    }

    /**
     * 使用正则表达式
     * @param s
     * @return
     */
    public static boolean isNumericByRegex(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        return s.matches("[0-9]*");
    }

    /**
     * 使用Java自带函数
     * @param s
     * @return
     */
    public static boolean isNumericByProto(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String ch = "pen";
        String ch2 = "100";
        System.out.println(ch + " is digit：" + isNumericByASCII(ch));
        System.out.println(ch2 + " is digit：" + isNumericByASCII(ch2));
        System.out.println(ch + " is digit：" + isNumericByRegex(ch));
        System.out.println(ch2 + " is digit：" + isNumericByRegex(ch2));
        System.out.println(ch + " is digit：" + isNumericByProto(ch));
        System.out.println(ch2 + "is digit：" + isNumericByProto(ch2));
    }
}
