package com.shadiz.usergin.shadowview.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by oldman on 02.11.16.
 */

public class Initial {
    public String getCodeInitial() {
        return codeInitial;
    }

    public void setCodeInitial(String codeInitial) {
        this.codeInitial = codeInitial;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
    @SerializedName("code_initial")
    @Expose
    private String codeInitial;
    @SerializedName("device")
    @Expose
    private String device;
}
