package com.shadiz.usergin.shadowview.di.modules;

import com.shadiz.usergin.shadowview.api.ServerApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by oldman on 25.10.16.
 */
@Module(includes = {RetrofitModule.class})
public class ApiModule {
    @Provides
    @Singleton
    public ServerApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(ServerApi.class);
    }
}
