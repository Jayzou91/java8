package com.fish.reflect;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/23
 */
public class Car {

    private Integer carId;
    private String carName;

    private Car() {
    }

    private Car(Integer carId, String carName) {
        this.carId = carId;
        this.carName = carName;
    }

    public Integer getCarId() {
        return carId;
    }

    public String getCarName() {
        return carName;
    }
}
