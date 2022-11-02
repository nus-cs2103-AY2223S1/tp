package seedu.classify.model.student;

import java.util.Comparator;

/**
 * Compares the grades of 2 students in a particular exam. The 2 students must be from the same class.
 */
public class GradeComparator implements Comparator<Student> {

    private final String exam;
    private final Class className;

    /**
     * Creates a GradeComparator object using the given {@code String exam}, to compare the grades in that exam.
     */
    public GradeComparator(String exam, Class className) {
        this.exam = exam;
        this.className = className;
    }

    @Override
    public int compare(Student o1, Student o2) {
        // if the student does not belong to the class, group them at the bottom of the list
        if (!o1.getClassName().equals(className)) {
            return 1;
        }
        if (!o2.getClassName().equals(className)) {
            return -1;
        }
        int o1Score = o1.getExamScore(exam);
        int o2Score = o2.getExamScore(exam);
        if (o1Score < o2Score) {
            return -1;
        } else if (o2Score < o1Score) {
            return 1;
        } else {
            return 0;
        }
    }
}
