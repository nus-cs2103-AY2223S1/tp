package seedu.classify.model.student;

import java.util.Comparator;

public class GradeComparator implements Comparator<Student> {

    private final String exam;

    public GradeComparator(String exam) {
        assert exam.equals("CA1") || exam.equals("CA2") || exam.equals("SA1") || exam.equals("SA2");
        this.exam = exam;
    }

    @Override
    public int compare(Student o1, Student o2) {
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
