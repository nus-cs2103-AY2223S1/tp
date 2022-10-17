package seedu.address.storage;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.level.Level;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.Day;
import seedu.address.model.tuitionclass.Name;
import seedu.address.model.tuitionclass.Subject;
import seedu.address.model.tuitionclass.Time;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Jackson-friendly version of {@link TuitionClass}.
 */

class JsonAdaptedTuitionClass {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "TuitionClass' %s field is missing!";

    private final String name;
    private final String subject;
    private final String level;
    private final String day;
    private final String startTime;
    private final String endTime;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedTutor> tutors = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedTuitionClass} with the given tuitionClass details.
     */
    @JsonCreator
    public JsonAdaptedTuitionClass(@JsonProperty("name") String name, @JsonProperty("subject") String subject,
                            @JsonProperty("level") String level, @JsonProperty("day") String day,
                            @JsonProperty("startTime") String startTime, @JsonProperty("endTime") String endTime,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                            @JsonProperty("students") List<JsonAdaptedStudent> students,
                            @JsonProperty("tutors") List<JsonAdaptedTutor> tutors) {
        this.name = name;
        this.subject = subject;
        this.level = level;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (students != null) {
            this.students.addAll(students);
        }
        if (tutors != null) {
            this.tutors.addAll(tutors);
        }
    }

    /**
     * Converts a given {@code Tutor} into this class for Jackson use.
     */
    public JsonAdaptedTuitionClass(TuitionClass source) {
        name = source.getName().name;
        subject = source.getSubject().name();
        level = source.getLevel().name();
        day = source.getDay().name();
        startTime = source.getTime().getStartTime();
        endTime = source.getTime().getEndTime();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        students.addAll(source.getStudents().stream()
                .map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
        tutors.addAll(source.getTutors().stream()
                .map(JsonAdaptedTutor::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted tuitionClass object into the model's {@code TuitionClass} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tuitionClass.
     */
    public TuitionClass toModelType() throws IllegalValueException {
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

        if (subject == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Subject.class.getSimpleName()));
        }
        if (!Subject.isValidSubject(subject)) {
            throw new IllegalValueException(Subject.MESSAGE_CONSTRAINTS);
        }
        final Subject modelSubject = Subject.createSubject(subject);

        if (level == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Level.class.getSimpleName()));
        }
        if (!Level.isValidLevel(level)) {
            throw new IllegalValueException(Level.MESSAGE_CONSTRAINTS);
        }
        final Level modelLevel = Level.createLevel(level);

        if (day == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }
        if (!Day.isValidDay(day)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day modelDay = Day.createDay(day);

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        try {
            LocalTime.parse(startTime);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        try {
            LocalTime.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelTime = new Time(startTime, endTime);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final List<Student> modelStudents = new ArrayList<>();
        for (JsonAdaptedStudent student: students) {
            modelStudents.add(student.toModelType());
        }

        final List<Tutor> modelTutors = new ArrayList<>();
        for (JsonAdaptedTutor tutor: tutors) {
            modelTutors.add(tutor.toModelType());
        }
        return new TuitionClass(modelName, modelSubject, modelLevel, modelDay, modelTime,
                modelTags, modelStudents, modelTutors);
    }
}
