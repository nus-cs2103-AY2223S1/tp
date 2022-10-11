package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Attendance;

/**
 * Jackson-friendly version of {@link Attendance}.
 */
class JsonAdaptedAttendance {

    private final String className;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAttendance(String className) {
        this.className = className;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
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
        boolean hasAttended;
        if (values.length != 2 || !Attendance.isValidClassName(values[0])) {
            throw new IllegalValueException(Attendance.MESSAGE_CONSTRAINTS);
        }
        if (values[1].equals(Attendance.ATTENDANCE_TRUE)) {
            hasAttended = true;
        } else {
            hasAttended = false;
        }
        return new Attendance(values[0], hasAttended);
    }

}
