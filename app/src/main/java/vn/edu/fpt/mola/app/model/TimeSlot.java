package vn.edu.fpt.mola.app.model;

import org.joda.time.LocalTime;

import java.io.Serializable;

/**
 * Created by phuctran93 on 9/28/2016.
 */

public class TimeSlot implements Serializable {
    private LocalTime fromTime;
    private LocalTime toTime;

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TimeSlot)) {
            return false;
        }
        TimeSlot castOther = (TimeSlot) obj;
        return this.fromTime.equals(castOther.fromTime)
                && this.toTime.equals(castOther.toTime);
    }

    @Override
    public String toString() {
        return fromTime.toString("HH:mm") + " - " + toTime.toString("HH:mm");
    }
}
