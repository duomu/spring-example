package com.yll.example.thread.demo;

/**
 * @author：linlin.yang
 * @date：2017/9/12 15:02
 */
public class DisplayMessage implements Runnable {
    private String message;

    public DisplayMessage(String message) {
        this.message = message;
    }
    public void run() {
        while (true) {
            System.out.println(message);
        }

    }
}
