package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.HelpTag;
import seedu.address.model.student.Response;
import seedu.address.model.student.StuEmail;
import seedu.address.model.student.StuName;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;

/**
 * Jackson-friendly version of {@link Student}.
 */
public class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String telegram;
    private final String email;
    private final String response;
    private final String attendance;
    private final String toHelp;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("telegram") String telegram,
                             @JsonProperty("email") String email, @JsonProperty("response") String response,
                             @JsonProperty("attendance") String attendance, @JsonProperty("toHelp") String toHelp) {
        this.name = name;
        this.telegram = telegram;
        this.email = email;
        this.response = response;
        this.attendance = attendance;
        this.toHelp = toHelp;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        telegram = source.getTelegram().telegram;
        email = source.getEmail().value;
        response = source.getResponse().value;
        attendance = source.getAttendance().attendance;
        toHelp = Boolean.toString(source.getHelpTag().toHelp);
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, StuName.class.getSimpleName()));
        }
        if (!StuName.isValidStuName(name)) {
            throw new IllegalValueException(StuName.MESSAGE_CONSTRAINTS);
        }
        final StuName modelName = new StuName(name);

        if (telegram == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Telegram.class.getSimpleName()));
        }
        if (!Telegram.isValidTelegram(telegram)) {
            throw new IllegalValueException(Telegram.MESSAGE_CONSTRAINTS);
        }
        final Telegram modelTelegram = new Telegram(telegram);

        if (email == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, StuEmail.class.getSimpleName()));
        }
        if (!StuEmail.isValidStuEmail(email)) {
            throw new IllegalValueException(StuEmail.MESSAGE_CONSTRAINTS);
        }
        final StuEmail modelEmail = new StuEmail(email);

        if (response == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Response.class.getSimpleName()));
        }
        if (!Response.isValidResponse(response)) {
            throw new IllegalValueException(Response.MESSAGE_CONSTRAINTS);
        }
        final Response modelResponse = new Response(response);

        if (attendance == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Attendance.class.getSimpleName()));
        }
        if (!Attendance.isValidAttendance(attendance)) {
            throw new IllegalValueException(Attendance.MESSAGE_CONSTRAINTS);
        }
        final Attendance modelAttendance = new Attendance(attendance);

        if (toHelp == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, HelpTag.class.getSimpleName()));
        }
        final HelpTag modelHelpTag = new HelpTag(Boolean.parseBoolean(toHelp));

        return new Student(modelName, modelTelegram, modelEmail, modelResponse, modelAttendance, modelHelpTag);
    }
}
