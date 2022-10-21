package jarvis.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.Lesson;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonNotes;

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
    private final JsonAdaptedTimePeriod timePeriod;

    // Data fields
    private final LessonAttendance attendance;
    private final LessonNotes notes;
    private final boolean isCompleted;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("lessonDesc") String lessonDesc,
                             @JsonProperty("timePeriod") JsonAdaptedTimePeriod timePeriod,
                             @JsonProperty("attendance") LessonAttendance attendance,
                             @JsonProperty("notes") LessonNotes notes,
                             @JsonProperty("isCompleted") boolean isCompleted) {
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
        lessonDesc = source.getDesc().lessonDesc;
        timePeriod = new JsonAdaptedTimePeriod(source.getTimePeriod());
        attendance = source.getAttendance();
        notes = source.getNotes();
        isCompleted = source.isCompleted();
    }

    protected String getLessonDesc() {
        return lessonDesc;
    }

    protected JsonAdaptedTimePeriod getTimePeriod() {
        return timePeriod;
    }


    protected LessonAttendance getAttendance() {
        return attendance;
    }

    protected LessonNotes getNotes() {
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
    public abstract Lesson toModelType() throws IllegalValueException;
}

