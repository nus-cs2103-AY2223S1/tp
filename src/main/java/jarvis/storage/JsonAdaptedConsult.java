package jarvis.storage;

import com.fasterxml.jackson.annotation.JsonCreator;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.Consult;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.TimePeriod;

/**
 * Jackson-friendly version of {@link Consult}.
 */
public class JsonAdaptedConsult extends JsonAdaptedLesson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consult's %s field is missing!";

    /**
     * Constructs a {@code JsonAdaptedConsult} with the given consult details.
     */
    @JsonCreator
    public JsonAdaptedConsult(String lessonType, String lessonDesc, TimePeriod timePeriod, LessonAttendance attendance,
                              LessonNotes notes, boolean isCompleted) {
        super(lessonType, lessonDesc, timePeriod, attendance, notes, isCompleted);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Consult toModelType() throws IllegalValueException {
        if (this.getLessonDesc() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LessonDesc.class.getSimpleName()));
        }
        if (LessonDesc.isValidLessonDesc(this.getLessonDesc())) {
            throw new IllegalValueException(LessonDesc.MESSAGE_CONSTRAINTS);
        }
        final LessonDesc modelLessonDesc = new LessonDesc(this.getLessonDesc());

        if (this.getTimePeriod() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TimePeriod.class.getSimpleName()));
        }

        if (this.getAttendance() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LessonAttendance.class.getSimpleName()));
        }

        if (this.getNotes() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LessonNotes.class.getSimpleName()));
        }

        return new Consult(modelLessonDesc, this.getTimePeriod(), this.getAttendance(), this.getNotes());
    }
}
