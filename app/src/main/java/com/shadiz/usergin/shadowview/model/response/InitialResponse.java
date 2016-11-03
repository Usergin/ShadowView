package com.shadiz.usergin.shadowview.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by oldman on 02.11.16.
 */

public class InitialResponse {
    public String getInitialCode() {
        return initialCode;
    }

    public void setInitialCode(String codeInitial) {
        this.initialCode = codeInitial;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
    @SerializedName("code_initial")
    @Expose
    private String initialCode;
    @SerializedName("device")
    @Expose
    private String device;
}
