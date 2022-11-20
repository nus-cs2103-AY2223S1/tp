package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.Attendance;

/**
 * Jackson-friendly version of {@link Attendance}.
 */
public class JsonAdaptedAttendance {
    private final String attendance;

    /**
     * Constructs a {@code JsonAdaptedAttendance} with the given {@code attendance}.
     */
    @JsonCreator
    public JsonAdaptedAttendance(String attendance) {
        this.attendance = attendance;
    }

    /**
     * Converts a given {@code Attendance} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(Attendance source) {
        attendance = source.value;
    }

    @JsonValue
    public String getAttendance() {
        return attendance;
    }

    /**
     * Converts this Jackson-friendly adapted attendance object into the model's {@code Attendance} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Attendance toModelType() throws IllegalValueException {
        if (!Attendance.isValidMark(attendance)) {
            throw new IllegalValueException(Attendance.MESSAGE_CONSTRAINTS);
        }
        return new Attendance(attendance);
    }

}
