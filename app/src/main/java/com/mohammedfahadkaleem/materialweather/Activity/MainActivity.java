package com.mohammedfahadkaleem.materialweather.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohammedfahadkaleem.materialweather.Api.OpenWeatherClient;
import com.mohammedfahadkaleem.materialweather.Api.OpenWeatherService;
import com.mohammedfahadkaleem.materialweather.Model.WeatherData;
import com.mohammedfahadkaleem.materialweather.Model.WeatherList;
import com.mohammedfahadkaleem.materialweather.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static int city_id = 5501350;
    private static String API = "16da578b7b15cd3985cb0f412829a86c";
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<WeatherList> dayWeathers;
    private  WeatherList todaysWeather;
    OpenWeatherService openWeatherService;

    TextView cityName;
    TextView weatherDescription;
    TextView temperature;
    TextView weatherUnits;
    ImageView weatherImage;
    TextView windSpeed;
    TextView windSpeedUnits;

    private String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_card);
        getWeather();
        displayWeather();
    }

    private void displayWeather(){
        cityName = findViewById(R.id.tv_city_name);
        weatherDescription = findViewById(R.id.tv_weather_description);
        temperature = findViewById(R.id.temperature);
        weatherUnits = findViewById(R.id.tv_units);
        weatherImage = findViewById(R.id.image_weather);
        windSpeed = findViewById(R.id.tv_windspeed);
        windSpeedUnits = findViewById(R.id.tv_windspeed_units);

        cityName.setText(city);

    }

    private void getWeather(){
        openWeatherService = OpenWeatherClient.getRetrofit().create(OpenWeatherService.class);
        Call<WeatherData> cityWeatherCall = openWeatherService.getWeather(city_id,2,API);
        cityWeatherCall.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                WeatherData weatherData = response.body();
                dayWeathers = weatherData.getList();
                todaysWeather = dayWeathers.get(0);
                city = weatherData.getCity().getName();
                Log.d(TAG,"Got Weather Data Successfully");
                Log.d(TAG,todaysWeather.toString());
                Log.d(TAG,city);
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.d(TAG,"Fail"+t);
            }
        });
    }
}
