package com.shadiz.usergin.shadowview;

import android.app.Application;

import com.shadiz.usergin.shadowview.di.AppComponent;
import com.shadiz.usergin.shadowview.di.DaggerAppComponent;
import com.shadiz.usergin.shadowview.di.LoginComponent;
import com.shadiz.usergin.shadowview.di.modules.AppModule;

/**
 * Created by oldman on 25.10.16.
 */

public class App extends Application {
    private static AppComponent appComponent;
    private static LoginComponent loginComponent;
    private static App context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        getAppComponent();
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            buildAppComponent();
        }
        return appComponent;
    }

    private static AppComponent buildAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(context))
                .build();
        return appComponent;
    }

}
