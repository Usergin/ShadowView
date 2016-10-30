package com.shadiz.usergin.shadowview.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.afollestad.materialdialogs.MaterialDialog;
import com.shadiz.usergin.shadowview.App;
import com.shadiz.usergin.shadowview.R;
import com.shadiz.usergin.shadowview.di.DaggerLoginComponent;
import com.shadiz.usergin.shadowview.di.modules.LoginModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginView {

    // UI references.
    private View mProgressView;
    private View mLoginFormView;
    @Inject
    LoginPresenterImpl presenter;
    @BindView(R.id.sign_in_button)
    Button signInButton;


    //    @Inject
//    Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this))
                .appComponent(((App) getApplication()).getAppComponent())
                .build().inject(this);
//        presenter.onSetBaseInfoDev();
//        finish();
    }

    @OnClick(R.id.sign_in_button)
    public void onSignInButtonClick() {
        presenter.onSetBaseInfoDev();
    }

    @OnClick(R.id.hide_icon_checkBox)
    public void onHideIconClick(CheckBox checkBox) {
        presenter.onSetHideIcon(checkBox.isChecked());
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
        new MaterialDialog.Builder(this)
                .content(R.string.please_wait)
                .progress(true, 0)
                .show();
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.input_number_account)
                .titleColorRes(R.color.colorAccent)
                .inputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED)
                .inputRange(5, 5, getResources().getColor(R.color.colorPink))
                .input(R.string.input_number_account, R.string.empty, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        // Do something
                        Log.d("LoginActivity", input.toString());
                        presenter.onSetId(Integer.parseInt(input.toString()));
                    }
                }).show();
    }

    @Override
    public void setVisibleSignInButton(boolean isVisible) {
        if (isVisible)
            signInButton.setVisibility(View.VISIBLE);
        else
            signInButton.setVisibility(View.GONE);

    }

}

