package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.AttendanceList;
import seedu.address.model.student.ClassGroup;


/**
 * Jackson-friendly version of {@link AttendanceList}.
 */

public class JsonAdaptedAttendanceList {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "AttendanceList's %s field is missing!";

    private final String modName;

    private final List<JsonAdaptedAttendance> attendanceList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedAttendanceList(@JsonProperty("modName") String modName,
                              @JsonProperty("attendanceList") List<JsonAdaptedAttendance> attendanceList) {
        this.modName = modName;
        if (attendanceList != null) {
            this.attendanceList.addAll(attendanceList);
        }
    }

    /**
     * Converts a given {@code AttendanceList} into this class for Jackson use.
     */
    public JsonAdaptedAttendanceList(AttendanceList source) {
        this.modName = source.getMod();
        this.attendanceList.addAll(source.getAttendanceList().stream()
                .map(JsonAdaptedAttendance::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code AttendanceList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attendance list.
     */
    public AttendanceList toModelType() throws IllegalValueException {
        if (modName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClassGroup.class.getSimpleName()));
        }
        final String modelModName = modName;

        final List<Attendance> modelStudentAttendanceList = new ArrayList<>();
        for (JsonAdaptedAttendance attendance : attendanceList) {
            modelStudentAttendanceList.add(attendance.toModelType());
        }
        return new AttendanceList(modelModName, modelStudentAttendanceList);
    }
}
