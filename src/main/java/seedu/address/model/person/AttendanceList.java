package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Person's attendance list in the address book.
 */
public class AttendanceList {
    public final List<Attendance> attendanceList;

    /**
     * Constructs a {@code AttendanceList}.
     */
    public AttendanceList() {
        attendanceList = new ArrayList<>();
    }

    public AttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    /**
     * Adds an attendance to the attendance list.
     * @param attendance The attendance object to be added.
     */
    public void addAttendance(Attendance attendance) {
        this.attendanceList.add(attendance);
    }

    /**
     * Returns a description of the attendance list. If attendance list size is greater than two,
     * only the first two are shown.
     */
    public String shortDescription() {
        if (attendanceList.size() <= 2) {
            return toString();
        }

        StringBuilder description = new StringBuilder("Attendance:\n");
        for (int i = 0; i < 2; i++) {
            description.append(i + 1).append(". ").append(attendanceList.get(i)).append("\n");
        }
        description.append("...\n");
        return description.toString();
    }

    /**
     * Clears the attendance list.
     */
    public void clearList() {
        attendanceList.clear();
    }
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        if (attendanceList.isEmpty()) {
            description.append("No Attendance found!\n");
        }
        for (int i = 0; i < attendanceList.size(); i++) {
            description.append(i + 1).append(". ").append(this.attendanceList.get(i)).append("\n");
        }
        return description.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AttendanceList
                && this.attendanceList.equals(((AttendanceList) other).attendanceList));
    }

    @Override
    public int hashCode() {
        return this.attendanceList.hashCode();
    }
}
