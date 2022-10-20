package jarvis.storage;

import java.sql.Time;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.Lesson;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonNotes;
import jarvis.model.TimePeriod;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
public abstract class JsonAdaptedLesson {
    private final String lessonType;
    // Identity fields
    private final String lessonDesc;
    private final TimePeriod timePeriod;

    // Data fields
    private final LessonAttendance attendance;
    private final LessonNotes notes;
    private boolean isCompleted;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("lessonType") String lessonType,
                             @JsonProperty("lessonDesc") String lessonDesc,
                             @JsonProperty("timePeriod") TimePeriod timePeriod,
                             @JsonProperty("attendance") LessonAttendance attendance,
                             @JsonProperty("notes") LessonNotes notes,
                             @JsonProperty("isCompleted") boolean isCompleted) {
        this.lessonType = lessonType;
        this.lessonDesc = lessonDesc;
        this.timePeriod = timePeriod;
        this.attendance = attendance;
        this.notes = notes;
        this.isCompleted = isCompleted;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        lessonType = source.getType();
        lessonDesc = source.getDesc().lessonDesc;
        timePeriod = source.getTimePeriod();
        attendance = source.getAttendance();
        notes = source.getNotes();
        isCompleted = source.isCompleted();
    }

    protected String getLessonDesc() {
        return lessonDesc;
    }

    protected TimePeriod getTimePeriod() {
        return timePeriod;
    }

    protected LessonAttendance getAttendance() {
        return attendance;
    }

    protected LessonNotes getNotes() {
        return notes;
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public abstract Lesson toModelType() throws IllegalValueException;
}

