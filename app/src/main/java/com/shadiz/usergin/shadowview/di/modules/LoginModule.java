package com.shadiz.usergin.shadowview.di.modules;

import com.shadiz.usergin.shadowview.login.LoginInteractor;
import com.shadiz.usergin.shadowview.login.LoginInteractorImpl;
import com.shadiz.usergin.shadowview.login.LoginPresenter;
import com.shadiz.usergin.shadowview.login.LoginPresenterImpl;
import com.shadiz.usergin.shadowview.login.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by oldman on 28.10.16.
 */
@Module
public class LoginModule {
    private LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    public LoginPresenter provideLoginPresenter(LoginView view, LoginInteractor interactor) {
        return new LoginPresenterImpl(view, interactor);
    }

    @Provides
    public LoginView provideView() {
        return view;
    }

    @Provides
    public LoginInteractor provideInteractor() {
        return new LoginInteractorImpl();
    }

}
