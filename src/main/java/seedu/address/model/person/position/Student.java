package seedu.address.model.person.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.person.Assignment.WEIGHTAGE_CONSTRAINTS;
import static seedu.address.model.person.Assignment.WEIGHTAGE_VALIDATION_REGEX;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Assignment;
import seedu.address.model.tag.Tag;

/**
 * Represents the Student position in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPosition(String)}
 */
public class Student extends Position {

    public static final String ATTENDANCE_CONSTRAINTS =
            "Attendance should be in the format [integer (0-100)]/[integer (0-100)], where the first number is "
                    + "smaller than or equal to the second number.";
    public static final String ATTENDANCE_VALIDATION_REGEX = "\\d{1,3}" + "/" + "\\d{1,3}";
    public static final String MESSAGE_ASSIGNMENT_INVALID = "The index of the assignment is invalid.";
    public static final String ASSIGNMENT_CONSTRAINTS =
            "Incorrect Assignment inputs. Please make sure your input is in the right format "
                    + "\n i.e. assignments assignments/ Assignment 1 w/20, Assignments 2 w/20, Finals w/60";
    public static final String ASSIGNMENT_DUPLICATE =
            "Please ensure you do not have multiple assignments with the same name.";
    public static final String ASSIGNMENT_INVALID_SUM_OF_WEIGHTAGE =
            "Please ensure that your weightages add up to 100.";
    public static final String TOO_MANY_ASSIGNMENT =
            "Please ensure you only have a maximum of 10 assignments.";

    private String attendance;
    private String overallGrade;
    private ArrayList<Assignment> assignmentsList;

    private String filePath;


    /**
     * Creates a student and initialises their attendance to 0/0.
     */
    public Student(String filePath) {
        super("Student");
        requireNonNull(filePath);
        this.attendance = "0/0";
        this.overallGrade = "0/0";
        this.assignmentsList = new ArrayList<>();
        this.filePath = filePath;
        File file = new File(filePath);
        String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir, filePath);



