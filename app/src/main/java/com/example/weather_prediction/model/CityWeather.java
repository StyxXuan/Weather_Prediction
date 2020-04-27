package com.example.weather_prediction.model;

import android.content.Context;
import android.util.Log;

import interfaces.heweather.com.interfacesmodule.bean.Code;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

public class CityWeather {
    private City city;
    private Context context;
    private NowBase now;

    public CityWeather(Context context, City city){
        this.context = context;
        this.city = city;
        this.getWeather();
    }

    public void setNow(NowBase now) {
        this.now = now;
    }

    public String getCond_code(){
        if(now != null){
            return this.now.getCond_code();
        }else{
            return "";
        }
    }

    public String getWind_sc(){
        if(now != null){
            return this.now.getWind_sc();
        }else{
            return "";
        }
    }

    public String getWind_dir(){
        if(now != null){
            return this.now.getWind_dir();
        }else{
            return "";
        }
    }

    public String getCityName(){
        return this.city.getCityName();
    }

    public String getTemp(){
        if(now != null){
            return this.now.getTmp();
        }else{
            return "";
        }
    }

    public String getFl(){
        if(now != null){
            return this.now.getFl();
        }else{
            return "";
        }
    }

    public String getCond_txt(){
        if(now != null){
            return this.now.getCond_txt();
        }else{
            return "";
        }
    }

    public String getHum(){
        if(now != null){
            return this.now.getHum();
        }else{
            return "";
        }
    }

    public String getPcpn(){
        if(now != null){
            return this.now.getPcpn();
        }else{
            return "";
        }
    }

    public void getWeather(){
        HeWeather.getWeatherNow(this.context, this.city.getCid(), Lang.CHINESE_SIMPLIFIED , Unit.METRIC , new HeWeather.OnResultWeatherNowBeanListener() {
            @Override
            public void onError(Throwable e) {
                Log.d("error", "getNow error");
            }

            @Override
            public void onSuccess(Now dataObject) {
                //先判断返回的status是否正确，当status正确时获取数据，若status不正确，可查看status对应的Code值找到原因
                if ( Code.OK.getCode().equalsIgnoreCase(dataObject.getStatus()) ){
                    //此时返回数据
                    setNow(dataObject.getNow());
                    Log.d("Res", dataObject.getNow().getCond_txt());
                    Log.d("Res", dataObject.getNow().getWind_dir());
                } else {
                    //在此查看返回数据失败的原因
                    Log.d("error", "getNow error");
                    String status = dataObject.getStatus();
                    Code code = Code.toEnum(status);
                }
            }
        });
    }



}
