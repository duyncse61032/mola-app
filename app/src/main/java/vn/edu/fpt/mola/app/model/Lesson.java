package vn.edu.fpt.mola.app.model;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.io.Serializable;

public class Lesson implements Serializable
{
    private static final long serialVersionUID = 1L;
    private long id;
    private String title;
    private Period period;

    public Lesson()
    {
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Period getPeriod()
    {
        return this.period;
    }

    public void setPeriod(Period period)
    {
        this.period = period;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDurationString() {
        if (period == null) {
            return "";
        }
        PeriodFormatter hm = new PeriodFormatterBuilder()
                .printZeroAlways()
                .minimumPrintedDigits(2)
                .appendHours()
                .appendSeparator("h")
                .appendMinutes()
                .appendSeparator("m")
                .toFormatter();

        return hm.print(this.period);
    }
}