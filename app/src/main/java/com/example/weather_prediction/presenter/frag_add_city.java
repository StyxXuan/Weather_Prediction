package com.example.weather_prediction.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_prediction.R;
import com.example.weather_prediction.model.Adapter.CityAdapter;

import java.util.ArrayList;
import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.Code;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.basic.Basic;
import interfaces.heweather.com.interfacesmodule.bean.search.Search;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

import static android.content.ContentValues.TAG;
import static com.example.weather_prediction.API.Cache.getCity;

public class frag_add_city extends Fragment {
    RecyclerView recyclerView;
    CityAdapter cityAdapter;
    EditText searchBar;

    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.frag_add_city, container, false);
        recyclerView = view.findViewById(R.id.rvAddCity);
        cityAdapter = new CityAdapter(view.getContext());
        searchBar = view.findViewById(R.id.search);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        setData();
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cityAdapter);
        recyclerView.setItemAnimator( new DefaultItemAnimator());

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!searchBar.getText().toString().equals("")){
                    setSearchData(view.getContext(), s.toString());
                }else{
                    setData();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(!searchBar.getText().toString().equals("")){
                    setSearchData(view.getContext(), s.toString());
                }else{
                    setData();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!searchBar.getText().toString().equals("")){
                    setSearchData(view.getContext(), s.toString());
                }else{
                    setData();
                }
            }
        });

        return view;
    }

    public void setSearchData(Context context, String s){
        HeWeather.getSearch(context, s, "CN", 1, Lang.CHINESE_SIMPLIFIED , new HeWeather.OnResultSearchBeansListener() {
            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onSuccess(Search search) {
                if ( Code.OK.getCode().equalsIgnoreCase(search.getStatus()) ){
                    List<Basic> now = search.getBasic();
                    List<String> CityNames = new ArrayList<String>();
                    for(Basic basic: now){
                        CityNames.add(basic.getLocation());
                    }
                    cityAdapter.setData(CityNames);
                } else {
                    //在此查看返回数据失败的原因
                    String status = search.getStatus();
                    Code code = Code.toEnum(status);
                    Log.i(TAG, "failed code: " + code);
                }
            }
        });
    }

    public void setData(){
        cityAdapter.setData(getCity());
    }
}
