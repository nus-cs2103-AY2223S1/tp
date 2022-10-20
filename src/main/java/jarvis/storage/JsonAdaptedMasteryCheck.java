package jarvis.storage;

import com.fasterxml.jackson.annotation.JsonCreator;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.MasteryCheck;
import jarvis.model.TimePeriod;

/**
 * Jackson-friendly version of {@link MasteryCheck}.
 */
public class JsonAdaptedMasteryCheck extends JsonAdaptedLesson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consult's %s field is missing!";

    /**
     * Constructs a {@code JsonAdaptedMasteryCheck} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedMasteryCheck(String lessonType, String lessonDesc, TimePeriod timePeriod,
                                   LessonAttendance attendance, LessonNotes notes, boolean isCompleted) {
        super(lessonType, lessonDesc, timePeriod, attendance, notes, isCompleted);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MasteryCheck toModelType() throws IllegalValueException {
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

        return new MasteryCheck(modelLessonDesc, this.getTimePeriod(), this.getAttendance(), this.getNotes());
    }
}
