package com.yll.example;

import org.springframework.stereotype.Component;

/**
 * @author：linlin.yang
 * @date：2017/9/27 18:33
 */
@Component
public class DefaultPerformance implements Performance {
    public void perform(String name) {
        System.out.println(name + " Sing a song");
    }
}
