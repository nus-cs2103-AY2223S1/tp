package jarvis.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jarvis.model.Consult;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.Student;
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
    public JsonAdaptedConsult(@JsonProperty("lessonDesc") String lessonDesc,
                              @JsonProperty("startDateTime") LocalDateTime startDateTime,
                              @JsonProperty("endDateTime") LocalDateTime endDateTime,
                              @JsonProperty("studentList") ArrayList<JsonAdaptedStudent> studentList,
                              @JsonProperty("attendance") Map<Integer, Boolean> attendance,
                              @JsonProperty("generalNotes") ArrayList<String> generalNotes,
                              @JsonProperty("studentNotes") Map<Integer, ArrayList<String>> studentNotes,
                              @JsonProperty("isCompleted") boolean isCompleted) {
        super(lessonDesc, startDateTime, endDateTime, studentList, attendance, generalNotes, studentNotes,
                isCompleted);
    }

    /**
     * Converts a given {@code Consult} into this class for Jackson use.
     */
    public JsonAdaptedConsult(Consult source) {
        super(source.getDesc(), source.getTimePeriod(), source.getStudentList(),
                source.getAttendance(), source.getGeneralNotes(), source.getStudentNotes(),
                source.isCompleted());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Consult toModelType() throws IllegalArgumentException {
        // LessonDesc
        final LessonDesc modelLessonDesc = createModelLessonDesc(getLessonDesc());

        // TimePeriod
        final TimePeriod modelTimePeriod = createModelTimePeriod(MISSING_FIELD_MESSAGE_FORMAT,
                getStartDateTime(), getEndDateTime());

        // Student list for lesson
        List<Student> modelStudentList = createModelStudentList(MISSING_FIELD_MESSAGE_FORMAT, getStudentList());

        // LessonAttendance
        LessonAttendance modelAttendance = createModelLessonAttendance(MISSING_FIELD_MESSAGE_FORMAT,
                modelStudentList, getAttendance());

        // LessonNotes
        LessonNotes modelLessonNotes = createModelLessonNotes(MISSING_FIELD_MESSAGE_FORMAT, modelStudentList,
                getGeneralNotes(), getStudentNotes());

        Consult consult = new Consult(modelLessonDesc, modelTimePeriod,
                modelStudentList, modelAttendance, modelLessonNotes);

        if (this.isCompleted()) {
            consult.markAsCompleted();
        }
        return consult;
    }
}
