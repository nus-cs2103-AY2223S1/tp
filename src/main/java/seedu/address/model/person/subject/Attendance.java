package seedu.address.model.person.subject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the attendance of the student
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS =
        "Attendance should only contain a date followed by a \"1\" or a \"0\", and it should not be blank";

    private final HashMap<String, Integer> personAttendance;

    /**
     * Constructs an {@code Attendance} object.
     */
    public Attendance() {
        personAttendance = new HashMap<>();
    }

    public static boolean isValidAttendance(String test) {
        // TODO: replace with valid regex check that checks for a date and a "1" or "0"
        return true;
    }

    /**
     * Gets the attendance for the specified day
     *
     * @param command the command with which to parse attendance
     */
    public void updateAttendance(String command) {
        // e.g. "1/3/2022 1" -> sets attendance to 1 for 1/3/2022
        String[] commandSplit = command.split(" ");
        String date = commandSplit[1];
        String attendance = commandSplit[2];
        int attended = Integer.parseInt(attendance);
        personAttendance.put(date, attended);
    }

    /**
     * @return an integer array with 1) Total number of days attended, and 2) Total number of days
     */
    public int[] getAttendanceDetails() {
        ArrayList<Integer> totalAttendanceArray = new ArrayList<>(personAttendance.values());
        int length = totalAttendanceArray.size();
        int count = 0;
        for (Integer integer : totalAttendanceArray) {
            count += integer;
        }
        return new int[]{count, length};
    }

    @Override
    public int hashCode() {
        return personAttendance.hashCode();
    }

    @Override
    public String toString() {
        int[] attendanceArray = getAttendanceDetails();
        return String.format("Attendance: %d/%d", attendanceArray[0], attendanceArray[1]);
    }

}
