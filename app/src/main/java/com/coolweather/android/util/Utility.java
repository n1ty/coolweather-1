package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.City1;
import com.coolweather.android.db.County1;
import com.coolweather.android.db.Province1;
import com.coolweather.android.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 2018/5/31.
 */

public class Utility {
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces=new JSONArray(response);
                for (int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject=allProvinces.getJSONObject(i);
                    Province1 province=new Province1();
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.setProvinceName(provinceObject.getString("name"));
                    province.save();
                }return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;

    }
    public static boolean handleCityResponse(String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities=new JSONArray(response);
                for (int i=0;i<allCities.length();i++){
                    JSONObject cityObject=allCities.getJSONObject(i);
                    City1 city=new City1();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
   public static boolean handleCountyResponse(String response,int cityId){
       if (!TextUtils.isEmpty(response)){
           try {
               JSONArray allCountise=new JSONArray(response);
               for (int i=1;i<allCountise.length();i++){
                   JSONObject countyObject=allCountise.getJSONObject(i);
                   County1 county=new County1();
                   county.setCountyName(countyObject.getString("name"));
                   county.setWeatherId(countyObject.getString("weather_id"));
                   county.setCityId(cityId);
                   county.save();
               }
               return true;
           }catch (JSONException e){
               e.printStackTrace();
           }
       }return false;
   }
    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
