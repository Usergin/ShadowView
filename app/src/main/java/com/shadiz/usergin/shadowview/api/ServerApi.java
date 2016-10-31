package com.shadiz.usergin.shadowview.api;

import com.shadiz.usergin.shadowview.model.FirstTokenModel;
import com.shadiz.usergin.shadowview.model.InitialModel;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by oldman on 25.10.16.
 */

public interface ServerApi {
    @POST("oauth/token")
    Observable<Response> getFirstToken(@Body FirstTokenModel firstTokenModel);
    @Headers({
            "Accept: application/json",
            "Authorization: Your-App-Name"
    })
    @POST("api/initial")
    Observable<Response> getTask(@Body InitialModel initialModel);
}
