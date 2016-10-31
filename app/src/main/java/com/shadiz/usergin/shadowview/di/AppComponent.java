package com.shadiz.usergin.shadowview.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.Preference;

import com.shadiz.usergin.shadowview.boot.BootBroadcastReceiver;
import com.shadiz.usergin.shadowview.di.modules.AppModule;
import com.shadiz.usergin.shadowview.di.modules.LoginModule;
import com.shadiz.usergin.shadowview.login.LoginActivity;
import com.shadiz.usergin.shadowview.login.LoginInteractor;
import com.shadiz.usergin.shadowview.login.LoginInteractorImpl;
import com.shadiz.usergin.shadowview.login.LoginPresenterImpl;
import com.shadiz.usergin.shadowview.receiver.CallReceiver;
import com.shadiz.usergin.shadowview.receiver.message.SmsBroadcastReceiver;
import com.shadiz.usergin.shadowview.utils.Preferences;
import com.shadiz.usergin.shadowview.utils.WorkTimeDefiner;

import java.util.Calendar;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by oldman on 25.10.16.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
//    LoginComponent loginComponent(LoginModule loginModule);

    Context getContext();
    Preferences getPreferences();
    Resources getResources();
    Calendar getCalendar();

    void inject(LoginInteractor interactor);
    void inject(BootBroadcastReceiver bootBroadcastReceiver);
    void inject(SmsBroadcastReceiver smsBroadcastReceiver);
    void inject(WorkTimeDefiner workTimeDefiner);
    void inject(CallReceiver callReceiver);
}
