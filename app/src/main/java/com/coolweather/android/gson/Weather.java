package com.coolweather.android.gson;



import java.util.List;

public class Weather {

//
//    private String status;
//    private Basic basic;
//    private AQI aqi;
//    private Now now;
//    private Suggestion suggestion;
//    private List<Forecast> daily_forecast;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public Basic getBasic() {
//        return basic;
//    }
//
//    public void setBasic(Basic basic) {
//        this.basic = basic;
//    }
//
//    public AQI getAqi() {
//        return aqi;
//    }
//
//    public void setAqi(AQI aqi) {
//        this.aqi = aqi;
//    }
//
//    public Now getNow() {
//        return now;
//    }
//
//    public void setNow(Now now) {
//        this.now = now;
//    }
//
//    public Suggestion getSuggestion() {
//        return suggestion;
//    }
//
//    public void setSuggestion(Suggestion suggestion) {
//        this.suggestion = suggestion;
//    }
//
//    public List<Forecast> getDaily_forecast() {
//        return daily_forecast;
//    }
//
//    public void setDaily_forecast(List<Forecast> daily_forecast) {
//        this.daily_forecast = daily_forecast;
//    }

    private List<HeWeather> HeWeather ;

    public void setHeWeather(List<HeWeather> HeWeather){
        this.HeWeather = HeWeather;
    }
    public List<HeWeather> getHeWeather(){
        return this.HeWeather;
    }
}
