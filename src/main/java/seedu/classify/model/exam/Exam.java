package seedu.classify.model.exam;

import static java.util.Objects.requireNonNull;
import static seedu.classify.commons.util.AppUtil.checkArgument;

/**
 * Represents an Exam that a student has.
 */
public class Exam {

    public static final String MESSAGE_CONSTRAINTS = "Exam should be in the format <NAME> <SCORE>";
    public static final String MESSAGE_NAME_CONSTRAINTS = "Exam name should be CA1, CA2, SA1 or SA2.";
    public static final String MESSAGE_SCORE_CONSTRAINTS = "Exam score should be a number between 0 to 100.";
    public static final String VALIDATION_REGEX = "[0-9]+";

    private final String name;
    private final int score;

    /**
     * Constructs a {@code Exam}.
     *
     * @param exam
     */
    public Exam(String exam) {
        requireNonNull(exam);
        checkArgument(isValidFormat(exam), MESSAGE_CONSTRAINTS);
        String[] args = exam.split("\\s+");
        String name = args[0];
        String score = args[1];
        checkArgument(isValidName(name.toUpperCase()), MESSAGE_NAME_CONSTRAINTS);
        checkArgument(isValidScore(score), MESSAGE_SCORE_CONSTRAINTS);
        this.name = name.toUpperCase();
        this.score = Integer.parseInt(score);
    }

    /**
     * Returns true if a given string is a valid exam format.
     */
    public static boolean isValidFormat(String test) {
        String[] args = test.split("\\s+");
        return args.length == 2;
    }

    /**
     * Returns true if a given string is a valid exam name.
     */
    public static boolean isValidName(String test) {
        return test.equals("CA1") || test.equals("CA2")
                || test.equals("SA1") || test.equals("SA2");
    }

    /**
     * Returns true if a given string is a valid exam score.
     */
    public static boolean isValidScore(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            int score = Integer.parseInt(test);
            return score >= 0 && score <= 100;
        }
        return false;
    }

    /**
     * Returns the score of the exam.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Returns the name of the exam.
     */
    public String getExamName() {
        return this.name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Exam // instanceof handles nulls
                && this.name.equals(((Exam) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return String.format("%s %d", this.name, this.score);
    }

}
