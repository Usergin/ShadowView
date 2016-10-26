package com.shadiz.usergin.shadowview.login;

import android.content.SharedPreferences;

/**
 * Created by oldman on 26.10.16.
 */

public class LoginPresenterImpl implements  LoginPresenter{
    private LoginView view;
    private String LOG_TAG = LoginPresenterImpl.class.getSimpleName();
    private SharedPreferences.Editor e;

    public LoginPresenterImpl(LoginView view) {
          this.view = view;
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
    public void onGetBaseInfoDev() {

    }

}
