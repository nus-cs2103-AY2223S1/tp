package jarvis.testutil;

import static jarvis.testutil.TypicalStudents.getTypicalStudents;

import java.time.LocalDateTime;
import java.util.Collection;

import jarvis.model.Consult;
import jarvis.model.Lesson;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.LessonType;
import jarvis.model.MasteryCheck;
import jarvis.model.Student;
import jarvis.model.Studio;
import jarvis.model.StudioParticipation;
import jarvis.model.TimePeriod;

/**
 * A utility class to help with building Lesson objects.
 */
public class LessonBuilder {

    public static final LessonDesc DEFAULT_LESSON_DESC = null;
    public static final Collection<Student> DEFAULT_STUDENTS = getTypicalStudents();
    public static final LessonAttendance DEFAULT_LESSON_ATTENDANCE = new LessonAttendance(DEFAULT_STUDENTS);
    public static final StudioParticipation DEFAULT_STUDIO_PARTICIPATION = new StudioParticipation(DEFAULT_STUDENTS);
    public static final LessonNotes DEFAULT_LESSON_NOTES = new LessonNotes(getTypicalStudents());
    public static final TimePeriod DEFAULT_TIME_PERIOD = new TimePeriod(
            LocalDateTime.of(2022, 12, 12, 10, 0),
            LocalDateTime.of(2022, 12, 12, 12, 0));

    private LessonDesc lessonDesc;
    private Collection<Student> students;
    private LessonAttendance attendance;
    private StudioParticipation studioParticipation;
    private LessonNotes notes;
    private TimePeriod timePeriod;

    /**
     * Creates a {@code LessonBuilder} with the default details.
     */
    public LessonBuilder() {
        lessonDesc = DEFAULT_LESSON_DESC;
        students = DEFAULT_STUDENTS;
        attendance = DEFAULT_LESSON_ATTENDANCE;
        studioParticipation = DEFAULT_STUDIO_PARTICIPATION;
        notes = DEFAULT_LESSON_NOTES;
        timePeriod = DEFAULT_TIME_PERIOD;
    }

    /**
     * Builds the lesson with the given values.
     * @param lessonType Type of lesson.
     * @return The lesson with the given values.
     */
    public Lesson build(LessonType lessonType) {
        switch (lessonType) {
        case STUDIO:
            return new Studio(lessonDesc, timePeriod, students, attendance, notes, studioParticipation);
        case CONSULT:
            return new Consult(lessonDesc, timePeriod, students, attendance, notes);
        case MASTERY_CHECK:
            return new MasteryCheck(lessonDesc, timePeriod, students, attendance, notes);
        default:
            throw new IllegalArgumentException("Unknown lesson type");
        }
    }
}
