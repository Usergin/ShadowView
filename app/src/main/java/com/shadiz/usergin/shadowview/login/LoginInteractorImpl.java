package com.shadiz.usergin.shadowview.login;

import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import com.shadiz.usergin.shadowview.App;
import com.shadiz.usergin.shadowview.model.DeviceInfo;
import com.shadiz.usergin.shadowview.utils.Preferences;

import javax.inject.Inject;

/**
 * Created by oldman on 26.10.16.
 */

public class LoginInteractorImpl implements LoginInteractor {
    @Inject
    Context context;
    @Inject
    Preferences preferences;

    public LoginInteractorImpl() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void createAboutDev() {
        final TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String model = android.os.Build.MODEL;
        String androidVersion = android.os.Build.VERSION.RELEASE;

        DeviceInfo dev = new DeviceInfo();
        try {
            dev.setBUILD(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
            dev.setImei(manager.getDeviceId());
            dev.setModel(model);
            dev.setAbout(" Model: " + model + " Version android: " + androidVersion);
            dev.setAccount(null);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        preferences.setBaseDeviceInfo(dev);
    }
}
