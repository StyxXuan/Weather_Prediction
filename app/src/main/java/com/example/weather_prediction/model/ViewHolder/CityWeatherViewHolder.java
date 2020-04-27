package com.example.weather_prediction.model.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_prediction.R;

public class CityWeatherViewHolder extends RecyclerView.ViewHolder {
    public final TextView cardCityName;
    public final TextView cardWeatherDescription;
    public final ImageView cardWeatherIcon;
    public final TextView cardCurrentTemp;
    public final TextView carMaxTemp;
    public final TextView cardMinTemp;

    public CityWeatherViewHolder(@NonNull View itemView) {
        super(itemView);
        cardCityName = itemView.findViewById(R.id.textViewCardCityName);
        cardWeatherDescription = itemView.findViewById(R.id.textViewCardWeatherDescription);
        cardWeatherIcon = itemView.findViewById(R.id.imageViewCardWeatherIcon);
        cardCurrentTemp = itemView.findViewById(R.id.textViewCardCurrentTemp);
        carMaxTemp = itemView.findViewById(R.id.textViewCardMaxTemp);
        cardMinTemp = itemView.findViewById(R.id.textViewCardMinTemp);
    }
}
