package seedu.classify.model.student;

import java.util.function.Predicate;

public class GradeLessThanMeanPredicate implements Predicate<Student> {

    private final String className;
    private final double mean;
    private final String exam;

    public GradeLessThanMeanPredicate(String className, double mean, String exam) {
        this.className = className;
        this.mean = mean;
        this.exam = exam;
    }

    @Override
    public boolean test(Student student) {
        return (student.getClassName().toString().equals(className)) && (student.getExamScore(exam) < mean);
    }
}
