package com.shadiz.usergin.shadowview.login;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.shadiz.usergin.shadowview.R;
import com.shadiz.usergin.shadowview.utils.Preferences;

import javax.inject.Inject;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginView{

    // UI references.
    private View mProgressView;
    private View mLoginFormView;
    private LoginPresenterImpl presenter;
    @Inject
    Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenterImpl(this);
        finish();
    }


    @Override
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

