package com.example.weather_prediction.model.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_prediction.R;
import com.example.weather_prediction.model.City;
import com.example.weather_prediction.model.CityWeather;
import com.example.weather_prediction.model.ViewHolder.CityWeatherViewHolder;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CityWeatherAdapter extends RecyclerView.Adapter<CityWeatherViewHolder> {
    private List<CityWeather> weatherList;
    private Context context;

    public CityWeatherAdapter(Context  context){
        this.context = context;
    }

    public void setWeatherList(List<CityWeather> weatherList) {
        this.weatherList = weatherList;
        Log.d("setWeatherList", String.valueOf(weatherList.size()));
        notifyDataSetChanged();
    }

    @Override
    public CityWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_card, parent, false);
        CityWeatherViewHolder viewHolder = new CityWeatherViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityWeatherViewHolder holder, int position) {
        Log.d("onBindViewHolder: ",  String.valueOf(getItemCount()));
        CityWeather cityWeather = weatherList.get(position);
        Log.d("Res", cityWeather.getCityName());
        holder.cardCityName.setText(cityWeather.getCityName());
        holder.cardWeatherDescription.setText(cityWeather.getCond_txt());
        holder.cardCurrentTemp.setText(cityWeather.getTemp()+"℃");
        holder.carMaxTemp.setText(cityWeather.getWind_dir());
        holder.cardMinTemp.setText(cityWeather.getWind_sc());
        String code = cityWeather.getCond_code();
        if(code != ""){
            Bitmap bitmap = BitmapFactory.decodeStream(context.getClass().getResourceAsStream("/res/drawable/p"+code+".png"));
            holder.cardWeatherIcon.setImageBitmap(bitmap);
        }

    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public void refresh() {
        notifyDataSetChanged();
    }
}
