package com.yll.example;

import org.springframework.stereotype.Component;

/**
 * @author：linlin.yang
 * @date：2017/9/27 19:18
 */
@Component("xmlAudience")
public class XmlAudience {
    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }

    public void takeSeats() {
        System.out.println("Taking seats");
    }

    public void applause() {
        System.out.println("CLAP CLAP CLAP");
    }

    public void demandRefund() {
        System.out.println("Demanding a refund");
    }
}
