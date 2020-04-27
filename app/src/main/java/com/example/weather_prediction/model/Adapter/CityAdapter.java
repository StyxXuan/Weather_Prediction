package com.example.weather_prediction.model.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_prediction.R;
import com.example.weather_prediction.model.City;
import com.example.weather_prediction.model.ViewHolder.CityViewHolder;

import java.util.List;

import static com.example.weather_prediction.API.Cache.addCity;
import static com.example.weather_prediction.API.Cache.cityStored;
import static com.example.weather_prediction.API.Cache.deleteCity;
import static com.example.weather_prediction.API.Cache.getCityNames;

public class CityAdapter extends RecyclerView.Adapter<CityViewHolder> {
    private List<String> cities;
    private Context context;

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_card, parent, false);
        CityViewHolder viewHolder = new CityViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CityViewHolder holder, final int position) {
        final String cityName = cities.get(position);
        holder.itemCity.setText(cityName);
        if(cityStored(cityName))
            holder.addBtn.setText("删除");
        else
            holder.addBtn.setText("添加");

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cityStored(cityName)){
                    deleteCity(cityName);
                }else{
                    addCity(view.getContext(), cityName);
                }
                setData(getCityNames());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public CityAdapter(Context context){
        this.context = context;
    }


    public void setData(List<String> cities){
        this.cities = cities;
        this.notifyDataSetChanged();
    }
}
