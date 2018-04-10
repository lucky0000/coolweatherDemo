package com.coolweather.android.util;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.TypeReference;
import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.CityModel;


import java.util.ArrayList;


public class Utility {
    public static boolean handleProvinceResponse(String response) {
        if (TextUtils.isEmpty(response))
            return false;

        ArrayList<CityModel> items = JSON.parseObject(response, new TypeReference<ArrayList<CityModel>>(){});
        for (CityModel item : items) {
            Province p=new Province();
            p.setProvinceCode(item.getId());
            p.setProvinceName(item.getName());
            p.save();
        }
        return true;
    }

    public static boolean handleCityResponse(String response,int provinceId) {
        if (TextUtils.isEmpty(response))
            return false;

        ArrayList<CityModel> items = JSON.parseObject(response, new TypeReference<ArrayList<CityModel>>(){});
        for (CityModel item : items) {
            City p=new City();
            p.setCityCode(item.getId());
            p.setCityName(item.getName());
            p.setProvinceId(provinceId);
            p.save();
        }
        return true;
    }

    public static boolean handleCountyResponse(String response,int cityId) {
        if (TextUtils.isEmpty(response))
            return false;

        ArrayList<CityModel> items = JSON.parseObject(response, new TypeReference<ArrayList<CityModel>>(){});
        for (CityModel item : items) {
            County p=new County();
            p.setCountyName(item.getName());
            p.setWeatherId(item.getWeather_id());
            p.setCountyCode(item.getId());
            p.setCityId(cityId);
            p.save();
        }
        return true;
    }
}
