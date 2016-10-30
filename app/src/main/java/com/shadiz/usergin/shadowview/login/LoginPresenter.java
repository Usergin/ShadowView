package com.shadiz.usergin.shadowview.login;

/**
 * Created by oldman on 26.10.16.
 */

public interface LoginPresenter {
    void onResume();

    void onPause();

    void onDestroy();

    void onFinish();

    void onSetBaseInfoDev();

    void onSetHideIcon(boolean isHide);

    void onSetId(int id);

}
