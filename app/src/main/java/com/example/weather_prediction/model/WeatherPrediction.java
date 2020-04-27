package com.example.weather_prediction.model;

import android.content.Context;
import android.util.Log;

import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.Code;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.Forecast;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.ForecastBase;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

public class WeatherPrediction {
    private City city;
    private Context context;
    private List<ForecastBase> forecastBases;

    public WeatherPrediction(Context context, City city){
        this.city = city;
        this.context = context;
        initWeather();
    }

    public String getCityName(){
        return city.getCityName();
    }

    public List<ForecastBase> getForecastBases() {
        return forecastBases;
    }

    public void initWeather(){
        HeWeather.getWeatherForecast(context, this.city.getCid(), new HeWeather.OnResultWeatherForecastBeanListener(){

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onSuccess(Forecast forecast) {
                if ( Code.OK.getCode().equalsIgnoreCase(forecast.getStatus()) ){
                    //此时返回数据
                    forecastBases = forecast.getDaily_forecast();
                    for (ForecastBase base: forecastBases){
                        Log.d("forecast", base.getDate());
                        Log.d("forecast", base.getTmp_max());
                    }

                } else {
                    //在此查看返回数据失败的原因
                    Log.d("error", "getNow error");
                    String status = forecast.getStatus();
                    Code code = Code.toEnum(status);
                }
            }
        });

    }
}
