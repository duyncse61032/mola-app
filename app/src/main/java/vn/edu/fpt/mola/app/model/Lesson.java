package vn.edu.fpt.mola.app.model;

import java.io.Serializable;

public class Lesson implements Serializable
{
    private static final long serialVersionUID = 1L;
    private long id;
    private String description;
    private int duration;
    private String title;

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

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getDuration()
    {
        return this.duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}