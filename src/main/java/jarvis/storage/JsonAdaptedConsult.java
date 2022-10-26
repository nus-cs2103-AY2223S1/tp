package jarvis.storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import jarvis.commons.core.index.Index;
import jarvis.commons.exceptions.IllegalValueException;
import jarvis.commons.util.JsonUtil;
import jarvis.model.Consult;
import jarvis.model.Lesson;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.Student;
import jarvis.model.TimePeriod;
import jarvis.model.util.SampleStudentUtil;

/**
 * Jackson-friendly version of {@link Consult}.
 */
public class JsonAdaptedConsult extends JsonAdaptedLesson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consult's %s field is missing!";

    /**
     * Constructs a {@code JsonAdaptedConsult} with the given consult details.
     */
    @JsonCreator
    public JsonAdaptedConsult(@JsonProperty("lessonDesc") String lessonDesc,
                              @JsonProperty("startDateTime") LocalDateTime startDateTime,
                              @JsonProperty("endDateTime") LocalDateTime endDateTime,
                              @JsonProperty("studentIndexList") Set<Integer> studentIndexList,
                              @JsonProperty("attendance") String attendance,
                              @JsonProperty("notes") String notes,
                              @JsonProperty("isCompleted") boolean isCompleted) {
        super(lessonDesc, startDateTime, endDateTime, studentIndexList, attendance, notes, isCompleted);
    }

    /**
     * Converts a given {@code Consult} into this class for Jackson use.
     */
    public JsonAdaptedConsult(Consult source, ReadOnlyStudentBook studentBook) throws JsonProcessingException {
        super(source.getDesc(), source.getTimePeriod(), studentBook.getIndexList(source.getStudents()),
                source.getAttendance(), source.getGeneralNotes(),
                source.isCompleted());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Consult toModelType(ReadOnlyStudentBook studentBook) throws IllegalValueException, IOException {
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
        /*
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<Student, Boolean> attendance = objectMapper.readValue(this.getAttendance(),
                new TypeReference<HashMap<Student,Boolean>>(){});
        LessonAttendance modelLessonAttendance = new LessonAttendance(attendance);*/

        if (this.getNotes() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LessonNotes.class.getSimpleName()));
        }

        Consult consult = new Consult(modelLessonDesc, modelTimePeriod,
                modelStudentSet);

        if (this.isCompleted()) {
            consult.markAsCompleted();
        }
        return consult;
    }
}
