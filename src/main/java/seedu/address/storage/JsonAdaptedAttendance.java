package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.person.Attendance;

/**
 * Jackson-friendly version of {@link Attendance}.
 */
class JsonAdaptedAttendance {
    private final String attendanceDesc;

    /**
     * Constructs a {@code JsonAdaptedAttendance} with the given {@code attendanceDesc}.
     */
    @JsonCreator
    public JsonAdaptedAttendance(String tagName) {
        this.attendanceDesc = tagName;
    }

    /**
     * Converts a given {@code Attendance} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(Attendance source) {
        attendanceDesc = source.toString();
    }

    @JsonValue
    public String getTagName() {
        return attendanceDesc;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Attendance} object.
     */
    public Attendance toModelType() {
        return new Attendance(attendanceDesc);
    }
}
