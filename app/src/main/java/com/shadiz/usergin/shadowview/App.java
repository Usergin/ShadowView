package com.shadiz.usergin.shadowview;

import android.app.Application;
import android.content.SharedPreferences;

import com.shadiz.usergin.shadowview.di.AppComponent;
import com.shadiz.usergin.shadowview.di.DaggerAppComponent;
import com.shadiz.usergin.shadowview.di.modules.AndroidModule;
import com.shadiz.usergin.shadowview.utils.Preferences;

/**
 * Created by oldman on 25.10.16.
 */

public class App extends Application {
    private static AppComponent sAppComponent;
    private static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
