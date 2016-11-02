package com.shadiz.usergin.shadowview.api;

import com.shadiz.usergin.shadowview.api.response.FirstToken;
import com.shadiz.usergin.shadowview.api.response.Initial;
import com.shadiz.usergin.shadowview.model.DeviceInfo;
import com.shadiz.usergin.shadowview.model.FirstTokenModel;

import javax.inject.Inject;

import retrofit2.Call;
import rx.Observable;

/**
 * Created by oldman on 01.11.16.
 */

public class ApiService {
    private ServerApi serverApi;

    @Inject
    public ApiService(ServerApi serverApi) {
        this.serverApi = serverApi;
    }


    public Observable<FirstToken> getFirstToken(FirstTokenModel token) {
        return serverApi.getFirstToken(token.getGrantType(), token.getScope(), token.getClient_id(), token.getClient_secret());
    }

    public Observable<Initial> getInitialDevice(String firstToken, DeviceInfo deviceInfo) {
        return serverApi.getInitialDevice(firstToken, "application/json", deviceInfo.getAccount(), deviceInfo.getImei(), deviceInfo.getModel());
    }
}
