package com.example.weather_prediction.presenter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_prediction.API.Cache;
import com.example.weather_prediction.R;
import com.example.weather_prediction.model.Adapter.CityWeatherAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class frag_weather_city extends Fragment {
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;

    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_city_weather, container, false);
        recyclerView = view.findViewById(R.id.rvWeatherCard);
        refreshLayout = view.findViewById(R.id.refreshLayout);


        final CityWeatherAdapter cityWeatherAdapter = new CityWeatherAdapter(this.getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());


        cityWeatherAdapter.setWeatherList(Cache.getCityWeathers());

        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cityWeatherAdapter);
        recyclerView.setItemAnimator( new DefaultItemAnimator());


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                cityWeatherAdapter.refresh();
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshLayout.autoRefresh();
    }
}
