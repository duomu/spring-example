package com.yll.example.jvm;

/**
 * -Xss128K
 * @author：linlin.yang
 * @date：2018/3/28 15:13
 */
public class JavaVMStackSOF {
    public static void main(String[] args) {
        JavaVMStackSOF test = new JavaVMStackSOF();
        System.out.println(test.func(1000));
    }

    int func(int n) {
        if (n == 1) {
            return 1;
        }

        return n * func(n - 1);
    }
}
