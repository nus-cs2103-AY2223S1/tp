package seedu.workbook.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.commons.util.AppUtil.checkArgument;

import seedu.workbook.commons.util.StringUtil;
import seedu.workbook.model.internship.util.StageUtil;

/**
 * Represents an Internship's application stage in WorkBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidStage(String)}
 */
public class Stage {

    // CHECKSTYLE.OFF: LineLength
    public static final String MESSAGE_CONSTRAINTS = "Stage should only contain alphanumeric characters and spaces, and it should not be blank";
    // CHECKSTYLE.ON: LineLength

    /*
     * The first character of the Stage must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Stage}.
     *
     * @param stage A valid Stage name.
     */
    public Stage(String stage) {
        requireNonNull(stage);
        checkArgument(isValidStage(stage), MESSAGE_CONSTRAINTS);
        String modifiedStage = StringUtil.toPascalCase(stage);
        value = modifiedStage;
    }

    /**
     * Returns true if a given string is a valid stage.
     */
    public static boolean isValidStage(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given stage does not have curated tips.
     */
    public boolean hasNoTips() {
        return !StageUtil.stageHasTips(this);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Stage // instanceof handles nulls
                && value.equals(((Stage) other).value)); // state check
    }


    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
