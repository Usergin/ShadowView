package com.shadiz.usergin.shadowview.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.shadiz.usergin.shadowview.App;
import com.shadiz.usergin.shadowview.utils.Preferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by oldman on 25.10.16.
 */
@Module
public class AndroidModule {
    private App app;

    public AndroidModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app;
    }
    @Provides
    @Singleton
    public SharedPreferences providesPreference() {
        return  PreferenceManager.getDefaultSharedPreferences(app);
    }
    @Provides
    @Singleton
    public Preferences providesMyPreference() {
        return  Preferences.getInstance(app);
    }
}
