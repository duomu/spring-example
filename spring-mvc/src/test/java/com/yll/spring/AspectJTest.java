package com.yll.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author：linlin.yang
 * @date：2018/5/22 14:33
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:testSpring.xml")
public class AspectJTest {
    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("testSpring.xml");
        TestBean bean = (TestBean) bf.getBean("testBean");
        bean.test();
    }
}
