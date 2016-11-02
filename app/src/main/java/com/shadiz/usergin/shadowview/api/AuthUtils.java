package com.shadiz.usergin.shadowview.api;

import com.shadiz.usergin.shadowview.App;
import com.shadiz.usergin.shadowview.utils.Preferences;

/**
 * Created by oldman on 31.10.16.
 */

public class AuthUtils {
    private static final String TOKEN = "token";

    public static String getToken() {
        return App.getAppComponent().getPreferences().getFirstToken();
    }

    public static void setToken(String token) {
        App.getAppComponent().getPreferences().setFirstToken(token);
    }
    public static void setDev(String token) {
        App.getAppComponent().getPreferences().setFirstToken(token);
    }
}
