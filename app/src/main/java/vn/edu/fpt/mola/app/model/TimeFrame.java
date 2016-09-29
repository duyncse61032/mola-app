package vn.edu.fpt.mola.app.model;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

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
    private List<TimeSlot> slotList;

    public TimeFrame() {
        slotList = new ArrayList<>();
    }

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

    public List<TimeSlot> getSlotList() {
        return slotList;
    }

    public void setSlotList(List<TimeSlot> slotList) {
        this.slotList = slotList;
    }

    public void addSlot(TimeSlot slot) {
        this.slotList.add(slot);
    }

    public void removeSlot(TimeSlot slot) {
        this.slotList.remove(slot);
    }

    public String getStartEndDate() {
        return startDate.toString("dd/MM/yyyy") + " - " + endDate.toString("dd/MM/yyyy");
    }

    public String getFromToTime() {
        return fromTime.toString("HH:mm") + " - " + toTime.toString("HH:mm");
    }

    public String getAgenda() {
        if (isDaily) return "Everyday";
        else {
            boolean sundayChecked = (weekly & 2) > 0;
            boolean mondayChecked = (weekly & 4) > 0;
            boolean tuesdayChecked = (weekly & 8) > 0;
            boolean wednesdayChecked = (weekly & 16) > 0;
            boolean thursdayChecked = (weekly & 32) > 0;
            boolean fridayChecked = (weekly & 64) > 0;
            boolean saturdayChecked = (weekly & 128) > 0;

            boolean weekendChecked = sundayChecked
                    && !mondayChecked
                    && !tuesdayChecked
                    && !wednesdayChecked
                    && !thursdayChecked
                    && !fridayChecked
                    && saturdayChecked;

            boolean weekdayChecked =  !sundayChecked
                    && mondayChecked
                    && tuesdayChecked
                    && wednesdayChecked
                    && thursdayChecked
                    && fridayChecked
                    && !saturdayChecked;

            if (weekdayChecked) return "Every Weekday";
            else if (weekendChecked) return "Every Weekend";
            else {
                String str = "Every ";
                if (sundayChecked) str += "Sunday, ";
                if (mondayChecked) str += "Monday, ";
                if (tuesdayChecked) str += "Tuesday, ";
                if (wednesdayChecked) str += "Wednesday, ";
                if (thursdayChecked) str += "Thursday, ";
                if (fridayChecked) str += "Friday, ";
                if (saturdayChecked) str += "Saturday, ";
                return str;
            }
        }
    }

    @Override
    public String toString() {
        return getStartEndDate() + " - " + getFromToTime() + " - " + getAgenda();
    }
}
