package vn.edu.fpt.mola.app.model;

import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import vn.edu.fpt.mola.app.model.enumerate.CourseStatus;
import vn.edu.fpt.mola.app.model.enumerate.Degree;

public class Course implements Serializable
{
    private static final long serialVersionUID = 1L;
    private long id;
    private LocalDate createDate;
    private Degree degree;
    private String description;
    private CourseStatus state;
    private String title;
    private String topic;
    private Language language;
    private List<Chapter> chapterList;

    public Course()
    {
        chapterList = new ArrayList<>();
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public LocalDate getCreateDate()
    {
        return this.createDate;
    }

    public void setCreateDate(LocalDate createDate)
    {
        this.createDate = createDate;
    }

    public String getCreateDateString() {
        return createDate == null ? "" : createDate.toString("dd/MM/yyyy");
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

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    public void addChapter(Chapter chapter) {
        chapterList.add(chapter);
    }

    public void removeChapter(Chapter chapter) {
        chapterList.remove(chapter);
    }
}