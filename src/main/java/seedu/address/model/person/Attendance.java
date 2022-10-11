package seedu.address.model.person;

import java.util.ArrayList;
import java.util.HashMap;

import static java.util.Objects.requireNonNull;

public class Attendance {

    public static final String MESSAGE_CONSTRAINTS =
            "Attendance should only contain numbers and slashes, and it should not be blank";

    public HashMap<String, Integer> personAttendance;

    public String attendanceCommand;

    /**
     * Constructs a {@code Attendance}.
     */
    public Attendance(String command) {
        requireNonNull(command);
        personAttendance = new HashMap<>();
        attendanceCommand = command;
    }

    public int[] totalAttendance(HashMap<String, Integer> personAttendance) {
        ArrayList<Integer> totalAttendanceArray = new ArrayList<>();
        totalAttendanceArray.addAll(personAttendance.values());
        int length = totalAttendanceArray.size();
        int count = 0;
        for (int i = 0; i < length; i++) {
            count += totalAttendanceArray.get(i);
        }
        return new int[]{count, length};
    }

    @Override
    public String toString() {
        int[] attendanceArray = totalAttendance(personAttendance);
        return String.format("Attendance: %d/%d", attendanceArray[0], attendanceArray[1]);
    }

    @Override
    public int hashCode() {
        return personAttendance.hashCode();
    }

}
