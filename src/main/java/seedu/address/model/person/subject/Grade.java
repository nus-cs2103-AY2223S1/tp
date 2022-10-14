package seedu.address.model.person.subject;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the grades of a student stored by subject
 */
public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
            "Grades should only contain an alphabet, and it should not be blank";

    private HashMap<String, double[]> marks;

    private String todoCommand;

    /**
     * Constructs a {@code Grade}.
     */
    public Grade(String command) {
        requireNonNull(command);
        marks = new HashMap<>();
        todoCommand = command;
    }

    public double getCurrentPercentageObtained(HashMap<String, double[]> subjectMarks) {
        ArrayList<double[]> totalMarksArray = new ArrayList<>();
        totalMarksArray.addAll(subjectMarks.values());
        int length = totalMarksArray.size();
        double totalMarks = 0;
        double totalWeightage = 0;
        for (int i = 0; i < length; i++) {
            totalMarks += totalMarksArray.get(i)[0] * totalMarksArray.get(i)[1];
            totalWeightage += totalMarksArray.get(i)[1];
        }
        return (totalMarks / totalWeightage);
    }

    public static boolean isValidGrade(String test) {
        return true;
    }

    @Override
    public String toString() {
        double currentPercentageObtained = getCurrentPercentageObtained(marks);
        return String.format("Grade: %.1f", currentPercentageObtained);
    }

    @Override
    public int hashCode() {
        return marks.hashCode();
    }

}
