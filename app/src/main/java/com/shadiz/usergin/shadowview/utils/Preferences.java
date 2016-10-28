package com.shadiz.usergin.shadowview.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.shadiz.usergin.shadowview.model.DeviceInfo;

import javax.inject.Inject;

/**
 * Created by oldman on 26.10.16.
 */

public class Preferences {
    public static final String PREFS_NAME = "MyPrefsFile";
    private final SharedPreferences prefs;
    private static final String visited = "is_visited";
    //-------------for begining request---------------------
    private static final String baseDeviceInfo = "base_device_info";
    private static final String hideIcon = "is_hide_icon";
    private static final String isAllDeviceInfo = "is_all_device_info";
    private static Gson gson;

    @Inject
    public Preferences(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, 0);
        gson = new Gson();
    }

    public boolean isVisited () {
        return prefs.getBoolean(visited, false);
    }
    public void setVisited() {
        prefs.edit().putBoolean(visited, true).apply();
    }

    public  DeviceInfo getBaseDeviceInfo() {
        String dev = prefs.getString(baseDeviceInfo, null);
        return  gson.fromJson(dev, DeviceInfo.class);
    }


    public void setBaseDeviceInfo(DeviceInfo dev) {
        String stringDev = gson.toJson(dev);
        prefs.edit().putString(baseDeviceInfo, stringDev).apply();
    }
    public  boolean isHideIcon() {
        return prefs.getBoolean(hideIcon, false) ;
    }
    public void setHideIcon(boolean isHide) {
          prefs.edit().putBoolean(hideIcon, isHide).apply();
    }

     public  boolean isAllDeviceInfo() {
         return prefs.getBoolean(isAllDeviceInfo, false) ;
    }
    public void setIsAllDeviceInfo(boolean isInfo) {
        prefs.edit().putBoolean(isAllDeviceInfo, isInfo).apply();
    }

    public void setFirstSettings(){
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean("hasVisited", true);
        edit.putBoolean("is_info", true);
        edit.putBoolean("hideIcon", false);
        edit.putString("period", "1"); // period must equal 10 min
        edit.putString("code", "-1");
        edit.commit();
    }


}
