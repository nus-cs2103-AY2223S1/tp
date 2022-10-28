package jarvis.storage;

import static jarvis.commons.util.JsonUtil.checkNullArgument;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jarvis.commons.util.CollectionUtil;
import jarvis.model.Consult;
import jarvis.model.Lesson;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.MasteryCheck;
import jarvis.model.Student;
import jarvis.model.Studio;
import jarvis.model.TimePeriod;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = JsonAdaptedConsult.class, name = "consult"),
    @JsonSubTypes.Type(value = JsonAdaptedMasteryCheck.class, name = "mastery check"),
    @JsonSubTypes.Type(value = JsonAdaptedStudio.class, name = "studio")
})
public abstract class JsonAdaptedLesson {
    // Identity fields
    private final String lessonDesc;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final List<JsonAdaptedStudent> studentList;

    // Data fields
    private final Map<Integer, Boolean> attendance;
    private final ArrayList<String> generalNotes;
    private final Map<Integer, ArrayList<String>> studentNotes;
    private final boolean isCompleted;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("lessonDesc") String lessonDesc,
                             @JsonProperty("startDateTime") LocalDateTime startDateTime,
                             @JsonProperty("endDateTime") LocalDateTime endDateTime,
                             @JsonProperty("studentList") List<JsonAdaptedStudent> studentList,
                             @JsonProperty("attendance") Map<Integer, Boolean> attendance,
                             @JsonProperty("generalNotes") ArrayList<String> generalNotes,
                             @JsonProperty("studentNotes") Map<Integer, ArrayList<String>> studentNotes,
                             @JsonProperty("isCompleted") boolean isCompleted) {
        this.lessonDesc = lessonDesc;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.studentList = studentList;
        this.attendance = attendance;
        this.generalNotes = generalNotes;
        this.studentNotes = studentNotes;
        this.isCompleted = isCompleted;
    }

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    public JsonAdaptedLesson(LessonDesc lessonDesc, TimePeriod timePeriod, List<Student> studentList,
                             Map<Integer, Boolean> attendance, ArrayList<String> generalNotes,
                             Map<Integer, ArrayList<String>> studentNotes, boolean isCompleted) {
        this.lessonDesc = lessonDesc == null ? null : lessonDesc.lessonDesc;
        this.startDateTime = timePeriod.getStart();
        this.endDateTime = timePeriod.getEnd();
        List<JsonAdaptedStudent> jsonAdaptedStudentList = new ArrayList<>();
        for (Student student : studentList) {
            jsonAdaptedStudentList.add(new JsonAdaptedStudent(student));
        }
        this.studentList = jsonAdaptedStudentList;
        this.attendance = attendance;
        this.generalNotes = generalNotes;
        this.studentNotes = studentNotes;
        this.isCompleted = isCompleted;
    }

    /**
     * Constructs a {@code JsonAdaptedConsult}, {@code JsonAdaptedMasteryCheck} or
     * {@code JsonAdaptedStudio} with the given lesson details.
     *
     * @param lesson The given lesson, which could be a {@code Consult}, {@code MasteryCheck} or {@code Studio}.
     * @return The Jackson-friendly version of the lesson.
     */
    public static JsonAdaptedLesson createLesson(Lesson lesson) {
        CollectionUtil.requireAllNonNull(lesson);
        assert (lesson instanceof Consult || lesson instanceof MasteryCheck) || lesson instanceof Studio;
        if (lesson instanceof Consult) {
            Consult consult = (Consult) lesson;
            return new JsonAdaptedConsult(consult);
        } else if (lesson instanceof MasteryCheck) {
            MasteryCheck masteryCheck = (MasteryCheck) lesson;
            return new JsonAdaptedMasteryCheck(masteryCheck);
        } else if (lesson instanceof Studio) {
            Studio studio = (Studio) lesson;
            return new JsonAdaptedStudio(studio);
        } else {
            throw new IllegalArgumentException();
        }
    }

    protected String getLessonDesc() {
        return lessonDesc;
    }

    protected LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    protected LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    protected List<JsonAdaptedStudent> getStudentList() {
        return studentList;
    }

    protected Map<Integer, Boolean> getAttendance() {
        return attendance;
    }

    protected ArrayList<String> getGeneralNotes() {
        return generalNotes;
    }

    protected Map<Integer, ArrayList<String>> getStudentNotes() {
        return studentNotes;
    }

    protected boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalArgumentException if there were any data constraints violated in the adapted lesson.
     */
    public abstract Lesson toModelType() throws IllegalArgumentException;

    /**
     * Creates an {@code LessonDesc} object with the given description.
     *
     * @param lessonDesc The given description.
     * @return The created object.
     */
    public static LessonDesc createModelLessonDesc(String lessonDesc) {
        return lessonDesc != null
                ? new LessonDesc(lessonDesc)
                : null;
    }

    /**
     * Creates an {@code TimePeriod} object with the given start and end times.
     *
     * @param missingFieldMsg The error message to be shown if any of the fields are missing.
     * @param start The start time.
     * @param end The end time.
     * @return The created object.
     */
    public static TimePeriod createModelTimePeriod(String missingFieldMsg, LocalDateTime start, LocalDateTime end) {
        checkNullArgument(TimePeriod.class, missingFieldMsg, start);
        checkNullArgument(TimePeriod.class, missingFieldMsg, end);

        return new TimePeriod(start, end);
    }

    /**
     * Creates an {@code List<Student>} object.
     *
     * @param missingFieldMsg The error message to be shown if any of the fields are missing.
     * @param jsonAdaptedStudentList The list of students in its Jackson adapted form.
     * @return The created object.
     */
    public static List<Student> createModelStudentList(String missingFieldMsg,
                                                       List<JsonAdaptedStudent> jsonAdaptedStudentList) {
        checkNullArgument(Student.class, missingFieldMsg, jsonAdaptedStudentList);

        return JsonAdaptedStudent.toModelList(jsonAdaptedStudentList);
    }

    /**
     * Creates an {@code LessonAttendance} object with the given attendance details.
     *
     * @param missingFieldMsg The error message to be shown if any of the fields are missing.
     * @param studentList The given list of students.
     * @param map The student attendance data mapped by student index.
     * @return The created object.
     */
    public static LessonAttendance createModelLessonAttendance(String missingFieldMsg, List<Student> studentList,
                                                               Map<Integer, Boolean> map) {
        checkNullArgument(LessonAttendance.class, missingFieldMsg, map);

        return new LessonAttendance(studentList, map);
    }

    /**
     * Creates an {@code LessonNotes} object with the given general and student notes.
     *
     * @param missingFieldMsg The error message to be shown if any of the fields are missing.
     * @param studentList The given list of students.
     * @param generalNotes The given general notes.
     * @param studentNotes The given student notes, mapped by student index.
     * @return The created object.
     */
    public static LessonNotes createModelLessonNotes(String missingFieldMsg, List<Student> studentList,
                                                     ArrayList<String> generalNotes,
                                                     Map<Integer, ArrayList<String>> studentNotes) {
        checkNullArgument(LessonNotes.class, missingFieldMsg, generalNotes);
        checkNullArgument(LessonNotes.class, missingFieldMsg, studentNotes);

        return new LessonNotes(studentList, generalNotes, studentNotes);
    }
}

