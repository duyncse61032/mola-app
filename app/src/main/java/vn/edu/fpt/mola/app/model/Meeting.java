package vn.edu.fpt.mola.app.model;

import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuctran93 on 9/29/2016.
 */

public class Meeting implements Serializable {
    private long id;
    private UserPrincipal learner;
    private UserPrincipal teacher;
    private Course course;
    private Lesson lesson;
    private LocalDate meetingDate;
    private List<TimeSlot> slotList;

    public Meeting() {
        slotList = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserPrincipal getLearner() {
        return learner;
    }

    public void setLearner(UserPrincipal learner) {
        this.learner = learner;
    }

    public UserPrincipal getTeacher() {
        return teacher;
    }

    public void setTeacher(UserPrincipal teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDate meetingDate) {
        this.meetingDate = meetingDate;
    }

    public List<TimeSlot> getSlotList() {
        return slotList;
    }

    public void setSlotList(List<TimeSlot> slotList) {
        this.slotList = slotList;
    }

    public void addSlot(TimeSlot slot) {
        slotList.add(slot);
    }

    public void removeSlot(TimeSlot slot) {
        slotList.remove(slot);
    }
}
