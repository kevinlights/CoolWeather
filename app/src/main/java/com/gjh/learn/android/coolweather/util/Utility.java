package com.gjh.learn.android.coolweather.util;

import android.text.TextUtils;

import com.gjh.learn.android.coolweather.db.City;
import com.gjh.learn.android.coolweather.db.Country;
import com.gjh.learn.android.coolweather.db.Province;
import com.gjh.learn.android.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObj = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObj.getString("name"));
                    province.setProvinceCode(provinceObj.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                LogUtil.error("handle province response error: %s", e);
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObj = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObj.getString("name"));
                    city.setCityCode(cityObj.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                LogUtil.error("handle city response error: %s", e);
            }
        }
        return false;
    }

    public static boolean handleCountryResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCountries = new JSONArray(response);
                for (int i = 0; i < allCountries.length(); i++) {
                    JSONObject countryObj = allCountries.getJSONObject(i);
                    Country country = new Country();
                    country.setCountryName(countryObj.getString("name"));
                    country.setWeatherId(countryObj.getString("weather_id"));
                    country.setCityId(cityId);
                    country.save();
                }
                return true;
            } catch (JSONException e) {
                LogUtil.error("handle country response error: %s", e);
            }
        }
        return false;
    }

    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (JSONException e) {
            LogUtil.error("handle weather response error: %s", e);
        }
        return null;
    }
}
