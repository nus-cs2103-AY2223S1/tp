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
    public JsonAdaptedAttendance(String attendanceDesc) {
        this.attendanceDesc = attendanceDesc;
    }

    /**
     * Converts a given {@code Attendance} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(Attendance source) {
        attendanceDesc = source.toString();
    }

    @JsonValue
    public String getAttendanceDesc() {
        return attendanceDesc;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Attendance} object.
     */
    public Attendance toModelType() {
        String[] parts = attendanceDesc.split(" ");
        if (parts.length < 2 || parts[1].contains("[Absent]")) {
            return new Attendance(parts[0]);
        } else {
            Attendance att = new Attendance(parts[0]);
            att.markAsPresent();
            return att;
        }
    }
}
