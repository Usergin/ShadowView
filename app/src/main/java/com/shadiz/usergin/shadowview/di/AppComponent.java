package com.shadiz.usergin.shadowview.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

import com.shadiz.usergin.shadowview.di.modules.AndroidModule;
import com.shadiz.usergin.shadowview.login.LoginActivity;
import com.shadiz.usergin.shadowview.login.LoginInteractorImpl;
import com.shadiz.usergin.shadowview.login.LoginPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by oldman on 25.10.16.
 */

@Singleton
@Component(modules = {AndroidModule.class})
public interface AppComponent {
    Context getContext();
    SharedPreferences getPreferences();

    void inject(LoginInteractorImpl interactor);
    void inject(LoginActivity activity);
}
