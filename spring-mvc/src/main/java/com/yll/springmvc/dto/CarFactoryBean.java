package com.yll.springmvc.dto;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author：linlin.yang
 * @date：2018/5/22 11:36
 */
public class CarFactoryBean implements FactoryBean<Car> {
    private String carInfo;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        String[] infos = carInfo.split(",");
        car.setBrand(infos[0]);
        car.setPrice(Double.valueOf(infos[1]));
        car.setMaxSpeed(Integer.valueOf(infos[2]));
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }
}
