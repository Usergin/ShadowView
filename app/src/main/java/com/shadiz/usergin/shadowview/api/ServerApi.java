package com.shadiz.usergin.shadowview.api;

import com.shadiz.usergin.shadowview.api.response.FirstToken;
import com.shadiz.usergin.shadowview.api.response.Initial;
import com.shadiz.usergin.shadowview.model.FirstTokenModel;
import com.shadiz.usergin.shadowview.model.InitialModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by oldman on 25.10.16.
 */

public interface ServerApi {
    @POST("/oauth/token")
    @FormUrlEncoded
    @Headers({
            "Accept: application/json"
    })
    Observable<FirstToken> getFirstToken(@Field("grant_type") String grantType,
                                   @Field("scope") String scope,
                                   @Field("client_id") String clientId,
                                   @Field("client_secret") String clientSecret);

    @Headers({
            "Accept: application/json",
    })
    @FormUrlEncoded
    @POST("/api/initial")
    Observable<Initial> getInitialDevice(@Header("Authorization") String autorization,
                                         @Header( "Accept") String accept,
                                   @Field("account") int account,
                                   @Field("imei") String imei,
                                   @Field("model") String model);
}
