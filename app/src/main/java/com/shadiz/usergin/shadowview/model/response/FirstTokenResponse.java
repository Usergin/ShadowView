package com.shadiz.usergin.shadowview.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by oldman on 31.10.16.
 */
public class FirstTokenResponse {

    public String getAccessFirstToken() {
        return accessFirstToken;
    }

    public void setAccessFirstToken(String accessFirstToken) {
        this.accessFirstToken = accessFirstToken;
    }
    @SerializedName("access_token")
    @Expose
    private String accessFirstToken;
}
