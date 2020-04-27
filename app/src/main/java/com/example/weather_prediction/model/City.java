package com.example.weather_prediction.model;

import android.content.Context;
import android.util.Log;

import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.Code;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.basic.Basic;
import interfaces.heweather.com.interfacesmodule.bean.search.Search;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

import static android.content.ContentValues.TAG;

public class City {
    private String Cid;
    private Context context;
    private String cityName;
    public City(Context context, String Cid, String cityName){
        this.context = context;
        this.Cid = Cid;
        this.cityName = cityName;
        this.getInfo();
    }

    public City(Context context, String Cid){
        this.context = context;
        this.Cid = Cid;
        this.cityName = "";
        this.getInfo();
    }

    public City(Context context){
        this.context = context;
        Cid = "";
        cityName = "";
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCid(){
        return this.Cid;
    }

    public String getCityName(){
        return this.cityName;

    }

    public void getInfo(){
        HeWeather.getSearch(context, this.Cid, "CN", 1, Lang.CHINESE_SIMPLIFIED , new HeWeather.OnResultSearchBeansListener() {
            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onSuccess(Search search) {
                if ( Code.OK.getCode().equalsIgnoreCase(search.getStatus()) ){
                    List<Basic> now = search.getBasic();
                    setCityName(now.get(0).getLocation());
                    setCid(now.get(0).getCid());
                    Log.d("Res", now.get(0).getCid());
                    Log.d("Res", now.get(0).getLocation());
                    Log.d("Res", now.get(0).getAdmin_area());
                } else {
                    //在此查看返回数据失败的原因
                    String status = search.getStatus();
                    Code code = Code.toEnum(status);
                    Log.i(TAG, "failed code: " + code);
                }
            }
        });
    }
}
