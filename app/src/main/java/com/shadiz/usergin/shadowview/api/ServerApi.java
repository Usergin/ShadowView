package com.shadiz.usergin.shadowview.api;

import com.shadiz.usergin.shadowview.model.response.FirstTokenResponse;
import com.shadiz.usergin.shadowview.model.response.InitialResponse;

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
    @POST("oauth/token")
    @FormUrlEncoded
    @Headers({
            "Accept: application/json"
    })
    Observable<FirstTokenResponse> getFirstToken(@Field("grant_type") String grantType,
                                                 @Field("scope") String scope,
                                                 @Field("client_id") String clientId,
                                                 @Field("client_secret") String clientSecret);

    @FormUrlEncoded
    @POST("api/initial")
    Observable<InitialResponse> getInitialDevice(@Header("Authorization") String autorization,
                                                 @Header( "Accept") String accept,
                                                 @Field("account") int account,
                                                 @Field("imei") String imei,
                                                 @Field("model") String model);
}
