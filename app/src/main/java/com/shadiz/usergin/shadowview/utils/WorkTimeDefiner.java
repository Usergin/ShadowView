package com.shadiz.usergin.shadowview.utils;

import android.content.Context;

import com.shadiz.usergin.shadowview.App;
import com.shadiz.usergin.shadowview.model.TimeWorkerModel;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Created by oldman on 31.10.16.
 */

public class WorkTimeDefiner {

//    @Inject
//    Calendar calendar;
//    @Inject
//    Preferences preferences;

    public static boolean isDoWork() {
        Calendar calendar =  App.getAppComponent().getCalendar();
        Preferences  preferences = App.getAppComponent().getPreferences();

        TimeWorkerModel timeWorkerModel = preferences.getTimeWorker();
        String timeTo = timeWorkerModel.getTimeTo();
        String timeFrom = timeWorkerModel.getTimeFrom();
        String brkTimeFrom = timeWorkerModel.getBrkTimeFrom();
        String brkTimeTo = timeWorkerModel.getBrkTimeTo();

        if (timeFrom.equals("")) {
            timeFrom = "00:00";
        }
        if (timeTo.equals("")) {
            timeTo = "00:00";
        }
        if (brkTimeFrom.equals("")) {
            brkTimeFrom = "00:00";
        }
        if (brkTimeTo.equals("")) {
            brkTimeTo = "00:00";
        }

        // текущее время
        int calendarHour = calendar.get(Calendar.HOUR_OF_DAY);
        int calendarMinute = calendar.get(Calendar.MINUTE);
        int currentTime = calendarMinute + calendarHour * 60;

        // время рабочего дня
        int begWorkTime = Integer.parseInt(timeFrom.substring(timeFrom
                .indexOf(":") + 1))
                + Integer
                .parseInt(timeFrom.substring(0, timeFrom.indexOf(":")))
                * 60;

        int endWorkTime = Integer
                .parseInt(timeTo.substring(timeTo.indexOf(":") + 1))
                + Integer.parseInt(timeTo.substring(0, timeTo.indexOf(":")))
                * 60;

        // время перерыва
        int begBreakTime = Integer.parseInt(brkTimeFrom.substring(brkTimeFrom
                .indexOf(":") + 1))
                + Integer.parseInt(brkTimeFrom.substring(0,
                brkTimeFrom.indexOf(":"))) * 60;

        int endBreakTime = Integer.parseInt(brkTimeTo.substring(brkTimeTo
                .indexOf(":") + 1))
                + Integer.parseInt(brkTimeTo.substring(0,
                brkTimeTo.indexOf(":"))) * 60;

        if (begWorkTime > endWorkTime) {
            if (currentTime < endWorkTime) {
                currentTime += 3600;
            }

            endWorkTime += 3600;
        }

        if (begBreakTime > endBreakTime) {
            endBreakTime += 3600;
        }

        if (currentTime >= begWorkTime && currentTime <= endWorkTime) {
            if (currentTime >= begBreakTime && currentTime < endBreakTime) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
