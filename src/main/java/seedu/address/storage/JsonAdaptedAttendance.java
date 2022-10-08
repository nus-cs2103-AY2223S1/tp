package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.person.Attendance;

class JsonAdaptedAttendance {
    private final String attendanceDesc;

    @JsonCreator
    public JsonAdaptedAttendance(String tagName) {
        this.attendanceDesc = tagName;
    }

    public JsonAdaptedAttendance(Attendance source) {
        attendanceDesc = source.toString();
    }

    @JsonValue
    public String geTagName() {
        return this.attendanceDesc;
    }

    public Attendance toModelType() {
        return new Attendance(attendanceDesc);
    }
}
