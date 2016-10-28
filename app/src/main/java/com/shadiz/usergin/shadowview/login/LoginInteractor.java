package com.shadiz.usergin.shadowview.login;

/**
 * Created by oldman on 26.10.16.
 */

public interface LoginInteractor {
    void createAboutDev(OnAboutDeviceListener listener);

    void checkIsVisited(OnAboutDeviceListener listener);

    void setFirstPref(OnAboutDeviceListener listener);

    void onFindIdOnStorage(OnAboutDeviceListener listener);

    interface OnAboutDeviceListener {
        void onSetBaseInfoFinished(boolean success);
        void onVisited(boolean success);
        void onResultIdOnStorage(int account);
    }
}
