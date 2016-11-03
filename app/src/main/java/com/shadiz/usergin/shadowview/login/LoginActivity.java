package com.shadiz.usergin.shadowview.login;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

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
    private MaterialDialog progressView;
    private View mLoginFormView;
    @Inject
    LoginPresenterImpl presenter;
    @BindView(R.id.sign_in_button)
    Button signInButton;
    private final int PERMISSION_READ_STATE = 2121;


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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_STATE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted!
                    // you may now do the action that requires this permission
                    showDialog();
                } else {
                    // permission denied
                    Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

    @OnClick(R.id.sign_in_button)
    public void onSignInButtonClick() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATE);
        } else {
            //TODO
            showDialog();
        }

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
        progressView = new MaterialDialog.Builder(this)
                .content(R.string.please_wait)
                .progress(true, 0)
                .show();
    }

    @Override
    public void hideProgress() {
        if (progressView != null)
            progressView.dismiss();
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
                        presenter.onSetBaseInfoDev(Integer.parseInt(input.toString()));

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

    @Override
    public void showSnackBar(String message) {
//        Snackbar snackbar = Snackbar
//                .make(this, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);

//        snackbar.show();
    }

}

