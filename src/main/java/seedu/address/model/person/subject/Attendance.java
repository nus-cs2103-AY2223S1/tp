package seedu.address.model.person.subject;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the attendance of the student
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS =
        "Attendance should only contain numbers and slashes, and it should not be blank";

    private HashMap<String, Integer> personAttendance;

    /**
     * Constructs a {@code Attendance}.
     */
    public Attendance() {
        personAttendance = new HashMap<>();
    }

    public static boolean isValidAttendance(String test) {
        return true;
    }

    /**
     * Gets the attendance for the specified day
     * @param command the command with which to parse attendance
     */
    public void addAttendance(String command) {
        // e.g. "1/3/2022 1" -> sets attendance to 1 for 1/3/2022
        String[] commandSplit = command.split(" ");
        String date = commandSplit[1];
        String attendance = commandSplit[2];
        int attended = Integer.parseInt(attendance);
        personAttendance.put(date, attended);
    }

    /**
     * @param personAttendance
     * @return
     */
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
    public int hashCode() {
        return personAttendance.hashCode();
    }

    @Override
    public String toString() {
        int[] attendanceArray = totalAttendance(personAttendance);
        return String.format("Attendance: %d/%d", attendanceArray[0], attendanceArray[1]);
    }

}
