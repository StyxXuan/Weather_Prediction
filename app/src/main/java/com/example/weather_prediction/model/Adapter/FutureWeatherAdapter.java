package com.example.weather_prediction.model.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_prediction.R;
import com.example.weather_prediction.model.ViewHolder.FutureWeatherViewHolder;

import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.ForecastBase;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherViewHolder> {
    private List<ForecastBase> forecastBases;
    private Context context;

    @NonNull
    @Override
    public FutureWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.future_weather_card, parent, false);
        FutureWeatherViewHolder viewHolder = new FutureWeatherViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FutureWeatherViewHolder holder, int position) {
        ForecastBase base = forecastBases.get(position);
        String code = base.getCond_code_d();
        if(code != ""){
            Bitmap bitmap = BitmapFactory.decodeStream(context.getClass().getResourceAsStream("/res/drawable/p"+code+".png"));
            holder.forecast_icon.setImageBitmap(bitmap);
        }

        holder.forecast_date.setText(base.getDate());
        holder.forecast_temp.setText(base.getTmp_max() + " " + base.getTmp_min());
        String txt = base.getCond_txt_d() + "。最高" + base.getTmp_max() + "℃。" +
            base.getWind_dir() + base.getWind_spd() + "km/h";

        holder.forecast_txt.setText(txt);
    }

    @Override
    public int getItemCount() {
        return forecastBases.size();
    }

    public FutureWeatherAdapter(Context context){
        this.context = context;
    }

    public void setForecastBases(List<ForecastBase> bases){
        this.forecastBases = bases;
        this.notifyDataSetChanged();
    }
}
