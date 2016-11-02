package com.shadiz.usergin.shadowview.di.modules;

import com.shadiz.usergin.shadowview.api.ApiService;
import com.shadiz.usergin.shadowview.api.ServerApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by oldman on 01.11.16.
 */
@Module(includes = {ApiModule.class})
public class ApiServiceModule {
    @Provides
    @Singleton
    public ApiService provideApiService(ServerApi authApi) {
        return new ApiService(authApi);
    }
}
