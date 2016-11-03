package com.shadiz.usergin.shadowview.login;

import android.content.SharedPreferences;
import android.util.Log;

import com.shadiz.usergin.shadowview.api.ApiService;
import com.shadiz.usergin.shadowview.api.AuthUtils;
import com.shadiz.usergin.shadowview.model.response.FirstTokenResponse;
import com.shadiz.usergin.shadowview.model.response.InitialResponse;
import com.shadiz.usergin.shadowview.model.DeviceInfo;
import com.shadiz.usergin.shadowview.model.FirstTokenModel;
import com.shadiz.usergin.shadowview.utils.RxUtils;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by oldman on 26.10.16.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnAboutDeviceListener {
    private LoginView view;
    private String LOG_TAG = LoginPresenterImpl.class.getSimpleName();
    private SharedPreferences.Editor e;

    LoginInteractor interactor;
    @Inject
    ApiService apiService;

    @Inject
    public LoginPresenterImpl(LoginView view, LoginInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
//        apiService = App.getAppComponent().getApiService();
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
    public void onSetBaseInfoDev(int id) {
        Log.d("Presenter", "Onsetbase");
        if (view != null)
            view.showProgress();
        interactor.createAboutDev(id, this);
    }

    @Override
    public void onSetHideIcon(boolean isHide) {
        interactor.onSetHideIcon(isHide);
    }

    @Override
    public void onSetId(int id) {
        if (view != null) {
            view.setVisibleSignInButton(false);
            view.showDialog();
        }
        interactor.createAboutDev(id, this);

    }

    @Override
    public void onSetBaseInfoFinished(boolean result) {
        interactor.checkIsVisited(this);
    }

    @Override
    public void onVisited(boolean result) {
        if (!result) {
            interactor.setFirstPref(this);
        }
    }

    @Override
    public void onSetIdSuccess() {
        FirstTokenModel tokenModel = new FirstTokenModel();
        DeviceInfo info = interactor.onGetDeviceInfo();
        Observable<FirstTokenResponse> firstTokenObservable = apiService.getFirstToken(tokenModel).doOnNext(response -> AuthUtils.setToken(response.getAccessFirstToken()));
        Observable<InitialResponse> initialObservable = apiService.getInitialDevice(AuthUtils.getToken(), info).doOnNext(response -> System.out.println(response.getDevice()));

        RxUtils.wrapAsync(firstTokenObservable)
                .subscribe(user -> {
                    Log.d(LOG_TAG, "onSetIdSuccess " + user.getAccessFirstToken());
                    RxUtils.wrapAsync(initialObservable)
                            .subscribe(device -> {
                                Log.d(LOG_TAG, "onSetDeviceSuccess " + device.getDevice());
                                view.hideProgress();
                                view.setVisibleSignInButton(false);
                                interactor.onSetInitialInfo(device, this);
                            }, exception -> {
                                view.showDialog();
                                Log.d(LOG_TAG, "onSetDeviceFailed " + exception);
                            });
                }, exception -> {

                    view.showDialog();
                    Log.d(LOG_TAG, "onSetIdFailed " + exception);
                });

    }

    private void getInitialRequest(String token) {
//        Observable<ResponseModel> initialObservable = RxUtils.wrapRetrofitCall(apiService.getInitialDevice(token, interactor.onGetDeviceInfo()))
//                .doOnNext(response -> AuthUtils.setToken(response.getAccessFirstToken()));

    }

    @Override
    public void onSetFirstSettings(boolean result) {

//        interactor.onFindIdOnStorage(this);
    }

    @Override
    public void onResultIdOnStorage(int account) {
        if (account == -1 || view != null) {
            view.hideProgress();
            view.showDialog();
        }
    }

    @Override
    public void onInitialInfoSuccess() {

    }

//    @Override
//    public void onFindIdOnStorage(int account) {
//        interactor.setFirstPref(this);
//    }
}
