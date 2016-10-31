package com.shadiz.usergin.shadowview.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by oldman on 31.10.16.
 */

public class ServerError extends Throwable {
    public ServerError(ResponseBody responseBody) {
        super(getMessage(responseBody));
    }

    private static String getMessage(ResponseBody responseBody) {
        try {
            return new JSONObject(responseBody.string()).optString("message");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return "Unknown exception";
    }
}