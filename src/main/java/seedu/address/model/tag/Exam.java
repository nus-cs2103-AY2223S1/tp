package seedu.address.model.tag;

import java.util.Objects;
import java.util.OptionalInt;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Exam that a student has.
 */
public class Exam {

    public static final String MESSAGE_NAME_CONSTRAINTS = "Exam name should be CA1, CA2, SA1 or SA2.";
    public static final String MESSAGE_SCORE_CONSTRAINTS = "Exam score should be between 0 to 100.";

    public final ExamType examName;
    public OptionalInt score = OptionalInt.empty();

    /**
     * Constructs a {@code Exam}.
     *
     * @param examName A valid exam name.
     */
    public Exam(String examName) {
        requireNonNull(examName);
        checkArgument(isValidExamName(examName), MESSAGE_NAME_CONSTRAINTS);
        this.examName = ExamType.EXAM_TYPE_MAP.get(examName);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidExamName(String test) {
        return ExamType.EXAM_TYPE_MAP.containsKey(test.toUpperCase());
    }

    /**
     * Returns the score of the exam, else -1 if score is not present.
     */
    public int getScore() {
        return this.score.orElse(-1);
    }

    /**
     * Updates the score pf the exam.
     * @param score Score of the exam.
     */
    public void updateScore(int score) {
        requireNonNull(score);
        checkArgument(score <= 100 && score >= 0, MESSAGE_SCORE_CONSTRAINTS);
        this.score = OptionalInt.of(score);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Exam // instanceof handles nulls
                && this.examName.equals(((Exam) other).examName)
                && this.score.equals(((Exam) other).score)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.examName, this.score);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return String.format("%s: $d", this.examName, this.score.orElse(-1));
    }

}
