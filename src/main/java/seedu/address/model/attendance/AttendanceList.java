package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a Student's attendance list in the address book
 */
public class AttendanceList {

    //if size is 0, empty list, elif up to 12, else reject.
    public static final String VALIDATION_REGEX = "[0-9]|1[012]";
    public static final String MESSAGE_CONSTRAINTS = "Invalid size";
    private final String mod;
    private List<Attendance> attendanceList;
    private final boolean isEmpty;

    /**
     * Constructs an {@code AttendanceList}.
     * @param mod A module of the attendance list.
     * @param size The size of the attendance list
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
     * Constructs an empty {@code AttendanceList}.
     */
    public AttendanceList() {
        this.isEmpty = true;
        this.mod = "NA";
        this.attendanceList = new ArrayList<>();
    }

    /**
     * Constructs an {@code AttendanceList}.
     * @param mod A module of the attendance list
     * @param attendanceList A List of attendance
     */
    public AttendanceList(String mod, List<Attendance> attendanceList) {
        requireNonNull(mod);
        checkArgument(isValidSize(Integer
                .toString(attendanceList.size())), MESSAGE_CONSTRAINTS);
        this.isEmpty = attendanceList.size() == 0;
        this.mod = isEmpty ? "NA" : mod;
        this.attendanceList = new ArrayList<Attendance>(attendanceList);
    }

    /**
     * Returns if a given string is a valid list size.
     */
    public static boolean isValidSize(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns if a given attendance list is empty
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * Returns size of attendance list.
     */
    public int getSize() {
        return attendanceList.size();
    }

    /**
     * Returns a copy of the List
     */
    public List<Attendance> getAttendanceList() {
        return new ArrayList<Attendance>(attendanceList);
    }

    /**
     * Returns the module of the attendance list.
     */
    public String getMod() {
        return mod;
    }

    /**
     * Marks the attendance in the attendance list
     * @param index Index of the attendance list to be marked
     * @param attendance Attendance to be updated to
     */
    public void mark(String index, Attendance attendance) {
        int lessonIndex = Integer.parseInt(index) - 1;
        // add error clause here
        checkArgument(lessonIndex < attendanceList.size()
                && lessonIndex >= 1);
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
