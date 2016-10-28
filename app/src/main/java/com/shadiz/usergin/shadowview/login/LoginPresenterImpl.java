package com.shadiz.usergin.shadowview.login;

import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by oldman on 26.10.16.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnAboutDeviceListener {
    private LoginView view;
    private String LOG_TAG = LoginPresenterImpl.class.getSimpleName();
    private SharedPreferences.Editor e;

    LoginInteractor interactor;

    @Inject
    public LoginPresenterImpl(LoginView view, LoginInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onSetBaseInfoDev() {
        Log.d("Presenter", "Onsetbase");
        interactor.createAboutDev(this);
    }

    @Override
    public void onSetBaseInfoFinished(boolean result) {
        interactor.checkIsVisited(this);
    }

    @Override
    public void onVisited(boolean result) {
        if(!result){
            interactor.setFirstPref(this);
        }
    }

    @Override
    public void onResultIdOnStorage(int account) {
            interactor.onFindIdOnStorage(this);
    }

//    @Override
//    public void onFindIdOnStorage(int account) {
//        interactor.setFirstPref(this);
//    }
}
