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
    public static final LessonDesc DEFAULT_CONSULT_DESC = new LessonDesc("Consult on recursion");
    public static final LessonDesc DEFAULT_STUDIO_DESC = new LessonDesc("Studio 1");
    public static final LessonDesc DEFAULT_MASTERY_CHECK_DESC = new LessonDesc("Mastery Check 1");
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
     * Initializes the LessonBuilder with the data of {@code consultToCopy}
     */
    public LessonBuilder(Consult consultToCopy) {
        lessonDesc = consultToCopy.getDesc();
        students = consultToCopy.getStudentList();
        attendance = consultToCopy.getLessonAttendance();
        notes = consultToCopy.getLessonNotes();
        timePeriod = consultToCopy.getTimePeriod();
    }

    /**
     * Initializes the LessonBuilder with the data of {@code masteryCheckToCopy}
     */
    public LessonBuilder(MasteryCheck masteryCheckToCopy) {
        lessonDesc = masteryCheckToCopy.getDesc();
        students = masteryCheckToCopy.getStudentList();
        attendance = masteryCheckToCopy.getLessonAttendance();
        notes = masteryCheckToCopy.getLessonNotes();
        timePeriod = masteryCheckToCopy.getTimePeriod();
    }

    /**
     * Initializes the LessonBuilder with the data of {@code studioToCopy}
     */
    public LessonBuilder(Studio studioToCopy) {
        lessonDesc = studioToCopy.getDesc();
        students = studioToCopy.getStudentList();
        attendance = studioToCopy.getLessonAttendance();
        notes = studioToCopy.getLessonNotes();
        timePeriod = studioToCopy.getTimePeriod();
        studioParticipation = studioToCopy.getStudioParticipation();
    }

    /**
     * Sets the {@code LessonDesc} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withDesc(LessonDesc lessondesc) {
        this.lessonDesc = lessondesc;
        return this;
    }

    /**
     * Sets the {@code Students}, {@code LessonAttendance}, {@code LessonNotes} and {@code StudioParticipation}
     * of the {@code Lesson} that we are building.
     */
    public LessonBuilder withStudents(Collection<Student> students) {
        this.students = students;
        attendance = new LessonAttendance(students);
        notes = new LessonNotes(students);
        studioParticipation = new StudioParticipation(students);
        return this;
    }

    /**
     * Sets the {@code TimePeriod} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
        return this;
    }

    /**
     * Returns the {@code Consult} that we have built.
     */
    public Consult buildConsult() {
        return new Consult(lessonDesc, timePeriod, students, attendance, notes);
    }

    /**
     * Returns the {@code MasteryCheck} that we have built.
     */
    public MasteryCheck buildMasteryCheck() {
        return new MasteryCheck(lessonDesc, timePeriod, students, attendance, notes);
    }

    /**
     * Returns the {@code Studio} that we have built.
     */
    public Studio buildStudio() {
        return new Studio(lessonDesc, timePeriod, students, attendance, notes, studioParticipation);
    }
}
