package com.example.weather_prediction.model.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_prediction.R;

public class CityViewHolder extends RecyclerView.ViewHolder {
    public final TextView itemCity;
    public final Button addBtn;

    public CityViewHolder(@NonNull View itemView) {
        super(itemView);
        itemCity = itemView.findViewById(R.id.item_city);
        addBtn = itemView.findViewById(R.id.btnAdd);
    }
}
