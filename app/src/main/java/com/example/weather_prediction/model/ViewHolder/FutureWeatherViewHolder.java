package com.example.weather_prediction.model.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_prediction.R;

public class FutureWeatherViewHolder extends RecyclerView.ViewHolder {
    public final ImageView forecast_icon;
    public final TextView forecast_date;
    public final TextView forecast_temp;
    public final TextView forecast_txt;

    public FutureWeatherViewHolder(@NonNull View itemView) {
        super(itemView);
        forecast_date = itemView.findViewById(R.id.forecast_date);
        forecast_icon = itemView.findViewById(R.id.forecast_icon);
        forecast_temp = itemView.findViewById(R.id.forecast_temp);
        forecast_txt = itemView.findViewById(R.id.forecast_txt);
    }
}
