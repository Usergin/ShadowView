package com.shadiz.usergin.shadowview.di;

import com.shadiz.usergin.shadowview.di.modules.ActivityScope;
import com.shadiz.usergin.shadowview.di.modules.LoginModule;
import com.shadiz.usergin.shadowview.login.LoginActivity;
import com.shadiz.usergin.shadowview.login.LoginInteractor;
import com.shadiz.usergin.shadowview.login.LoginInteractorImpl;
import com.shadiz.usergin.shadowview.login.LoginPresenter;
import com.shadiz.usergin.shadowview.login.LoginPresenterImpl;

import dagger.Component;

/**
 * Created by oldman on 27.10.16.
 */
@ActivityScope
@Component(modules = {LoginModule.class}, dependencies = AppComponent.class)
public interface LoginComponent extends AppComponent {
    LoginPresenter getPresenter();

    LoginInteractor getInteractor();

    void inject(LoginActivity loginActivity);

    void inject(LoginInteractorImpl interactor);

    void inject(LoginPresenterImpl presenter);
}
