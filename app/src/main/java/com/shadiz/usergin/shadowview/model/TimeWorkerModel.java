package com.shadiz.usergin.shadowview.model;

import java.io.Serializable;

/**
 * Created by oldman on 31.10.16.
 */

public class TimeWorkerModel implements Serializable {
    String timeFrom;
    String timeTo;
    String brkTimeFrom;
    String brkTimeTo;

    public String getBrkTimeTo() {
        return brkTimeTo;
    }

    public void setBrkTimeTo(String brkTimeTo) {
        this.brkTimeTo = brkTimeTo;
    }

    public String getBrkTimeFrom() {
        return brkTimeFrom;
    }

    public void setBrkTimeFrom(String brkTimeFrom) {
        this.brkTimeFrom = brkTimeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }
}
