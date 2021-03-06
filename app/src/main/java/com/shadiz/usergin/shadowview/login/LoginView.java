package com.shadiz.usergin.shadowview.login;

/**
 * Created by oldman on 26.10.16.
 */

public interface LoginView {
    void showProgress();

    void hideProgress();

    void showDialog();

    void setVisibleSignInButton(boolean isVisible);

    void showSnackBar(String message);
}
