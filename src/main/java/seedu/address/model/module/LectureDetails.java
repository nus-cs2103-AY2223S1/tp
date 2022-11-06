package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's lecture details in the address book,-.
 * Guarantees: immutable; is valid as declared in {@link #areValidLectureDetails(String)}
 */
public class LectureDetails {

    public static final String MESSAGE_CONSTRAINTS =
            "Lecture Details are optional but cannot take empty values";
    public static final String EMPTY_LECTURE_DETAILS = "";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs {@code LectureDetails}
     *
     * @param lectureDetails Valid lecture details
     */
    public LectureDetails(String lectureDetails) {
        requireNonNull(lectureDetails);
        checkArgument(areValidLectureDetails(lectureDetails));
        value = lectureDetails;
    }

    /**
     * Returns true if a given string is a valid lecture detail.
     */
    public static boolean areValidLectureDetails(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (value == null) {
            return EMPTY_LECTURE_DETAILS;
        }
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LectureDetails // instanceof handles nulls
                && value.equals(((LectureDetails) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
