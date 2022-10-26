package jarvis.storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jarvis.commons.core.index.Index;
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
    private final Set<Integer> studentIndexList;

    // Data fields
    private final String attendance;
    private final String notes;
    private final boolean isCompleted;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("lessonDesc") String lessonDesc,
                             @JsonProperty("startDateTime") LocalDateTime startDateTime,
                             @JsonProperty("endDateTime") LocalDateTime endDateTime,
                             @JsonProperty("studentIndexList") Set<Integer> studentIndexList,
                             @JsonProperty("attendance") String attendance,
                             @JsonProperty("notes") String notes,
                             @JsonProperty("isCompleted") boolean isCompleted) {
        this.lessonDesc = lessonDesc;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.studentIndexList = studentIndexList;
        this.attendance = attendance;
        this.notes = notes;
        this.isCompleted = isCompleted;
    }

    public JsonAdaptedLesson(LessonDesc lessonDesc, TimePeriod timePeriod, Set<Integer> studentIndexList,
                             LessonAttendance attendance, String notes,
                             boolean isCompleted) throws JsonProcessingException {
        this.lessonDesc = lessonDesc == null ? null : lessonDesc.lessonDesc;
        this.startDateTime = timePeriod.getStart();
        this.endDateTime = timePeriod.getEnd();
        this.studentIndexList = studentIndexList;
        this.attendance = attendance.toFullString();
        this.notes = notes;
        this.isCompleted = isCompleted;
    }

    /**
     * Constructs a {@code JsonAdaptedConsult}, {@code JsonAdaptedMasteryCheck} or
     * {@code JsonAdaptedStudio} with the given lesson details.
     *
     * @param lesson The given lesson, which could be a {@code Consult}, {@code MasteryCheck} or {@code Studio}.
     * @return The Jackson-friendly version of the lesson.
     */
    public static JsonAdaptedLesson createLesson(Lesson lesson, ReadOnlyStudentBook studentBook) throws JsonProcessingException {
        CollectionUtil.requireAllNonNull(lesson);
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

    protected Set<Integer> getStudentIndexList() {
        return studentIndexList;
    }

    protected String getAttendance() {
        return attendance;
    }

    protected String getNotes() {
        return notes;
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

