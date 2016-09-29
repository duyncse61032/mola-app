package vn.edu.fpt.mola.app.controller.teacher.dummy;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import vn.edu.fpt.mola.app.model.Chapter;
import vn.edu.fpt.mola.app.model.Course;
import vn.edu.fpt.mola.app.model.Language;
import vn.edu.fpt.mola.app.model.Lesson;
import vn.edu.fpt.mola.app.model.Meeting;
import vn.edu.fpt.mola.app.model.TimeFrame;
import vn.edu.fpt.mola.app.model.UserPrincipal;
import vn.edu.fpt.mola.app.model.enumerate.CourseStatus;
import vn.edu.fpt.mola.app.model.enumerate.Degree;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Course> COURSE_LIST = new ArrayList<Course>();
    public static final List<Chapter> CHAPTER_LIST = new ArrayList<Chapter>();
    public static final List<Lesson> LESSON_LIST = new ArrayList<Lesson>();
    public static final List<TimeFrame> TIME_FRAME_LIST = new ArrayList<>();
    public static final List<Meeting> MEETINGS_LIST = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Long, Course> COURSE_MAP = new HashMap<Long, Course>();
    public static final Map<Long, Chapter> CHAPTER_MAP = new HashMap<Long, Chapter>();
    public static final Map<Long, Lesson> LESSON_MAP = new HashMap<Long, Lesson>();
    public static final Map<Long, TimeFrame> TIME_FRAME_MAP = new HashMap<>();
    public static final Map<Long, Meeting> MEETING_MAP = new HashMap<>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addLesson(createDummyLesson("Lesson Example", i));
            addChapter(createDummyChapter("Chapter Example", i));
            addCourse(createDummyCourse("Course Example", i));
        }
        for (int i = 1; i <= 5; i++) {
            addMeeting(createDummyMeeting(i));
        }
    }

    private static void addMeeting(Meeting meeting) {
        MEETINGS_LIST.add(meeting);
        MEETING_MAP.put(meeting.getId(), meeting);
    }

    private static void addCourse(Course course) {
        COURSE_LIST.add(course);
        COURSE_MAP.put(course.getId(), course);
        for (Chapter c : CHAPTER_LIST) {
            course.addChapter(c);
        }
    }

    private static void addChapter(Chapter chapter) {
        CHAPTER_LIST.add(chapter);
        CHAPTER_MAP.put(chapter.getId(), chapter);
        for (Lesson l : LESSON_LIST) {
            chapter.addLesson(l);
        }
    }

    private static void addLesson(Lesson lesson) {
        LESSON_LIST.add(lesson);
        LESSON_MAP.put(lesson.getId(), lesson);
    }

    private static void addItem(
            List<DummyItem> dummyItemList,
            Map<String, DummyItem> dummyItemMap,
            DummyItem item) {
        dummyItemList.add(item);
        dummyItemMap.put(item.id, item);
    }

    private static Meeting createDummyMeeting(int i) {
        Random random = new Random();
        int coursePosition = random.nextInt(COURSE_LIST.size());
        Course course = COURSE_LIST.get(coursePosition);
        Chapter chapter = new Chapter();
        Lesson lesson = new Lesson();
        if (course.getChapterList().size() > 0) {
            int chapterPosition = random.nextInt(course.getChapterList().size());
            chapter = course.getChapterList().get(chapterPosition);
            if (chapter.getLessonList().size() > 0) {
                int lessonPosition = random.nextInt(chapter.getLessonList().size());
                lesson = chapter.getLessonList().get(lessonPosition);
            }
        }

        UserPrincipal learner = new UserPrincipal();
        learner.setFirstName("Adam");
        learner.setLastName("Smith");
        learner.setUsername("adam.smith");
        UserPrincipal teacher = new UserPrincipal();
        teacher.setFirstName("Same");
        teacher.setLastName("Will");
        teacher.setUsername("same.will");

        Meeting meeting = new Meeting();
        meeting.setId(i);
        meeting.setCourse(course);
        meeting.setLesson(lesson);
        meeting.setLearner(learner);
        meeting.setTeacher(teacher);

        return meeting;
    }

    private static Course createDummyCourse(String name, int position) {
        Course c = new Course();
        c.setId(position);
        c.setTitle(name);
        if (position % 3 == 0) c.setDegree(Degree.BEGINNER);
        else if (position % 3 == 1) c.setDegree(Degree.INTERMEDIATE);
        else if (position % 3 == 2) c.setDegree(Degree.ADVANCED);
        c.setCreateDate(new LocalDate());
        Language english = new Language();
        english.setId(57);
        english.setEnglishName("English");
        c.setLanguage(english);
        c.setState(CourseStatus.DRAFT);
        c.setDescription(makeDetails("Course", position));
        return c;
    }

    private static Chapter createDummyChapter(String name, int position) {
        Chapter c = new Chapter();
        c.setId(position);
        c.setTitle(name);
        c.setDescription(makeDetails("Chapter", position));
        return c;
    }

    private static Lesson createDummyLesson(String name, int position) {
        Lesson l = new Lesson();
        l.setId(position);
        l.setTitle(name);
        l.setPeriod(new Period(0, 15, 0, 0));
        return l;
    }

    private static DummyItem createDummyItem(String name, int position) {
        return new DummyItem(String.valueOf(position), "Course " + position, makeDetails("Item", position));
    }

    private static String makeDetails(String name, int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about " + name + ": ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
