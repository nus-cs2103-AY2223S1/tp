package seedu.classify.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s grade for a particular exam is less than the mean score of the class for that exam
 */
public class GradeLessThanMeanPredicate implements Predicate<Student> {

    /** The class that the Student belongs to */
    private final Class className;

    /** The mean score of the class */
    private final double mean;

    /** The exam of interest */
    private final String exam;

    /**
     * Creates a GradeLessThanMeanPredicate Object to compare the given exam.
     */
    public GradeLessThanMeanPredicate(Class className, double mean, String exam) {
        this.className = className;
        this.mean = mean;
        this.exam = exam;
    }

    @Override
    public boolean test(Student student) {
        return (student.getClassName().equals(className)) && (student.getExamScore(exam) < mean);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradeLessThanMeanPredicate // instanceof handles nulls
                && className.equals(((GradeLessThanMeanPredicate) other).className)
                && mean == ((GradeLessThanMeanPredicate) other).mean
                && exam.equals(((GradeLessThanMeanPredicate) other).exam)); // state check
    }
}
