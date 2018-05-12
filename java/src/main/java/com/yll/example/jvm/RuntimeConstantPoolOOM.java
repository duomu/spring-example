package com.yll.example.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author：linlin.yang
 * @date：2018/3/28 16:39
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        RuntimeConstantPoolOOM test = new RuntimeConstantPoolOOM();
        List<String> list = new ArrayList<String>();
        int i=0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
