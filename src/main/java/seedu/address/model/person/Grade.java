package seedu.address.model.person;

import java.util.ArrayList;
import java.util.HashMap;

public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
            "Grades should only contain an alphabet, and it should not be blank";

    public HashMap<String, double[]> marks;

    /**
     * Constructs a {@code Grade}.
     */
    public Grade() {
        marks = new HashMap<>();
    }

    public double totalMarks(HashMap<String, double[]> subjectMarks) {
        ArrayList<double[]> totalMarksArray = new ArrayList<>();
        totalMarksArray.addAll(subjectMarks.values());
        int length = totalMarksArray.size();
        double totalMarks = 0;
        for (int i = 0; i < length; i++) {
            totalMarks = totalMarksArray.get(i)[0] * totalMarksArray.get(i)[1];
        }
        return totalMarks;
    }

    @Override
    public String toString() {
        double totalMarks = totalMarks(marks);
        return String.format("Grade: %.1f", totalMarks);
    }

    @Override
    public int hashCode() {
        return marks.hashCode();
    }

}
