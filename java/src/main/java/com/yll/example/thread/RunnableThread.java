package com.yll.example.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：linlin.yang
 * @date：2018/3/6 16:53
 */
public class RunnableThread implements Runnable {
    private Counter counter;

    public RunnableThread(Counter counter) {
        this.counter = counter;
    }
    public void run() {
        this.counter.increase();
        System.out.println(Thread.currentThread().getName() + "的Counter值为：" + counter.getCount());
//        System.out.println(Thread.currentThread().getName() + "正在执行");
    }


    public static void main(String[] args) {
//        Counter counter = new Counter();
//        for (int i = 0; i < 100; i++) {
//            Thread thread = new Thread(new RunnableThread(counter), "线程" + i);
//            thread.start();
//        }

//        System.out.println("计数器值：" + counter.getCount());

        int i = 0;
        if (i >= 65 && i <= 90) {
            System.out.println(i);
        }

        int result = i - 65 | 90 - i;
        System.out.println(result);
        System.out.println(-1|-1);
    }
}
