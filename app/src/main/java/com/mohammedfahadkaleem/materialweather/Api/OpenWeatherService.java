package com.mohammedfahadkaleem.materialweather.Api;

import com.mohammedfahadkaleem.materialweather.Model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fahadkaleem on 1/29/18.
 */

public interface OpenWeatherService {

    @GET("forecast/daily?units=metric")
    Call<WeatherData> getWeather(@Query("id") int city_id, @Query("cnt") int cnt, @Query("appid") String api );
}