        if (Files.exists(path)) {
            try {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNext()) {
                    String assignments = scanner.nextLine();
                    setAssignments(assignments);
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates a student with the given details.
     * @param attendance of the student
     * @param overallGrade of the student
     * @param assignmentsList Assignments that have been assigned to the student
     */
    public Student(String attendance, String overallGrade, ArrayList<Assignment> assignmentsList, String filePath) {
        super("Student");
        requireNonNull(attendance);
        requireNonNull(overallGrade);
        requireNonNull(assignmentsList);
        requireNonNull(filePath);
        String[] array = attendance.split("/");
        array[0] = array[0].replaceFirst("^0+(?!$)", "");
        array[1] = array[1].replaceFirst("^0+(?!$)", "");
        this.attendance = array[0] + "/" + array[1];
        this.overallGrade = overallGrade;
        this.assignmentsList = assignmentsList;
        this.filePath = filePath;
    }

    public String getAttendance() {
        return attendance;
    }

    public String getOverallGrade() {
        return overallGrade;
    }

    public ArrayList<Assignment> getAssignmentsList() {
        return assignmentsList;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        requireNonNull(filePath);
        this.filePath = filePath;
    }

    public void setFilePath(Set<Tag> modelTags) {
        requireNonNull(modelTags);
        String str = modelTags.toString();
        String data = "./data/";
        String txt = ".txt";
        String module = str.split("-")[0].replace("[", "");
        String filePath = data + module + txt;
        this.filePath = filePath;
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
            Integer firstNumber = Integer.parseInt(split[0]);
            Integer secondNumber = Integer.parseInt(split[1]);
            return firstNumber <= secondNumber && secondNumber <= 100;
        }
    }

    /**
     * Returns true if a given string is a valid string of Assignments
     */
    public static boolean isValidAssignments(String test) {
        Set<String> assignmentNames = new HashSet<>();
        String[] splitStr = test.split(", ");
        int len = splitStr.length;
        int totalWeightage = 0;

        for (int i = 0; i < len; i++) {
            if (i >= 10) {
                return false;
            }

            String[] nameAndWeight = splitStr[i].split(" w/");
            if (nameAndWeight.length != 2) {
                return false;
            }

            if (!nameAndWeight[1].matches(WEIGHTAGE_VALIDATION_REGEX)) {
                return false;
            }

            int weightage = Integer.parseInt(nameAndWeight[1]);

            if (weightage <= 0) {
                return false;
            }

            if (!assignmentNames.add(nameAndWeight[0])) {
                return false;
            }

            totalWeightage += weightage;
        }

        return totalWeightage == 100;
    }

    /**
     * Returns true if a given string is a valid string of Assignments
     */
    public static String findAssignmentIssue(String test) {
        Set<String> assignmentNames = new HashSet<>();
        String[] splitStr = test.split(", ");
        int len = splitStr.length;
        int totalWeightage = 0;

        for (int i = 0; i < len; i++) {
            if (i >= 10) {
                return TOO_MANY_ASSIGNMENT;
            }
            String[] nameAndWeight = splitStr[i].split(" w/");
            if (nameAndWeight.length != 2) {
                return ASSIGNMENT_CONSTRAINTS;
            }

            if (!nameAndWeight[1].matches(WEIGHTAGE_VALIDATION_REGEX)) {
                return WEIGHTAGE_CONSTRAINTS;
            }

            int weightage = Integer.parseInt(nameAndWeight[1]);
            if (weightage <= 0) {
                return WEIGHTAGE_CONSTRAINTS;
            }
            if (!assignmentNames.add(nameAndWeight[0])) {
                return ASSIGNMENT_DUPLICATE;
            }

            totalWeightage += weightage;
        }

        if (!(totalWeightage == 100)) {
            return ASSIGNMENT_INVALID_SUM_OF_WEIGHTAGE;
        }
        return null;
    }

    /**
     * Returns true if the given index of assignment to be edited is valid.
     * @param indexOfAssignment Index of the assignment to be edited
     * @return whether the given index is valid
     */
    public boolean isValidAssignmentIndex(Index indexOfAssignment) {
        return indexOfAssignment.getZeroBased() >= 0
                && indexOfAssignment.getZeroBased() < assignmentsList.size();
    }

    public void setOverallGrade(String overallGrade) {
        requireNonNull(overallGrade);
        this.overallGrade = overallGrade;
    }

    /**
     * Updates the overall grade of the student when the grade of
     * one of their assignments in changed.
     */
    public String updateOverallGrade(Index indexOfAssignment, String grade) throws CommandException {
        this.setAssignmentGrade(indexOfAssignment, grade);
        int totalWeightage = 0;
        float totalGrade = 0;
        for (Assignment assignment: assignmentsList) {
            if (assignment.getIsGradeUpdated()) {
                totalWeightage += assignment.getWeightage();
                totalGrade += assignment.getGradePercentage() * assignment.getWeightage();
            }
        }
        return String.format("%.2f/%d", totalGrade, totalWeightage);
    }

    public Map<String, Number> getAssignmentsAndGrade() {
        Map<String, Number> map = new LinkedHashMap<>();
        for (Assignment a: assignmentsList) {
            map.put(a.getAssignmentName() + " (" + a.getWeightage() + "%)", a.getScore());
        }
        return map;
    }

    public Map<String, Number> getAssignmentsAndMaximumGrade() {
        Map<String, Number> map = new LinkedHashMap<>();
        for (Assignment a: assignmentsList) {
            map.put(a.getAssignmentName() + " (" + a.getWeightage() + "%)", a.getMaximumScore());
        }
        return map;
    }

    private void setAssignmentGrade(Index indexOfAssignment, String grade) throws CommandException {
        if (!isValidAssignmentIndex(indexOfAssignment)) {
            throw new CommandException(MESSAGE_ASSIGNMENT_INVALID);
        }
        Assignment assignmentToEdit = assignmentsList.get(indexOfAssignment.getZeroBased());
        assignmentToEdit.setGrade(grade);
    }

    @Override
    public void setDetails(String details) {
        String[] gradeAndAttendance = isolateDetails(details);
        String overallGrade = gradeAndAttendance[0];
        String attendance = gradeAndAttendance[1];
        String assignments = gradeAndAttendance[2];
        setOverallGrade(overallGrade);
        setAttendance(attendance);
        setPreviousAssignments(assignments);
    }

    /**
     * Isolates the grade, attendance, and assignments values in the details
     * @param details A valid detail.
     * @return a String[] with its first element being the grade and the 2nd element the attendance
     */
    public String[] isolateDetails(String details) {
        String[] gradeAttendanceAssignments = new String[3];
        String[] splitDetails = details.split(", grade - ");
        String[] splitDetails2 = splitDetails[0].split("attendance - ");
        String[] splitDetails3 = splitDetails[1].split(" Assignments: ");
        gradeAttendanceAssignments[0] = splitDetails3[0];
        gradeAttendanceAssignments[1] = splitDetails2[1];
        gradeAttendanceAssignments[2] = splitDetails3[1];
        return gradeAttendanceAssignments;
    }

    public ArrayList<Assignment> setAssignments(String assignments) {
        requireNonNull(assignments);
        String[] splitStr = assignments.split(", ");
        int len = splitStr.length;
        if (assignmentsList.size() > 0) {
            assignmentsList = new ArrayList<>();
        }
        for (int i = 0; i < len; i++) {
            String[] weightageStr = splitStr[i].split(" w/");
            String name = weightageStr[0];
            String weightage = weightageStr[1];
            Assignment a = new Assignment(name, weightage);
            addAssignments(a);
        }
        return assignmentsList;
    }

    public void setPreviousAssignments(String assignments) {

        String trimmedAssignments = trimAssignments(assignments);

        if (trimmedAssignments.equals("")) {
            return;
        }

        String[] assignmentsArr = trimmedAssignments.split(", ");
        int assignmentArrLen = assignmentsArr.length;

        for (int i = 0; i < assignmentArrLen; i++) {
            String curr = assignmentsArr[i];
            String[] splitStr = curr.split(" Score: ");
            String[] splitStr2 = splitStr[1].split(" Weightage: ");
            String name = splitStr[0];
            String grade = splitStr2[0];
            String weightage = splitStr2[1].replace("%", "");
            Assignment a = new Assignment(name, grade, weightage);
            addAssignments(a);
        }
    }

    /**
     * Returns true if a given string is a valid grade.
     */
    public String trimAssignments(String assignments) {
        String trimmedAssignments = assignments.replace("[", "")
                .replace("]", "")
                .replace("(", "")
                .replace(")", "");

        return trimmedAssignments;
    }

    public void addAssignments(Assignment assignment) {
        this.assignmentsList.add(assignment);
    }

    @Override
    public String toShow() {
        return "Attendance: " + attendance + "\n"
                + "Grade: " + overallGrade;
    }

    @Override
    public String toString() {
        return "Student: attendance - " + attendance + ", grade - " + overallGrade
                + "\nAssignments: " + assignmentsList.toString();
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
        return "attendance - " + attendance + ", grade - " + overallGrade + " Assignments: " + assignmentsList;

    }

}
