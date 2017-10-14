package com.yll.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author：linlin.yang
 * @date：2017/9/27 18:25
 */
@Configuration
@EnableAspectJAutoProxy//启用AspectJ自动代理
@ComponentScan("com.yll.example")
public class ConcertConfig {

}
