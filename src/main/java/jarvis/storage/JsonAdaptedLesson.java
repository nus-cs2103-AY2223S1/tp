package jarvis.storage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.commons.util.CollectionUtil;
import jarvis.model.Consult;
import jarvis.model.Lesson;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.MasteryCheck;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.Studio;
import jarvis.model.Student;
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
    public static JsonAdaptedLesson createLesson(Lesson lesson, ReadOnlyStudentBook studentBook) {
        CollectionUtil.requireAllNonNull(lesson);
        assert (lesson instanceof Consult || lesson instanceof MasteryCheck) || lesson instanceof Studio;
        if (lesson instanceof Consult) {
            Consult consult = (Consult) lesson;
            return new JsonAdaptedConsult(consult, studentBook);
        } else if (lesson instanceof MasteryCheck) {
            MasteryCheck masteryCheck = (MasteryCheck) lesson;
            return new JsonAdaptedMasteryCheck(masteryCheck, studentBook);
        } else if (lesson instanceof Studio) {
            Studio studio = (Studio) lesson;
            return new JsonAdaptedStudio(studio, studentBook);
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
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public abstract Lesson toModelType(ReadOnlyStudentBook studentBook) throws IllegalValueException, IOException;
}

