package vn.edu.fpt.mola.app.model;

import java.io.Serializable;
import java.util.Date;

import vn.edu.fpt.mola.app.model.enumerate.CourseStatus;
import vn.edu.fpt.mola.app.model.enumerate.Degree;

public class Course implements Serializable
{
    private static final long serialVersionUID = 1L;
    private long id;
    private Date createDate;
    private Degree degree;
    private String description;
    private CourseStatus state;
    private String title;
    private String topic;
    private Language language;

    public Course()
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

    public Date getCreateDate()
    {
        return this.createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public Degree getDegree()
    {
        return this.degree;
    }

    public void setDegree(Degree degree)
    {
        this.degree = degree;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public CourseStatus getState()
    {
        return this.state;
    }

    public void setState(CourseStatus state)
    {
        this.state = state;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTopic()
    {
        return this.topic;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }

    public Language getLanguage()
    {
        return this.language;
    }

    public void setLanguage(Language language)
    {
        this.language = language;
    }
}