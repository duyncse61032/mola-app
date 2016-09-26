package vn.edu.fpt.mola.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Chapter implements Serializable
{
    private static final long serialVersionUID = 1L;
    private long id;
    private String description;
    private String title;
    private List<Lesson> lessonList;

    public Chapter()
    {
        lessonList = new ArrayList<>();
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

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public Lesson addLesson(Lesson lesson)
    {
        getLessonList().add(lesson);

        return lesson;
    }

    public Lesson removeLesson(Lesson lesson)
    {
        getLessonList().remove(lesson);

        return lesson;
    }
}