package com.shadiz.usergin.shadowview.login;

import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.shadiz.usergin.shadowview.App;
import com.shadiz.usergin.shadowview.model.DeviceInfo;
import com.shadiz.usergin.shadowview.utils.Preferences;
import com.shadiz.usergin.shadowview.utils.WalkTree;

import java.util.Calendar;
import java.util.TimeZone;

import javax.inject.Inject;

/**
 * Created by oldman on 26.10.16.
 */

public class LoginInteractorImpl implements LoginInteractor {
    @Inject
    Context context;
    @Inject
    Preferences preferences;

    @Inject
    public LoginInteractorImpl() {
        context = App.getAppComponent().getContext();
        preferences = App.getAppComponent().getPreferences();
    }


    @Override
    public void createAboutDev(OnAboutDeviceListener listener) {
         final TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String model = android.os.Build.MODEL;
        String androidVersion = android.os.Build.VERSION.RELEASE;
        String imei;

        if (manager.getDeviceId() != null) {
            imei = manager.getDeviceId(); // *** use for mobiles
        } else {
            imei = Settings.Secure.getString(context
                    .getContentResolver(), Settings.Secure.ANDROID_ID); // *** use for
            // tablets
        }
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long time = cal.getTimeInMillis();
        DeviceInfo dev = new DeviceInfo();
        try {
            dev.setBUILD(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
            dev.setImei(imei);
            dev.setModel(model);
            dev.setAbout(" Model: " + model + " Version android: " + androidVersion);
            dev.setTime(Long.toString(time));
            dev.setAccount("account");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            listener.onSetBaseInfoFinished(false);

        }

        preferences.setBaseDeviceInfo(dev);
        listener.onSetBaseInfoFinished(true);
    }

    @Override
    public void checkIsVisited(OnAboutDeviceListener listener) {
        listener.onVisited(preferences.isVisited());
    }

    @Override
    public void setFirstPref(OnAboutDeviceListener listener) {
        preferences.setFirstSettings();
        listener.onSetFirstSettings(true);
    }

    @Override
    public void onFindIdOnStorage(OnAboutDeviceListener listener) {
       listener.onResultIdOnStorage(WalkTree.findId());
    }

    @Override
    public void onSetHideIcon(boolean isHide) {
        Log.d("LoginInteractor", "hide icon " + isHide);
        preferences.setHideIcon(isHide);
    }

    @Override
    public void onSetIdAccount(int id, OnAboutDeviceListener listener) {
        preferences.setAccount(id);
        listener.onSetIdSuccess();
    }

}
