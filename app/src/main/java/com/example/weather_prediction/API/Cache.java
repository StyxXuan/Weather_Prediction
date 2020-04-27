package com.example.weather_prediction.API;

import android.content.Context;
import android.util.Log;

import com.example.weather_prediction.model.City;
import com.example.weather_prediction.model.CityWeather;
import com.example.weather_prediction.model.WeatherPrediction;

import java.util.ArrayList;
import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.Code;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.basic.Basic;
import interfaces.heweather.com.interfacesmodule.bean.search.Search;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

import static android.content.ContentValues.TAG;
import static com.example.weather_prediction.API.File_API.WriteToFile;
import static com.example.weather_prediction.API.File_API.getContent;
import static com.example.weather_prediction.API.File_API.initFile;

public class Cache {
    private static List<City> cities;
    private static List<CityWeather> cityWeathers;
    private static List<WeatherPrediction> weatherPredictions;

    public static void init(Context context){
        initFile(context);
        String strCities = getContent();
        Log.d("Path", strCities);
        cities= new ArrayList<>();

        String[] strCity = strCities.split("\n");
        for(String city: strCity){
            cities.add(new City(context, city));
        }


        cityWeathers = new ArrayList<>();
        weatherPredictions = new ArrayList<>();

        for(City city: cities){
            CityWeather cityWeather = new CityWeather(context, city);
            cityWeathers.add(cityWeather);

            WeatherPrediction weatherPrediction = new WeatherPrediction(context, city);
            weatherPredictions.add(weatherPrediction);
        }
    }

    public static String FlushContent(){
        String content = "";
        for(City city: cities){
            content += city.getCid();
            content += "\n";
        }
        return content;
    }

    public static List<WeatherPrediction> getWeatherPredictions() {
        return weatherPredictions;
    }

    public static void refresh(){

    }

    public static Boolean cityStored(String cityName){
        for(City city: cities){
            if(city.getCityName().equals(cityName)){
                return true;
            }
        }

        return false;
    }

    public static List<String> getCity(){
        List<String> CityNames = new ArrayList<>();
        for(City city: cities){
            CityNames.add(city.getCityName());
        }

        return  CityNames;
    }

    public static void addCity(final Context context, String cityName){
        HeWeather.getSearch(context, cityName, "CN", 1, Lang.CHINESE_SIMPLIFIED , new HeWeather.OnResultSearchBeansListener() {
            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onSuccess(Search search) {
                if ( Code.OK.getCode().equalsIgnoreCase(search.getStatus()) ){
                    City city = new City(context);
                    List<Basic> now = search.getBasic();
                    city.setCid(now.get(0).getCid());
                    city.getInfo();
                    cities.add(city);
                    CityWeather cityWeather = new CityWeather(context, city);
                    cityWeathers.add(cityWeather);

                    WeatherPrediction weatherPrediction = new WeatherPrediction(context, city);
                    weatherPredictions.add(weatherPrediction);
                    String content = FlushContent();
                    Log.d("Content", content);
                    WriteToFile(content);
                } else {
                    //在此查看返回数据失败的原因
                    String status = search.getStatus();
                    Code code = Code.toEnum(status);
                    Log.i(TAG, "failed code: " + code);
                }
            }
        });
    }

    public static void deleteCity(String cityName){
        for(City city: cities){
            if(city.getCityName().equals(cityName)){
                cities.remove(city);
                break;
            }
        }

        for(CityWeather cityWeather: cityWeathers){
            if(cityWeather.getCityName().equals(cityName)){
                cityWeathers.remove(cityWeather);
                break;
            }
        }

        for(WeatherPrediction weatherPrediction: weatherPredictions){
            if(weatherPrediction.getCityName().equals(cityName)){
                weatherPredictions.remove(weatherPrediction);
                break;
            }
        }

        String content = FlushContent();
        WriteToFile(content);
    }


    public static List<CityWeather> getCityWeathers(){
        return cityWeathers;
    }

    public static List<City> getCities(){
        return cities;
    }

    public static List<String> getCityNames(){
        List<String> cityNames = new ArrayList<>();
        for(City city: cities){
            cityNames.add(city.getCityName());
        }

        return cityNames;
    }
}
