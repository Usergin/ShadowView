package com.shadiz.usergin.shadowview.model;

import java.io.Serializable;

/**
 * Created by oldman on 26.10.16.
 */

public class DeviceInfo implements Serializable {
    private String BUILD, imei, ABOUT, model, account;
    private static DeviceInfo devInstance = null; // the only instance of the class

    public DeviceInfo() {
    }
    public static DeviceInfo getInstance()
    {
        if (devInstance == null)
        {
            devInstance = new DeviceInfo();
        }
        return devInstance;
    }
    public String getBUILD() {
        return BUILD;
    }

    public void setBUILD(String BUILD) {
        this.BUILD = BUILD;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getABOUT() {
        return ABOUT;
    }

    public void setAbout(String ABOUT) {
        this.ABOUT = ABOUT;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}