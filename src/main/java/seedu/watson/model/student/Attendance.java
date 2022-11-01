package seedu.watson.model.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.watson.commons.util.DateUtil;
import seedu.watson.logic.parser.exceptions.ParseException;

/**
 * Represents the attendance of the student
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS =
        "Attendance should only contain a date followed by a \"1\" or a \"0\"";

    private static final Pattern FORMAT = Pattern.compile("date/(?<date>.+) attendance/(?<attendance>[01])");

    private final HashMap<String, Integer> personAttendance;

    /**
     * Constructs an {@code Attendance} object.
     */
    public Attendance() {
        this(new HashMap<>());
    }

    /**
     * Constructs an {@code Attendance} object with a given date/value HashMap.
     */
    public Attendance(HashMap<String, Integer> hashMap) {
        personAttendance = hashMap;
    }

    /**
     * Checks if a given String can be parsed into a valid Attendance object.
     * @param test the String to be checked
     * @return true if the String can be parsed into a valid Attendance object, otherwise false
     */
    public static boolean isValidAttendance(String test) {
        // Input should be in the form date/DATE attendance/[1/0]
        // e.g. date/13 Jan 2022 attendance/1
        if (test.isBlank()) {
            return true; // a student will start with a blank attendance
        }
        Matcher matcher = FORMAT.matcher(test.trim());
        if (!matcher.matches()) {
            return false;
        }
        String date = matcher.group("date");
        date += " 00:00"; // scuffed implementation to avoid Temporal errors
        return DateUtil.isValidDateString(date);
    }

    /**
     * Parses a given String into an HashMap.
     * @param json the String (json) to be parsed
     * @return A HashMap representing the attendance of the student
     * @throws ParseException
     */
    public static HashMap<String, Integer> parseAttendanceFromJson(String json)
            throws ParseException {
        // For empty attendances
        if (json.isBlank()) {
            return new HashMap<>();
        }
        String trimmedInput = json.trim();
        String[] toParse = trimmedInput.split("%%");
        HashMap<String, Integer> tempMap = new HashMap<>();
        for (String s : toParse) {
            if (!Attendance.isValidAttendance(s)) {
                throw new ParseException(Attendance.MESSAGE_CONSTRAINTS);
            } else {
                Matcher matcher = FORMAT.matcher(s.trim());
                if (!matcher.matches()) {
                    System.out.println("failed valid check: " + s);
                    throw new ParseException(Attendance.MESSAGE_CONSTRAINTS); // "initializes" matcher
                }
                String date = matcher.group("date");
                int attendance = Integer.parseInt(matcher.group("attendance"));
                tempMap.put(date, attendance);
            }
        }
        return tempMap;
    }

    /**
     * Gets the attendance for the specified day
     *
     * @param command the command with which to parse attendance
     */
    public void updateAttendance(String command) {
        //checkArgument(isValidAttendance(command), MESSAGE_CONSTRAINTS);
        // e.g. "1/3/2022 1" -> sets attendance to 1 for 1/3/2022
        String[] commandSplit = command.split(" ");
        String date = commandSplit[0];
        String attendance = commandSplit[1];
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
        StringBuilder sb = new StringBuilder();
        for (String key : personAttendance.keySet()) {
            sb.append("date/").append(key).append(" attendance/").append(personAttendance.get(key)).append("%%");
        }
        return sb.toString();
    }

    /**
     * Returns the Attendance into a String to be shown in the GUI.
     *
     * @return a String which represents the Attendance of the student.
     */
    public String guiString() {
        StringBuilder sb = new StringBuilder();
        if (personAttendance.isEmpty()) {
            sb.append("No attendance recorded yet!");
            return sb.toString();
        }
        int[] attendance = this.getAttendanceDetails();
        sb.append(attendance[0]).append("/").append(attendance[1]);
        return sb.toString();
    }

    /**
     * Checks if a student has above 80% attendance.
     *
     * @return A boolean value.
     */
    public boolean hasGoodAttendance() {
        int[] attendance = getAttendanceDetails();
        if (attendance[1] == 0) {
            return false;
        }
        return ((float) attendance[0] / attendance[1] >= 0.8);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Attendance // instanceof handles nulls
            && personAttendance.equals(((Attendance) other).personAttendance)); // state check
    }
}
