package com.example.android.weather;

/**
 * Created by 118168 on 6/14/2017.
 */

public class Weather {
    float temp_f;
    float temp_c;
    String img;
    float feelsLike_f;
    String conditions;
    String Location;
    String relativeHumidity;
    int pressure;
    float precip_today_in;
    String forecast_URL;
    float latitude;
    float longitude;
    String elevation;

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(String relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public float getPrecip_today_in() {
        return precip_today_in;
    }

    public void setPrecip_today_in(float precip_today_in) {
        this.precip_today_in = precip_today_in;
    }

    public String getForecast_URL() {
        return forecast_URL;
    }

    public void setForecast_URL(String forecast_URL) {
        this.forecast_URL = forecast_URL;
    }



    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public float getFeelsLike_f() {
        return feelsLike_f;
    }

    public void setFeelsLike_f(float feelsLike_f) {
        this.feelsLike_f = feelsLike_f;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Weather(float temp_f, float temp_c, String img , float feelsLike_f,String conditions,String location,String relativeHumidity ,int pressure ,float precip_today_in,String forecast_URL,float latitude, float longitude, String elevation ){
        this.temp_f = temp_f;
        this.temp_c = temp_c;
        this.img = img;
        this.feelsLike_f= feelsLike_f;
        this.conditions=conditions;
        this.Location = location;
        this.relativeHumidity=relativeHumidity;
        this.pressure=pressure;
        this.precip_today_in=precip_today_in;
        this.forecast_URL= forecast_URL;
        this.latitude=latitude;
        this.longitude=longitude;
        this.elevation= elevation;
    }

    public float getTemp_f() {
        return temp_f;
    }

    public void setTemp_f(float temp_f) {
        this.temp_f = temp_f;
    }

    public float getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(float temp_c) {
        this.temp_c = temp_c;
    }
}
