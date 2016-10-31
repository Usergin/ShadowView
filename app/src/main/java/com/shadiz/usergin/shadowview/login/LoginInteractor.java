package com.shadiz.usergin.shadowview.login;

/**
 * Created by oldman on 26.10.16.
 */

public interface LoginInteractor {
    void createAboutDev(OnAboutDeviceListener listener);

    void checkIsVisited(OnAboutDeviceListener listener);

    void setFirstPref(OnAboutDeviceListener listener);

    void onFindIdOnStorage(OnAboutDeviceListener listener);

    void onSetHideIcon(boolean isHide);

    void onSetIdAccount(int id, OnAboutDeviceListener listener);

    interface OnAboutDeviceListener {
        void onSetBaseInfoFinished(boolean result);
        void onVisited(boolean result);
        void onSetIdSuccess();
        void onSetFirstSettings(boolean result);
        void onResultIdOnStorage(int account);
    }
}
