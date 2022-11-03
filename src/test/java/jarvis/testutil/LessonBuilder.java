package jarvis.testutil;

import static jarvis.testutil.TypicalStudents.getTypicalStudents;

import java.time.LocalDateTime;
import java.util.Collection;

import jarvis.model.GradeProfile;
import jarvis.model.Lesson;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.MatricNum;
import jarvis.model.Student;
import jarvis.model.StudentName;
import jarvis.model.TimePeriod;

/**
 * A utility class to help with building Lesson objects.
 */
public class LessonBuilder {

    public static final LessonDesc DEFAULT_LESSON_DESC = null;
    public static final Collection<Student> DEFAULT_STUDENTS = getTypicalStudents();
    public static final LessonAttendance DEFAULT_LESSON_ATTENDANCE = new LessonAttendance(getTypicalStudents());
    public static final LessonNotes DEFAULT_LESSON_NOTES = new LessonNotes(getTypicalStudents());
    public static final TimePeriod DEFAULT_TIME_PERIOD = new TimePeriod(
            LocalDateTime.of(2022, 12, 12, 10, 0),
            LocalDateTime.of(2022, 12, 12, 12, 0));

    private LessonDesc lessonDesc;
    private Collection<Student> students;
    private LessonAttendance attendance;
    private LessonNotes notes;
    private TimePeriod timePeriod;

    /**
     * Creates a {@code LessonBuilder} with the default details.
     */
    public LessonBuilder() {
        lessonDesc = DEFAULT_LESSON_DESC;
        students = DEFAULT_STUDENTS;
        attendance = DEFAULT_LESSON_ATTENDANCE;
        notes = DEFAULT_LESSON_NOTES;
        timePeriod = DEFAULT_TIME_PERIOD;
    }

}
