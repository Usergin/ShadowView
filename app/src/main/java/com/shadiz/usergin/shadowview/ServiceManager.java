package com.shadiz.usergin.shadowview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.shadiz.usergin.shadowview.receiver.location.LocationTracker;
import com.shadiz.usergin.shadowview.receiver.message.SentSmsObserver;
import com.shadiz.usergin.shadowview.utils.Logger;

import javax.inject.Inject;

/**
 * Created by oldman on 31.10.16.
 */

public class ServiceManager {

    @Inject
    Context context;
//    private ApiService serverApi;

    private static String LOG_TAG = ServiceManager.class.getSimpleName()
            .toString();

//    public ServiceManager(ApiService serverApi) {
//        this.serverApi = serverApi;
//    }


//    public Observable<Response> signIn(FirstTokenModel firstTokenModel) {
//        return ServerApiService.getFirstToken(firstTokenModel);
//    }
//
//    public Call<List<Repository>> getUserRepos(String user, int page, Integer pageSize) {
//        return mGithubApi.getUserRepos(user, page, pageSize);
//    }


    public void runSMSObserver() {
        SentSmsObserver observer = new SentSmsObserver(null);
        context.getContentResolver().registerContentObserver(Uri.parse("content://sms"), true, observer);
//        SmsBroadcastReceiver.regSmsObserver();
    }

    public static void runLocation(Context mContext) {
        Logger.doLog(LOG_TAG, "runLocation", "runLocation");
        Intent locServiceIntent = new Intent(mContext, LocationTracker.class);
        mContext.startService(locServiceIntent);
//        Intent recognitionServiceIntent = new Intent(mContext,
//                RecognitionDevService.class);
//        mContext.startService(recognitionServiceIntent);
    }

    public static void startRequest4(Context mContext) {
//        mContext.startService(new Intent(mContext, Request4.class));
    }
}
