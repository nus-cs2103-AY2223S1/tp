package seedu.studmap.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.studmap.commons.exceptions.IllegalValueException;
import seedu.studmap.model.student.Attendance;

/**
 * Jackson-friendly version of {@link Attendance}.
 */
class JsonAdaptedAttendance {

    private final String className;

    /**
     * Constructs a {@code JsonAdaptedAttendance} with the given {@code className}.
     */
    @JsonCreator
    public JsonAdaptedAttendance(String className) {
        this.className = className;
    }

    /**
     * Converts a given {@code Attendance} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(Attendance source) {
        className = source.getString();
    }

    @JsonValue
    public String getClassName() {
        return className;
    }

    /**
     * Converts this Jackson-friendly adapted attendance object into the model's {@code Attendance} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attendance.
     */
    public Attendance toModelType() throws IllegalValueException {
        String[] values = className.split(":");
        Attendance.Status status;
        if (values.length != 2 || !Attendance.isValidClassName(values[0])) {
            throw new IllegalValueException(Attendance.MESSAGE_CONSTRAINTS);
        }

        try {
            status = Attendance.Status.fromString(values[1]);
        } catch (IllegalArgumentException iae) {
            throw new IllegalValueException(iae.getMessage());
        }

        return new Attendance(values[0], status);
    }

}
