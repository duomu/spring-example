package com.yll.spring;

import com.yll.springmvc.dto.Car;
import com.yll.springmvc.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author：linlin.yang
 * @date：2018/5/22 10:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class BeanFactoryTest {
    @Test
    public void testSimpleLoad() {
        //deprecated
        Resource resource = new ClassPathResource("spring/applicationContext.xml");
//        BeanFactory bf = new XmlBeanFactory(resource);
        BeanFactory bf = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) bf);
        reader.loadBeanDefinitions(resource);
        IUserService userService = (IUserService) bf.getBean("userService");
        if (userService != null) {
            System.err.println("get bean success");
        } else {
            System.err.println("get bean failed");
        }

        //ApplicationContext
        ApplicationContext sc = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        userService = (IUserService) sc.getBean("userService");
        if (userService != null) {
            System.err.println("get bean success");
        } else {
            System.err.println("get bean failed");
        }

        Car car = (Car) sc.getBean("car");
        if (car != null) {
            System.err.println("get car success：" + car.getBrand() + ", " + car.getPrice() + ", " + car.getMaxSpeed());
        } else {
            System.err.println("get car failed");
        }


    }
}
