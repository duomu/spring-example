package com.yll.example.jvm;

import java.util.LinkedList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author：linlin.yang
 * @date：2018/3/28 14:03
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new LinkedList<OOMObject>();
        int i = 0;
        while (i < 1000000) {
            list.add(new OOMObject());
            i++;
        }
    }

}
