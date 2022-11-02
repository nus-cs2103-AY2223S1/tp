package seedu.address.commons.core.filename;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the file name to be created
 */
public class FileName {
    public static final String MESSAGE_CONSTRAINTS = "FileName must follow the following criteria:\n"
            + "- Can only contain alphanumeric characters, whitespaces, hyphen(-), underscore(_) and full stop(.)\n"
            + "- Name must be at least 3 characters long and a maximum of 30 characters";
    private static final String VALIDATION_REGEX = "^[\\w\\-\\s.]{3,30}$";
    private String fileName;

    /**
     * FileName must be non-null and only contain number, letters, underscore, hyphen and period.
     */
    public FileName(String fileName) {
        requireNonNull(fileName);
        checkArgument(isValidFileName(fileName), MESSAGE_CONSTRAINTS);
        this.fileName = fileName;
    }

    /**
     * Returns true if test is a valid file name
     */
    public static boolean isValidFileName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns fileName with .csv
     * @return String
     */
    public String toCsvFormat() {
        return fileName + ".csv";
    }

    @Override
    public String toString() {
        return fileName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FileName // instanceof handles nulls
                && fileName.equals(((FileName) other).fileName)); // state check
    }

    @Override
    public int hashCode() {
        return fileName.hashCode();
    }

}
