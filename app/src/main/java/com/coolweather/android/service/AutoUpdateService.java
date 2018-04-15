package com.coolweather.android.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.coolweather.android.R;
import com.coolweather.android.WeatherActivity;
import com.coolweather.android.gson.Weather;
import com.coolweather.android.util.HttpUtil;
import com.coolweather.android.util.Utility;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends IntentService {
    private static final String TAG = "AutoUpdateService";

    public AutoUpdateService() {
        super("AutoUpdateService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        updateWeather();
        updateBingPic();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: AutoUpdateService运行");

        startLoop(intent);
        startNotification();

        return super.onStartCommand(intent, flags, startId);
    }

    private void startLoop(@Nullable Intent intent) {
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        int anHour = 8 * 60 * 60 * 1000;
        int anHour = 4 * 60 * 60 * 1000;
//        int anHour = 30 * 60 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void startNotification() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());


        Intent intent = new Intent(this, WeatherActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = new Notification.Builder(getApplicationContext())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("更新通知")
                    .setContentText("天气预报和背景图更新了 " + simpleDateFormat.format(date))
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build();
        }
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            NotificationManager manager = (NotificationManager) getSystemService(NotificationManager.class);
        manager.notify(1234, notification);
    }


    private void updateWeather() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);

        if (weatherString != null && !weatherString.isEmpty()) {
            Weather weather = Utility.handleWeatherResponse(weatherString);
            String weatherId = weather.getHeWeather().get(0).getBasic().getId();
            if (weatherId == null || weatherId.isEmpty()) {
                Log.d(TAG, "onResponse: 未选择城市 不更新天气");
                return;
            }

            String weatherUrl = "http://guolin.tech/api/weather?key=3d4c2cd353124bb69b14f748f1578763&cityid=" + weatherId;

            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    final String responseText = response.body().string();
                    final Weather weather = Utility.handleWeatherResponse(responseText);


                    if (weather != null && "ok".equals(weather.getHeWeather().get(0).getStatus())) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString("weather", responseText);
                        editor.apply();
                        Log.d(TAG, "onResponse: 更新天气成功");
                    } else
                        Log.d(TAG, "onResponse: 更新天气失败");

                }
            });

        }
        else
            Log.d(TAG, "onResponse: 未选择城市 不更新天气");

    }

    private void updateBingPic() {

        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                Log.d(TAG, "onResponse: 更新背景图成功");

            }
        });
    }


}
//
//import android.app.AlarmManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.IBinder;
//import android.os.SystemClock;
//import android.preference.PreferenceManager;
//import android.util.Log;
//
//import com.bumptech.glide.Glide;
//import com.coolweather.android.WeatherActivity;
//import com.coolweather.android.gson.Weather;
//import com.coolweather.android.util.HttpUtil;
//import com.coolweather.android.util.Utility;
//
//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.Response;
//
//public class AutoUpdateService extends Service {
//    private static final String TAG = "AutoUpdateService";
//    public AutoUpdateService() {
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.d(TAG, "onStartCommand: AutoUpdateService运行");
//        updateWeather();
//        updateBingPic();
//
//        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        int anHour = 8 * 60 * 60 * 1000;
//        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
//        Intent i = new Intent(this, AutoUpdateService.class);
//        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
//        manager.cancel(pi);
//        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    private void updateWeather() {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        String weatherString = prefs.getString("weather", null);
//
//        if (weatherString != null) {
//            Weather weather = Utility.handleWeatherResponse(weatherString);
//            String weatherId = weather.getHeWeather().get(0).getBasic().getId();
//            String weatherUrl = "http://guolin.tech/api/weather?key=3d4c2cd353124bb69b14f748f1578763&cityid=" + weatherId;
//
//            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//
//                    final String responseText = response.body().string();
//                    final Weather weather = Utility.handleWeatherResponse(responseText);
//
//
//                    if (weather != null && "ok".equals(weather.getHeWeather().get(0).getStatus())) {
//                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
//                        editor.putString("weather", responseText);
//                        editor.apply();
//                    }
//
//                }
//            });
//
//        }
//
//    }
//
//    private void updateBingPic() {
//
//        String requestBingPic = "http://guolin.tech/api/bing_pic";
//        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String bingPic = response.body().string();
//                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
//                editor.putString("bing_pic", bingPic);
//                editor.apply();
//            }
//        });
//    }
//
//
//}
