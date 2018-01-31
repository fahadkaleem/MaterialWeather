
package com.mohammedfahadkaleem.materialweather.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherList {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("temp")
    @Expose
    private DayTemperature temp;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("weather")
    @Expose
    private java.util.List<WeatherOverview> weather = null;
    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Integer deg;
    @SerializedName("clouds")
    @Expose
    private Integer clouds;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public DayTemperature getTemp() {
        return temp;
    }

    public void setTemp(DayTemperature temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public java.util.List<WeatherOverview> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<WeatherOverview> weather) {
        this.weather = weather;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    @Override
    public String toString() {
        return "Todays Weather is: " +
                "\ndt=" + dt.toString() +
                ",\nDay Temp=" + temp+
                ",\nPressure=" + pressure.toString() +
                ",\nHumidity=" + humidity.toString() +
                ",\nWeather=" + weather +
                ",\nSpeed=" + speed.toString() +
                ",\nDeg=" + deg.toString() +
                ",\nClouds=" + clouds.toString();
    }
}
