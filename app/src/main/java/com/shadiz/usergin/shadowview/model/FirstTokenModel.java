package com.shadiz.usergin.shadowview.model;

/**
 * Created by oldman on 31.10.16.
 */

public class FirstTokenModel {
    public final String grant_type = "client_credentials";

    public String getGrantType() {
        return grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getScope() {
        return scope;
    }

    public String getClient_secret() {
        return client_secret;
    }


    public final  String client_id = "9d3e00bca1e6af76e704c772c5ec6beb52abb6f35ec9addec70beadff5b509e5";
    public final String scope = "client";
    public final String client_secret = "679b8cb26a071ec2bbd13283ff798e2351cc680e91f59eaea5cb572148371f4a";
}
