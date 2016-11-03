package com.shadiz.usergin.shadowview.api;

import com.shadiz.usergin.shadowview.model.response.FirstTokenResponse;
import com.shadiz.usergin.shadowview.model.response.InitialResponse;
import com.shadiz.usergin.shadowview.model.DeviceInfo;
import com.shadiz.usergin.shadowview.model.FirstTokenModel;

import javax.inject.Inject;

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


    public Observable<FirstTokenResponse> getFirstToken(FirstTokenModel token) {
        return serverApi.getFirstToken(token.getGrantType(), token.getScope(), token.getClient_id(), token.getClient_secret());
    }

    public Observable<InitialResponse> getInitialDevice(String firstToken, DeviceInfo deviceInfo) {
        return serverApi.getInitialDevice("Bearer " + firstToken, "application/json", deviceInfo.getAccount(), deviceInfo.getImei(), deviceInfo.getModel());
    }
}
