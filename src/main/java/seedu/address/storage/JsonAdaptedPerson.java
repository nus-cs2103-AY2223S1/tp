package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.AttendanceList;
import seedu.address.model.person.DurationList;
import seedu.address.model.person.GradeProgressList;
import seedu.address.model.person.HomeworkList;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String lessonPlan;
    private final List<JsonAdaptedHomework> homeworkList = new ArrayList<>();
    private final List<JsonAdaptedAttendance> attendanceList = new ArrayList<>();
    private final List<JsonAdaptedDuration> durationList = new ArrayList<>();
    private final List<JsonAdaptedGradeProgress> gradeProgressList = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("lessonPlan") String lessonPlan,
            @JsonProperty("homework") List<JsonAdaptedHomework> homeworkList,
            @JsonProperty("attendance") List<JsonAdaptedAttendance> attendanceList,
            @JsonProperty("duration") List<JsonAdaptedDuration> durationList,
            @JsonProperty("gradeProgress") List<JsonAdaptedGradeProgress> gradeProgressList,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.lessonPlan = lessonPlan;
        if (gradeProgressList != null) {
            this.gradeProgressList.addAll(gradeProgressList);
        }
        if (homeworkList != null) {
            this.homeworkList.addAll(homeworkList);
        }
        if (attendanceList != null) {
            this.attendanceList.addAll(attendanceList);
        }
        if (durationList != null) {
            this.durationList.addAll(durationList);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        lessonPlan = source.getLessonPlan().value;
        homeworkList.addAll(source.getHomeworkList().homeworkList.stream()
                        .map(JsonAdaptedHomework::new)
                        .collect(Collectors.toList()));
        attendanceList.addAll(source.getAttendanceList().attendanceList.stream()
                        .map(JsonAdaptedAttendance::new)
                        .collect(Collectors.toList()));
        durationList.addAll(source.getDurationList().durationList.stream()
                        .map(JsonAdaptedDuration::new)
                        .collect(Collectors.toList()));
        gradeProgressList.addAll(source.getGradeProgressList().gradeProgressList.stream()
                .map(JsonAdaptedGradeProgress::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (lessonPlan == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, LessonPlan.class.getSimpleName()));
        }
        final LessonPlan modelLessonPlan = new LessonPlan(lessonPlan);

        final HomeworkList modelHomeworkList = new HomeworkList();
        for (JsonAdaptedHomework homework : homeworkList) {
            modelHomeworkList.addHomework(homework.toModelType());
        }
        final AttendanceList modelAttendanceList = new AttendanceList();
        for (JsonAdaptedAttendance attendance: attendanceList) {
            modelAttendanceList.addAttendance(attendance.toModelType());
        }

        final DurationList modelDurationList = new DurationList();
        for (JsonAdaptedDuration duration: durationList) {
            modelDurationList.addDuration(duration.toModelType());
        }

        final GradeProgressList modelGradeProgressList = new GradeProgressList();
        for (JsonAdaptedGradeProgress gradeProgress : gradeProgressList) {
            modelGradeProgressList.addGradeProgress(gradeProgress.toModelType());
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelLessonPlan, modelHomeworkList,
                modelAttendanceList, modelDurationList, modelGradeProgressList, modelTags);
    }

}
