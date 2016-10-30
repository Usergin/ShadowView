package com.shadiz.usergin.shadowview.boot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import com.shadiz.usergin.shadowview.R;
import com.shadiz.usergin.shadowview.utils.Logger;
import com.shadiz.usergin.shadowview.utils.Preferences;

import javax.inject.Inject;

/**
 * Created by OldMan on 31.10.2016.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {
    private final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";

    @Inject
    Context context;
    @Inject
    Preferences preferences;
    @Inject
    Resources resources;


    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.doLog("BootBroadCastReceiver", "onReceive", "onReceive");
//        if (AppSettings.getState(0, context) == 0) {
//            ServiceControl.startRequest4(context);
//            return;
//        }
//        String action = intent.getAction();
//        if (action.equalsIgnoreCase(BOOT_ACTION)) {
//            sendRequest(resources.getString(R.string.boot));
//            // send info request
//            RequestList.sendInfoDeviceRequest(mContext);
//            // for Service
//            ServiceControl.runService(mContext);
//
//        }
//        if (action.equalsIgnoreCase(Intent.ACTION_REBOOT)) {
//            sendRequest(resources.getString(R.string.reboot));
//
//        }
//        if (action.equalsIgnoreCase(Intent.ACTION_SHUTDOWN)) {
//            sendRequest(resources.getString(R.string.shutdown));
//
//        }
    }

    private void sendRequest(String str) {
//        Map<String, Object> device = new HashMap<String, Object>();
//        Map<String, String> info = new HashMap<String, String>();
//        device.put("type", AppConstants.TYPE_SERVICE_REQUEST);
//        device.put("time", ConvertDate.logTime());
//        info.put("area", resources.getString(R.string.device));
//        info.put("event", str);
//        device.put("info", info);
//
//        RequestList.sendDataRequest(device, null, mContext);

    }
}