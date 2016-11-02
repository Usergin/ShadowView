package com.shadiz.usergin.shadowview.utils;

import com.shadiz.usergin.shadowview.api.ServerError;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oldman on 31.10.16.
 */

public class RxUtils {
    public static <T> Observable<T> wrapRetrofitCall(final Call<T> call) {
        return Observable.create(subscriber ->
        {
            final Response<T> execute;
            try {
                execute = call.execute();
            } catch (IOException e) {
                subscriber.onError(e);
                return;
            }

            if (execute.isSuccessful()) {
                subscriber.onNext(execute.body());
            } else {
                subscriber.onError(new ServerError(execute.errorBody()));
            }
        });
    }

    public static <T> Observable<T> wrapRetrofitInitialCall(final Call<T> call) {
        return Observable.create(subscriber ->
        {
            final Response<T> execute;
            try {
                execute = call.execute();
            } catch (IOException e) {
                subscriber.onError(e);
                return;
            }

            if (execute.isSuccessful()) {
                subscriber.onNext(execute.body());
            } else {
                subscriber.onError(new ServerError(execute.errorBody()));
            }
        });
    }
    public static <T> Observable<T> wrapAsync(Observable<T> observable) {
        return wrapAsync(observable, Schedulers.io());
    }

    public static <T> Observable<T> wrapAsync(Observable<T> observable, Scheduler scheduler) {
        return observable
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
