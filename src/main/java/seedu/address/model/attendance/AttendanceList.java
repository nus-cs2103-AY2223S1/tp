package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents an attendance list in the student field
 */
public class AttendanceList {

    //if size is 0, empty list, elif up to 12, else reject.
    public static final String VALIDATION_REGEX = "[0-9]|1[012]";
    public static final String MESSAGE_CONSTRAINTS = "Invalid size";
    public final String mod;
    public List<Attendance> attendanceList;
    public final boolean isEmpty;

    /**
     * Creates new attendance list object
     * @param mod is the attendance of the list
     * @param size is the size of the list
     */
    public AttendanceList(String mod, String size) {
        requireNonNull(mod, size);
        checkArgument(isValidSize(size), MESSAGE_CONSTRAINTS);
        this.isEmpty = Integer.parseInt(size) == 0;
        this.mod = isEmpty ? "NA" : mod;
        this.attendanceList = Stream
                .generate(Attendance::new)
                .limit(Integer.parseInt(size))
                .collect(Collectors.toList());
    }

    /**
     * Overloaded method to create empty AttendanceLists
     */
    public AttendanceList() {
        this.isEmpty = true;
        this.mod = "NA";
        this.attendanceList = new ArrayList<>();
    }

    /**
     * Duplicate attendance list
     * @param mod
     * @param attendanceList
     */
    public AttendanceList(String mod, List<Attendance> attendanceList) {
        requireNonNull(mod);
        checkArgument(isValidSize(Integer
                .toString(attendanceList.size())),MESSAGE_CONSTRAINTS);
        this.isEmpty = attendanceList.size() == 0;
        this.mod = isEmpty ? "NA" : mod;
        this.attendanceList = new ArrayList<Attendance>(attendanceList);
    }

    /**
     * Checks for valid number
     * @param test is the input for the size
     * @return true if within 0-12
     */
    public static boolean isValidSize(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if AttendanceList is empty
     * @return
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    public List<Attendance> getAttendanceList() {
        return new ArrayList<Attendance>(attendanceList);
    }

    public String getMod() {
        return mod;
    }

    public void mark(String index, Attendance attendance) {
        // add error clause here

        int lessonIndex = Integer.parseInt(index) - 1;
        attendanceList.set(lessonIndex, attendance);
    }



    @Override
    public String toString() {
        if (isEmpty()) {
            return "NA";
        }
        StringBuilder str = new StringBuilder();
        str.append(String.format("(%s)", mod));
        str.append(Arrays.toString(attendanceList.toArray()));
        return str.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttendanceList // instanceof handles nulls
                && mod.equals(((AttendanceList) other).mod)); // state check
    }

    @Override
    public int hashCode() {
        return mod.hashCode();
    }
}
