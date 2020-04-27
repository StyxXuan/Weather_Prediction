package com.example.weather_prediction.presenter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_prediction.API.Cache;
import com.example.weather_prediction.R;
import com.example.weather_prediction.model.Adapter.FutureWeatherAdapter;
import com.example.weather_prediction.model.WeatherPrediction;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.ForecastBase;

public class frag_future_weather extends Fragment {
    private Spinner spinner;
    private int presentCity;
    private RecyclerView recyclerView;
    private FutureWeatherAdapter futureWeatherAdapter;
    private ArrayAdapter<String> adapter;

    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.frag_future_weather, container, false);
        presentCity = 0;
        spinner = view.findViewById(R.id.spinner);
        recyclerView =  view.findViewById(R.id.rvFutureWeather);
        futureWeatherAdapter = new FutureWeatherAdapter(view.getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new ArrayAdapter<String>(view.getContext(), R.layout.spinner_item, R.id.spinner_item, Cache.getCityNames());

        spinner.setAdapter(adapter);
        spinner.setPrompt("选择城市");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presentCity = i;
                setData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        setData();
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(futureWeatherAdapter);
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        return view;
    }

    public void setData(){
        WeatherPrediction weatherPrediction = Cache.getWeatherPredictions().get(presentCity);
        List<ForecastBase> forecastBases = weatherPrediction.getForecastBases();

        if(forecastBases == null){
            return;
        }

        futureWeatherAdapter.setForecastBases(forecastBases);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
