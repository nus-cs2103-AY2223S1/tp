package jarvis.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.Student;
import jarvis.model.Studio;
import jarvis.model.StudioParticipation;
import jarvis.model.TimePeriod;

/**
 * Jackson-friendly version of {@link Studio}.
 */
public class JsonAdaptedStudio extends JsonAdaptedLesson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Studio's %s field is missing!";

    private final Map<Integer, Integer> studioParticipation;

    /**
     * Constructs a {@code JsonAdaptedStudio} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedStudio(@JsonProperty("lessonDesc") String lessonDesc,
                             @JsonProperty("startDateTime") LocalDateTime startDateTime,
                             @JsonProperty("endDateTime") LocalDateTime endDateTime,
                             @JsonProperty("studentList") List<JsonAdaptedStudent> studentList,
                             @JsonProperty("attendance") Map<Integer, Boolean> attendance,
                             @JsonProperty("generalNotes") ArrayList<String> generalNotes,
                             @JsonProperty("studentNotes") Map<Integer, ArrayList<String>> studentNotes,
                             @JsonProperty("isCompleted") boolean isCompleted,
                             @JsonProperty("studioParticipation") Map<Integer, Integer> studioParticipation) {
        super(lessonDesc, startDateTime, endDateTime, studentList, attendance, generalNotes, studentNotes,
                isCompleted);
        this.studioParticipation = studioParticipation;
    }

    /**
     * Converts a given {@code Studio} into this class for Jackson use.
     */
    public JsonAdaptedStudio(Studio source) {
        super(source.getDesc(), source.getTimePeriod(), source.getStudentList(),
                source.getAttendance(), source.getGeneralNotes(), source.getStudentNotes(), source.isCompleted());
        studioParticipation = source.getParticipation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Studio toModelType() throws IllegalArgumentException {
        // LessonDesc
        final LessonDesc modelLessonDesc = this.getLessonDesc() != null
                ? new LessonDesc(this.getLessonDesc())
                : null;

        // TimePeriod
        checkNullArgument(TimePeriod.class, MISSING_FIELD_MESSAGE_FORMAT, this.getStartDateTime());
        checkNullArgument(TimePeriod.class, MISSING_FIELD_MESSAGE_FORMAT, this.getEndDateTime());
        final TimePeriod modelTimePeriod = new TimePeriod(this.getStartDateTime(),
                this.getEndDateTime());

        // Student list for lesson
        checkNullArgument(Student.class, MISSING_FIELD_MESSAGE_FORMAT, this.getStudentList());
        List<Student> modelStudentList = JsonAdaptedStudent.toModelList(this.getStudentList());

        // LessonAttendance
        checkNullArgument(LessonAttendance.class, MISSING_FIELD_MESSAGE_FORMAT, this.getAttendance());
        LessonAttendance modelAttendance = new LessonAttendance(modelStudentList, this.getAttendance());

        // LessonNotes
        checkNullArgument(LessonNotes.class, MISSING_FIELD_MESSAGE_FORMAT, this.getGeneralNotes());
        checkNullArgument(LessonNotes.class, MISSING_FIELD_MESSAGE_FORMAT, this.getStudentNotes());
        LessonNotes modelLessonNotes = new LessonNotes(modelStudentList, this.getGeneralNotes(),
                this.getStudentNotes());

        // StudioParticipation
        checkNullArgument(StudioParticipation.class, MISSING_FIELD_MESSAGE_FORMAT, studioParticipation);
        StudioParticipation modelStudioParticipation = new StudioParticipation(modelStudentList, studioParticipation);

        Studio studio = new Studio(modelLessonDesc, modelTimePeriod, modelStudentList,
                modelAttendance, modelLessonNotes, modelStudioParticipation);

        if (this.isCompleted()) {
            studio.markAsCompleted();
        }
        return studio;
    }
}
