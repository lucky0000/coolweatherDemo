package com.coolweather.android.util;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.TypeReference;
import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.CityModel;
import com.coolweather.android.gson.Weather;


import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class Utility {
    public static boolean handleProvinceResponse(String response) {
        if (TextUtils.isEmpty(response))
            return false;

        ArrayList<CityModel> items = JSON.parseObject(response, new TypeReference<ArrayList<CityModel>>() {
        });
        for (CityModel item : items) {

            //判断是否添加过
            List<Province> provinceList = DataSupport.where("provinceCode=?", String.valueOf(item.getId())).find(Province.class);
            if (provinceList.size() > 0) {
                //添加过 不再重复添加
                continue;
            } else {
                Province p = new Province();
                p.setProvinceCode(item.getId());
                p.setProvinceName(item.getName());
                p.save();
            }
        }
        return true;
    }

    public static boolean handleCityResponse(String response, int provinceId) {
        if (TextUtils.isEmpty(response))
            return false;

        ArrayList<CityModel> items = JSON.parseObject(response, new TypeReference<ArrayList<CityModel>>() {
        });
        for (CityModel item : items) {
            //判断是否添加过
            List<City> list = DataSupport.where("cityCode=?", String.valueOf(item.getId())).find(City.class);
            if (list.size() > 0) {
                //添加过 不再重复添加
                continue;
            } else {
                City p = new City();
                p.setCityCode(item.getId());
                p.setCityName(item.getName());
                p.setProvinceId(provinceId);
                p.save();
            }
        }
        return true;
    }

    public static boolean handleCountyResponse(String response, int cityId) {
        if (TextUtils.isEmpty(response))
            return false;

        ArrayList<CityModel> items = JSON.parseObject(response, new TypeReference<ArrayList<CityModel>>() {
        });
        for (CityModel item : items) {
            List<County> list = DataSupport.where("countyCode=?", String.valueOf(item.getId())).find(County.class);
            if (list.size() > 0) {
                //添加过 不再重复添加
                continue;
            } else {
                County p = new County();
                p.setCountyName(item.getName());
                p.setWeatherId(item.getWeather_id());
                p.setCountyCode(item.getId());
                p.setCityId(cityId);
                p.save();
            }
        }
        return true;
    }


    public static Weather handleWeatherResponse(String response) {
        Weather weather = JSON.parseObject(response, Weather.class);
        return weather;
    }
}
