package com.example.weather_prediction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.weather_prediction.API.Cache;
import com.example.weather_prediction.API.Weather_API;
import com.example.weather_prediction.presenter.frag_add_city;
import com.example.weather_prediction.presenter.frag_future_weather;
import com.example.weather_prediction.presenter.frag_weather_city;

import static com.example.weather_prediction.API.Cache.FlushContent;
import static com.example.weather_prediction.API.File_API.WriteToFile;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private frag_add_city add_city;
    private frag_future_weather future_weather;
    private frag_weather_city weather_city;
    private FragmentManager fragmentManager;
    private ImageView btn_add_city, btn_future_weather, btn_weather_city;
    private RelativeLayout l_add_city, l_future_weather, l_weather_city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        init();
        setChoiceFragment(0);
    }

    @SuppressLint("WrongViewCast")
    private void init(){
        Weather_API.weather_init("HE2004091642421931", "a26d5d210cea4cd08f9e1c4e230c3b0f");
        btn_weather_city = findViewById(R.id.city_weather);
        btn_future_weather = findViewById(R.id.future_weather);
        btn_add_city = findViewById(R.id.add_city);

        l_add_city = findViewById(R.id.l_add_city);
        l_future_weather = findViewById(R.id.l_future_weather);
        l_weather_city = findViewById(R.id.l_city_weather);

        l_add_city.setOnClickListener(MainActivity.this);
        l_future_weather.setOnClickListener(MainActivity.this);
        l_weather_city.setOnClickListener(MainActivity.this);

        Cache.init(MainActivity.this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.l_city_weather:
                setChoiceFragment(0);
                break;
            case R.id.l_future_weather:
                Log.d("Click", "1");
                setChoiceFragment(1);
                break;
            case R.id.l_add_city:
                Log.d("Click", "2");
                setChoiceFragment(2);
                break;
            default:
                break;
        }
    }

    private void setChoiceFragment(int choice){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        switch (choice){
            case 0:
                if(weather_city == null) {
                    weather_city = new frag_weather_city();
                    fragmentTransaction.add(R.id.content, weather_city);
                }
                else
                    fragmentTransaction.show(weather_city);
                break;
            case 1:
                if(future_weather == null) {
                    future_weather= new frag_future_weather();
                    fragmentTransaction.add(R.id.content, future_weather);
                }
                else
                    fragmentTransaction.show(future_weather);
                break;
            case 2:
                if(add_city == null) {
                    add_city= new frag_add_city();
                    fragmentTransaction.add(R.id.content, add_city);
                }
                else
                    fragmentTransaction.show(add_city);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }


    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (future_weather != null) {
            fragmentTransaction.hide(future_weather);
        }
        if (add_city != null) {
            fragmentTransaction.hide(add_city);
        }
        if (weather_city!= null) {
            fragmentTransaction.hide(weather_city);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String content = FlushContent();
        Log.d("Content", content);
        WriteToFile(content);
    }
}
