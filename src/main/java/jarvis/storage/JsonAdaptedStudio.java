package jarvis.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.Studio;
import jarvis.model.StudioParticipation;
import jarvis.model.TimePeriod;
import jarvis.model.util.SampleStudentUtil;

/**
 * Jackson-friendly version of {@link Studio}.
 */
public class JsonAdaptedStudio extends JsonAdaptedLesson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Studio's %s field is missing!";

    private final StudioParticipation studioParticipation;

    /**
     * Constructs a {@code JsonAdaptedStudio} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedStudio(@JsonProperty("lessonDesc") String lessonDesc,
                             @JsonProperty("timePeriod") JsonAdaptedTimePeriod timePeriod,
                             @JsonProperty("attendance") LessonAttendance attendance,
                             @JsonProperty("notes") LessonNotes notes,
                             @JsonProperty("isCompleted") boolean isCompleted,
                             @JsonProperty("participation") StudioParticipation studioParticipation) {
        super(lessonDesc, timePeriod, isCompleted);
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
/*
        if (this.getAttendance() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LessonAttendance.class.getSimpleName()));
        }

        if (this.getNotes() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LessonNotes.class.getSimpleName()));
        }*/

        if (studioParticipation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudioParticipation.class.getSimpleName()));
        }

        return new Studio(modelLessonDesc, this.getTimePeriod().toModelType(),
                new LessonAttendance(List.of(SampleStudentUtil.getSampleStudents())), studioParticipation,
                new LessonNotes(List.of(SampleStudentUtil.getSampleStudents())));
    }
}
