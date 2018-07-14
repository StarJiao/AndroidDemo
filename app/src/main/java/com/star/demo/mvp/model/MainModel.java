package com.star.demo.mvp.model;

import com.star.demo.mvp.bean.Temperature;

import java.util.Random;

public class MainModel {
    public Temperature queryTemperature() {
        // simulate query from Server
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // degree is in random [20,30)
        int degree = 20 + new Random().nextInt(10);

        Temperature temperatureBean = new Temperature();
        temperatureBean.setTemper(degree);
        return temperatureBean;
    }
}
