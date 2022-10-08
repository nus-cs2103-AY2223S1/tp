package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

public class AttendanceList {
    public final List<Attendance> attendanceList;

    public AttendanceList() {
        attendanceList = new ArrayList<>();
    }

    public AttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public void addAttendance(Attendance attendance) {
        this.attendanceList.add(attendance);
    }

    public String toString() {
        StringBuilder description =  new StringBuilder("Attendance: \n");
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
