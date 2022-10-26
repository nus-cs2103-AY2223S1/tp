package jarvis.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jarvis.commons.core.index.Index;
import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.Lesson;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.Student;
import jarvis.model.Studio;
import jarvis.model.StudioParticipation;
import jarvis.model.TimePeriod;
import jarvis.model.util.SampleStudentUtil;

/**
 * Jackson-friendly version of {@link Studio}.
 */
public class JsonAdaptedStudio extends JsonAdaptedLesson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Studio's %s field is missing!";

    private final String studioParticipation;

    /**
     * Constructs a {@code JsonAdaptedStudio} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedStudio(@JsonProperty("lessonDesc") String lessonDesc,
                             @JsonProperty("startDateTime") LocalDateTime startDateTime,
                             @JsonProperty("endDateTime") LocalDateTime endDateTime,
                             @JsonProperty("studentIndexList") Set<Integer> studentIndexList,
                             @JsonProperty("attendance") String attendance,
                             @JsonProperty("generalNotes") ArrayList<String> generalNotes,
                             @JsonProperty("isCompleted") boolean isCompleted,
                             @JsonProperty("studioParticipation") String studioParticipation) {
        super(lessonDesc, startDateTime, endDateTime, studentIndexList, attendance, generalNotes, isCompleted);
        this.studioParticipation = studioParticipation;
    }

    /**
     * Converts a given {@code Studio} into this class for Jackson use.
     */
    public JsonAdaptedStudio(Studio source, ReadOnlyStudentBook studentBook) throws JsonProcessingException {
        super(source.getDesc(), source.getTimePeriod(), studentBook.getIndexList(source.getStudents()),
                source.getAttendance(), source.getGeneralNotes(), source.isCompleted());
        studioParticipation = source.getParticipation().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Studio toModelType(ReadOnlyStudentBook studentBook) throws IllegalValueException {
        if (this.getLessonDesc() != null && !LessonDesc.isValidLessonDesc(this.getLessonDesc())) {
            throw new IllegalValueException(LessonDesc.MESSAGE_CONSTRAINTS);
        }
        final LessonDesc modelLessonDesc = this.getLessonDesc() != null
                ? new LessonDesc(this.getLessonDesc())
                : null;

        if (this.getStartDateTime() == null || this.getEndDateTime() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TimePeriod.class.getSimpleName()));
        }
        // check validity
        final TimePeriod modelTimePeriod = new TimePeriod(this.getStartDateTime(),
                this.getEndDateTime());

        if (this.getStudentIndexList() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Student.class.getSimpleName()));
        }
        Set<Student> modelStudentSet = studentBook.studentSetOf(getStudentIndexList());

        if (this.getAttendance() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LessonAttendance.class.getSimpleName()));
        }

        if (this.getGeneralNotes() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LessonNotes.class.getSimpleName()));
        }
        LessonNotes modelLessonNotes = new LessonNotes(modelStudentSet, this.getGeneralNotes());

        if (studioParticipation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudioParticipation.class.getSimpleName()));
        }

        Studio studio = new Studio(modelLessonDesc, modelTimePeriod, modelStudentSet, modelLessonNotes);
        if (this.isCompleted()) {
            studio.markAsCompleted();
        }
        return studio;
    }
}
