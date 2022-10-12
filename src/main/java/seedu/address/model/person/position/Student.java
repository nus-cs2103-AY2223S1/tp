package seedu.address.model.person.position;

import static java.util.Objects.requireNonNull;

/**
 * Represents the Student position in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPosition(String)}
 */
public class Student extends Position {

    public static final String ATTENDANCE_CONSTRAINTS =
            "Attendance should be in the format [number]/[number], where the first number is greater "
                    + "or equal to the second number (max 999).";

    public static final String ATTENDANCE_VALIDATION_REGEX = "\\d{1,3}" + "/" + "\\d{1,3}";

    public static final String GRADE_CONSTRAINTS =
            "Grade should be in the format [number]/[number], where the first number is greater "
                    + "or equal to the second number (max 99999).";

    public static final String GRADE_VALIDATION_REGEX = "\\d{1,5}" + "/" + "\\d{1,5}";

    private String attendance;

    private String grade;

    /**
     * Creates a student and initialises their attendance to 0/0.
     */
    public Student() {
        super("Student");
        this.attendance = "0/0";
        this.grade = "0/0";
    }

    public void setAttendance(String attendance) {
        requireNonNull(attendance);
        this.attendance = attendance;
    }

    /**
     * Returns true if a given string is a valid attendance.
     */
    public static boolean isValidAttendance(String test) {
        if (!test.matches(ATTENDANCE_VALIDATION_REGEX)) {
            return false;
        } else {
            String[] split = test.split("/");
            return Integer.parseInt(split[0]) <= Integer.parseInt(split[1]);
        }
    }

    public void setGrade(String grade) {
        requireNonNull(grade);
        this.grade = grade;
    }
    @Override
    public void setDetails(String details) {
        String[] gradeAndAttendance = isolateGradeAndAttendance(details);
        String grade = gradeAndAttendance[0];
        String attendance = gradeAndAttendance[1];
        setGrade(grade);
        setAttendance(attendance);
    }

    /**
     * method used to isolate the grade and attendance values in its details
     * @param details A valid detail.
     * @return a String[] with its first element being the grade and the 2nd element the attendance
     */
    public String[] isolateGradeAndAttendance(String details) {
        String[] gradeAndAttendance = new String[2];
        String[] splitDetails = details.split(", grade - ");
        String[] splitDetails2 = splitDetails[0].split("attendance - ");
        gradeAndAttendance[0] = splitDetails[1];
        gradeAndAttendance[1] = splitDetails2[1];
        return gradeAndAttendance;
    }

    /**
     * Returns true if a given string is a valid grade.
     */
    public static boolean isValidGrade(String test) {
        if (!test.matches(GRADE_VALIDATION_REGEX)) {
            return false;
        } else {
            String[] split = test.split("/");
            return Integer.parseInt(split[0]) <= Integer.parseInt(split[1]);
        }
    }

    @Override
    public String toString() {
        return "Student: attendance - " + attendance + ", grade - " + grade;
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }

    @Override
    public int hashcode() {
        return 0;
    }

    @Override
    public String getDetails() {
        return "attendance - " + attendance + ", grade - " + grade;

    }

}
