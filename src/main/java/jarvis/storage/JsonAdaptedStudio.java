package jarvis.storage;

import com.fasterxml.jackson.annotation.JsonCreator;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.Studio;
import jarvis.model.StudioParticipation;
import jarvis.model.TimePeriod;

/**
 * Jackson-friendly version of {@link Studio}.
 */
public class JsonAdaptedStudio extends JsonAdaptedLesson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Studio's %s field is missing!";

    private StudioParticipation studioParticipation;

    /**
     * Constructs a {@code JsonAdaptedStudio} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedStudio(String lessonType, String lessonDesc, TimePeriod timePeriod, LessonAttendance attendance,
                             LessonNotes notes, boolean isCompleted, StudioParticipation studioParticipation) {
        super(lessonType, lessonDesc, timePeriod, attendance, notes, isCompleted);
        this.studioParticipation = studioParticipation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Studio toModelType() throws IllegalValueException {
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

        if (studioParticipation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudioParticipation.class.getSimpleName()));
        }

        return new Studio(modelLessonDesc, this.getTimePeriod(), this.getAttendance(), studioParticipation,
                this.getNotes());
    }
}
