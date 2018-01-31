package com.mohammedfahadkaleem.materialweather.Activity;

import android.os.AsyncTask;
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

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static int city_id = 5501350;
    private static String API = "16da578b7b15cd3985cb0f412829a86c";
    WeatherData weatherData;
    OpenWeatherService openWeatherService;
    Call<WeatherData> cityWeatherCall;
    TextView tv_cityName;
    TextView tv_weatherDescription;
    TextView tv_temperature;
    TextView tv_weatherUnits;
    ImageView tv_weatherImage;
    TextView tv_windSpeed;
    TextView tv_windSpeedUnits;
    TextView tv_dayOfWeek;
    TextView tv_time;
    private List<WeatherList> dayWeathers;
    private WeatherList todaysWeather;
    private String city;
    private String weatherDesc;
    private int humidity;
    private Double windSpeed;
    private int minTemp;
    private int maxTemp;
    private int morningTemp;
    private int eveningTemp;
    private int nightTemp;
    private int clouds;
    private Double pressure;
    private int currentTemp;
    private String today;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_card);
        //getWeather();
        createViews();
        //openWeatherService = OpenWeatherClient.getRetrofit().create(OpenWeatherService.class);
        //cityWeatherCall = openWeatherService.getWeather(city_id,2,API);
        new NetworkCall().execute(cityWeatherCall);
    }

    private void createViews() {
        tv_cityName = findViewById(R.id.tv_city_name);
        tv_weatherDescription = findViewById(R.id.tv_weather_description);
        tv_temperature = findViewById(R.id.temperature);
        tv_weatherUnits = findViewById(R.id.tv_units);
        tv_weatherImage = findViewById(R.id.image_weather);
        tv_windSpeed = findViewById(R.id.tv_windspeed);
        tv_windSpeedUnits = findViewById(R.id.tv_windspeed_units);
        tv_dayOfWeek = findViewById(R.id.tv_day_of_the_week);
        tv_time = findViewById(R.id.tv_time);
    }

    private void getWeather() {
        openWeatherService = OpenWeatherClient.getRetrofit().create(OpenWeatherService.class);
        Call<WeatherData> cityWeatherCall = openWeatherService.getWeather(city_id, 2, API);
        cityWeatherCall.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                weatherData = response.body();
                dayWeathers = weatherData.getList();
                todaysWeather = dayWeathers.get(0);
                city = weatherData.getCity().getName();
                Log.d(TAG, "Got Weather Data Successfully");
                Log.d(TAG, todaysWeather.toString());
                Log.d(TAG, city);
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.d(TAG, "Fail" + t);
            }
        });
    }

    private class NetworkCall extends AsyncTask<Call, Void, String> {

        @Override
        protected String doInBackground(Call[] calls) {
            openWeatherService = OpenWeatherClient.getRetrofit().create(OpenWeatherService.class);
            Call<WeatherData> cityWeatherCall = openWeatherService.getWeather(city_id, 2, API);
            try {
                WeatherData weatherData = cityWeatherCall.execute().body();
                dayWeathers = weatherData.getList();
                todaysWeather = dayWeathers.get(0);
                city = weatherData.getCity().getName();
                currentTemp = (int) Math.round(weatherData.getList().get(0).getTemp().getDay());
                minTemp = (int) Math.round(weatherData.getList().get(0).getTemp().getMin());
                maxTemp = (int) Math.round(weatherData.getList().get(0).getTemp().getMax());
                morningTemp = (int) Math.round(weatherData.getList().get(0).getTemp().getMorn());
                eveningTemp = (int) Math.round(weatherData.getList().get(0).getTemp().getEve());
                nightTemp = (int) Math.round(weatherData.getList().get(0).getTemp().getNight());
                pressure = weatherData.getList().get(0).getPressure();
                humidity = weatherData.getList().get(0).getHumidity();
                weatherDesc = weatherData.getList().get(0).getWeather().get(0).getMain();
                windSpeed = weatherData.getList().get(0).getSpeed();
                Log.d(TAG, "Got Weather Data Successfully");
                Log.d(TAG, todaysWeather.toString());
                Log.d(TAG, city);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            tv_cityName.setText(city);
            today = new SimpleDateFormat("EE", Locale.ENGLISH).format(System.currentTimeMillis());
            DateFormat df = new SimpleDateFormat("hh:mm aa");
            Date dateobj = new Date();
            String time = df.format(dateobj);
            tv_temperature.setText(String.valueOf(currentTemp));
            tv_time.setText(time);
            tv_dayOfWeek.setText(today);
            tv_weatherDescription.setText(weatherDesc);
            tv_windSpeed.setText(windSpeed.toString());
        }
    }
}
