package jarvis.storage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;

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
    public JsonAdaptedConsult(Consult source, ReadOnlyStudentBook studentBook) {
        super(source.getDesc(), source.getTimePeriod(), source.getStudentList(),
                source.getAttendance(), source.getGeneralNotes(), source.getStudentNotes(),
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

        if (this.getStudentList() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Student.class.getSimpleName()));
        }
        List<Student> modelStudentList = new ArrayList<>();
        for (JsonAdaptedStudent jsonAdaptedStudent : this.getStudentList()) {
            modelStudentList.add(jsonAdaptedStudent.toModelType());
        }

        if (this.getAttendance() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LessonAttendance.class.getSimpleName()));
        }
        TreeMap<Student, Boolean> attendanceMap = new TreeMap<>(Comparator.comparing(s ->
                s.getName().toString()));
        for (Integer i : this.getAttendance().keySet()) {
            attendanceMap.put(modelStudentList.get(i), this.getAttendance().get(i));
        }
        LessonAttendance modelAttendance = new LessonAttendance(attendanceMap);

        if (this.getGeneralNotes() == null || this.getStudentNotes() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LessonNotes.class.getSimpleName()));
        }
        TreeMap<Student, ArrayList<String>> modelStudentNotes = new TreeMap<>(Comparator.comparing(s -> s.getName().toString()));
        for (Integer i : this.getStudentNotes().keySet()) {
            modelStudentNotes.put(modelStudentList.get(i), this.getStudentNotes().get(i));
        }
        LessonNotes modelLessonNotes = new LessonNotes(modelStudentList, this.getGeneralNotes(), modelStudentNotes);


        Consult consult = new Consult(modelLessonDesc, modelTimePeriod,
                modelStudentList, modelAttendance, modelLessonNotes);

        if (this.isCompleted()) {
            consult.markAsCompleted();
        }
        return consult;
    }
}
