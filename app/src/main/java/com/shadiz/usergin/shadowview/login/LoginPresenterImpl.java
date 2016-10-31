package com.shadiz.usergin.shadowview.login;

import android.content.SharedPreferences;
import android.util.Log;

import com.shadiz.usergin.shadowview.api.AuthUtils;
import com.shadiz.usergin.shadowview.api.Response;
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
    public LoginPresenterImpl(LoginView view, LoginInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
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
    public void onSetBaseInfoDev() {
        Log.d("Presenter", "Onsetbase");
//        if (view != null)
//            view.showProgress();
//        interactor.createAboutDev(this);
        view.showDialog();
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
        interactor.onSetIdAccount(id, this);

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
//        if(view != null) {
//            view.hideProgress();
//
//        }

//        Observable<Response> userObservable = RxUtils.wrapRetrofitCall(serverService.signIn(token))
//                .doOnNext(user -> AuthUtils.setToken(token));
//        RxUtils.wrapAsync(userObservable)
//                .subscribe(user -> {
//                    getViewState().hideProgress();
//                    getViewState().successSignIn();
//                }, exception -> {
//                    getViewState().hideProgress();
//                    getViewState().showError(exception.getMessage());
//                });

    }

    @Override
    public void onSetFirstSettings(boolean result) {
        interactor.onFindIdOnStorage(this);
    }

    @Override
    public void onResultIdOnStorage(int account) {
        if (account == -1 || view != null) {
            view.hideProgress();
            view.showDialog();
        }
    }

//    @Override
//    public void onFindIdOnStorage(int account) {
//        interactor.setFirstPref(this);
//    }
}
