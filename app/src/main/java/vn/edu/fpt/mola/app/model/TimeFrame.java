package vn.edu.fpt.mola.app.model;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by phuctran93 on 9/27/2016.
 */

public class TimeFrame {
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime fromTime;
    private LocalTime toTime;
    private boolean isDaily;
    private int weekly;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

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

    public boolean isDaily() {
        return isDaily;
    }

    public void setDaily(boolean daily) {
        isDaily = daily;
    }

    public int getWeekly() {
        return weekly;
    }

    public void setWeekly(int weekly) {
        this.weekly = weekly;
    }
}
