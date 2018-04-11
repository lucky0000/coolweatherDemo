package com.coolweather.android.gson;

import java.util.List;

public class HeWeather {
    private Basic basic;

    //private Basic.Update update;

    private String status;

    private Now now;

    private List<Forecast> daily_forecast ;

    private AQI aqi;

    private Suggestion suggestion;

    public void setBasic(Basic basic){
        this.basic = basic;
    }
    public Basic getBasic(){
        return this.basic;
    }
//    public void setUpdate(Basic.Update update){
//        this.update = update;
//    }
//    public Basic.Update getUpdate(){
//        return this.update;
//    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setNow(Now now){
        this.now = now;
    }
    public Now getNow(){
        return this.now;
    }
    public void setDaily_forecast(List<Forecast> daily_forecast){
        this.daily_forecast = daily_forecast;
    }
    public List<Forecast> getDaily_forecast(){
        return this.daily_forecast;
    }
    public void setAqi(AQI aqi){
        this.aqi = aqi;
    }
    public AQI getAqi(){
        return this.aqi;
    }
    public void setSuggestion(Suggestion suggestion){
        this.suggestion = suggestion;
    }
    public Suggestion getSuggestion(){
        return this.suggestion;
    }

}