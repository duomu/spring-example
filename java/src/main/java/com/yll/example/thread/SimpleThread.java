package com.yll.example.thread;

/**
 * @author：linlin.yang
 * @date：2018/3/6 17:01
 */
public class SimpleThread extends Thread {
    public SimpleThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("线程1")) {
            Thread.yield();
        }

        if (Thread.currentThread().getName().equals("线程2")) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {

            }
        }
        System.out.println(Thread.currentThread().getName() + "正在执行");
    }

    public static void main(String[] args) {
        Thread thread1 = new SimpleThread("线程1");
        Thread thread2 = new SimpleThread("线程2");
        thread1.start();
        thread2.start();

        while (thread1.isAlive()) {
            System.out.println(thread1.getName() + " is alive");
        }
    }
}
