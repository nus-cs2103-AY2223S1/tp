package seedu.watson.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.watson.commons.util.AppUtil.checkArgument;

/**
 * Represents the index number of a student
 */
public class IndexNumber {

    public static final String MESSAGE_CONSTRAINTS =
        "Index number should only contain numbers from 1 to 99";

    public final String indexNumber;

    /**
     * Constructs an {@code IndexNumber} object with the given indexNumber.
     *
     * @param indexNumber The indexNumber of the student.
     */
    public IndexNumber(String indexNumber) {
        requireNonNull(indexNumber);
        checkArgument(isValidIndexNumber(indexNumber), MESSAGE_CONSTRAINTS);
        this.indexNumber = indexNumber;
    }

    /**
     * Checks if a indexNumber is in the range [1, 99].
     *
     * @param test The indexNumber to test.
     * @return A boolean.
     */
    public static boolean isValidIndexNumber(String test) {
        Integer ind = Integer.valueOf(test);
        return ind >= 1 && ind <= 99;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof IndexNumber // instanceof handles nulls
                   && indexNumber.equals(((IndexNumber) other).indexNumber)); // state check
    }

    @Override
    public String toString() {
        return indexNumber;
    }
}
