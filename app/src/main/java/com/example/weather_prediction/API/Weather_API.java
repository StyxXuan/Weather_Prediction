package com.example.weather_prediction.API;

import interfaces.heweather.com.interfacesmodule.view.HeConfig;

public class Weather_API {

    public static void weather_init(String id, String key){
        HeConfig.init(id, key);
        HeConfig.switchToFreeServerNode();
    }
}
