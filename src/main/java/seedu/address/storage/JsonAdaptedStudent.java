package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.ModuleCode;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Grade;
import seedu.address.model.student.Name;
import seedu.address.model.student.Participation;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.TelegramHandle;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.TutorialName;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String id;
    private final String phone;
    private final String email;
    private final String telegram;
    private final String tutorialModule;
    private final String tutorialName;
    private final String attendance;
    private final String participation;
    private final String grade;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("id") String id,
                              @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("telegram") String telegram,
                              @JsonProperty("tutorialModule") String tutorialModule,
                              @JsonProperty("tutorialName") String tutorialName,
                              @JsonProperty("attendance") String attendance,
                              @JsonProperty("participation") String participation,
                              @JsonProperty("grade") String grade,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.tutorialModule = tutorialModule;
        this.tutorialName = tutorialName;
        this.attendance = attendance;
        this.participation = participation;
        this.grade = grade;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        id = source.getId().id;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        telegram = source.getTelegram().telegram;
        tutorialModule = source.getTutorialModule().moduleCode;
        tutorialName = source.getTutorialName().fullName;
        attendance = String.valueOf(source.getAttendance().value);
        participation = String.valueOf(source.getParticipation().value);
        grade = source.getGrade().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Student toModelType() throws IllegalValueException {
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

        if (id == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidId(id)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelId = new StudentId(id);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (telegram == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TelegramHandle.class.getSimpleName()));
        }
        if (!TelegramHandle.isValidTelegram(telegram)) {
            throw new IllegalValueException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        final TelegramHandle modelTelegramHandle = new TelegramHandle(telegram);

        if (tutorialModule == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModule(tutorialModule)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(tutorialModule);

        if (tutorialName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TutorialName.class.getSimpleName()));
        }
        if (!TutorialName.isValidName(tutorialName)) {
            throw new IllegalValueException(TutorialName.MESSAGE_CONSTRAINTS);
        }
        final TutorialName modelTutorialName = new TutorialName(tutorialName);

        if (attendance == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Attendance.class.getSimpleName()));
        }
        if (!Attendance.isValidAttendance(attendance)) {
            throw new IllegalValueException(Attendance.MESSAGE_CONSTRAINTS);
        }
        final Attendance modelAttendance = new Attendance(attendance);

        if (participation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Participation.class.getSimpleName()));
        }
        if (!Participation.isValidParticipation(participation)) {
            throw new IllegalValueException(Participation.MESSAGE_CONSTRAINTS);
        }
        final Participation modelParticipation = new Participation(participation);

        if (grade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(grade)) {
            throw new IllegalValueException(Grade.MESSAGE_CONSTRAINTS);
        }
        final Grade modelGrade = new Grade(grade);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Student(modelName, modelId, modelPhone, modelEmail,
                modelTelegramHandle, modelModuleCode, modelTutorialName,
                modelAttendance, modelParticipation, modelGrade, modelTags);
    }

}
