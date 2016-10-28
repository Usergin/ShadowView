package com.shadiz.usergin.shadowview.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shadiz.usergin.shadowview.App;
import com.shadiz.usergin.shadowview.R;
import com.shadiz.usergin.shadowview.di.DaggerLoginComponent;
import com.shadiz.usergin.shadowview.di.modules.LoginModule;
import com.shadiz.usergin.shadowview.utils.Preferences;

import javax.inject.Inject;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginView{

    // UI references.
    private View mProgressView;
    private View mLoginFormView;
    @Inject
    LoginPresenterImpl presenter;
//    @Inject
//    Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this))
                .appComponent(((App)getApplication()).getAppComponent())
                .build().inject(this);
        presenter.onSetBaseInfoDev();
//        finish();
    }

    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showDialog() {

    }
}

