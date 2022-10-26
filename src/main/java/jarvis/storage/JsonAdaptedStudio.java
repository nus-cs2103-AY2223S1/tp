package jarvis.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.ReadOnlyStudentBook;
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
    public JsonAdaptedStudio(Studio source, ReadOnlyStudentBook studentBook) {
        super(source.getDesc(), source.getTimePeriod(), source.getStudentList(),
                source.getAttendance(), source.getGeneralNotes(), source.getStudentNotes(), source.isCompleted());
        studioParticipation = source.getParticipation();
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
        TreeMap<Student, ArrayList<String>> modelStudentNotes = new TreeMap<>(Comparator.comparing(s ->
                s.getName().toString()));
        for (Integer i : this.getStudentNotes().keySet()) {
            modelStudentNotes.put(modelStudentList.get(i), this.getStudentNotes().get(i));
        }
        LessonNotes modelLessonNotes = new LessonNotes(modelStudentList, this.getGeneralNotes(), modelStudentNotes);

        if (studioParticipation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudioParticipation.class.getSimpleName()));
        }
        TreeMap<Student, Integer> studioParticipationMap = new TreeMap<>(Comparator.comparing(s ->
                s.getName().toString()));
        for (int i : studioParticipation.keySet()) {
            studioParticipationMap.put(modelStudentList.get(i), studioParticipation.get(i));
        }
        StudioParticipation modelStudioParticipation = new StudioParticipation(studioParticipationMap);

        Studio studio = new Studio(modelLessonDesc, modelTimePeriod, modelStudentList,
                modelAttendance, modelLessonNotes, modelStudioParticipation);
        if (this.isCompleted()) {
            studio.markAsCompleted();
        }
        return studio;
    }
}
