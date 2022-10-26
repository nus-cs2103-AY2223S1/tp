package jarvis.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import jarvis.commons.core.index.Index;
import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.Consult;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.MasteryCheck;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.Student;
import jarvis.model.TimePeriod;
import jarvis.model.util.SampleStudentUtil;

/**
 * Jackson-friendly version of {@link MasteryCheck}.
 */
public class JsonAdaptedMasteryCheck extends JsonAdaptedLesson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consult's %s field is missing!";

    /**
     * Constructs a {@code JsonAdaptedMasteryCheck} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedMasteryCheck(@JsonProperty("lessonDesc") String lessonDesc,
                                   @JsonProperty("startDateTime") LocalDateTime startDateTime,
                                   @JsonProperty("endDateTime") LocalDateTime endDateTime,
                                   @JsonProperty("studentIndexList") Set<Integer> studentIndexList,
                                   @JsonProperty("attendance") String attendance,
                                   @JsonProperty("generalNotes") ArrayList<String> generalNotes,
                                   @JsonProperty("isCompleted") boolean isCompleted) {
        super(lessonDesc, startDateTime, endDateTime, studentIndexList, attendance, generalNotes, isCompleted);
    }

    /**
     * Converts a given {@code MasteryCheck} into this class for Jackson use.
     */
    public JsonAdaptedMasteryCheck(MasteryCheck source, ReadOnlyStudentBook studentBook) throws JsonProcessingException {
        super(source.getDesc(), source.getTimePeriod(), studentBook.getIndexList(source.getStudents()),
                source.getAttendance(), source.getGeneralNotes(), source.isCompleted());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MasteryCheck toModelType(ReadOnlyStudentBook studentBook) throws IllegalValueException {
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
        TimePeriod modelTimePeriod = new TimePeriod(this.getStartDateTime(),
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

        MasteryCheck masteryCheck = new MasteryCheck(modelLessonDesc, modelTimePeriod, modelStudentSet,
                modelLessonNotes);
        if (this.isCompleted()) {
            masteryCheck.markAsCompleted();
        }
        return masteryCheck;
    }
}
