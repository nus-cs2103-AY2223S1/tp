package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.FileUtil;

/**
 * Represents a Person's pdf file in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFilePath(String)}
 */
public class FilePath {

    public static final String MESSAGE_CONSTRAINTS =
            "FilePath should be a valid file path in your directory";

    public final String value;

    /**
     * Constructs a {@code FilePath}.
     *
     * @param filePath A valid file path.
     */
    public FilePath(String filePath) {
        requireNonNull(filePath);
        checkArgument(isValidPdfFilePath(filePath), MESSAGE_CONSTRAINTS);
        this.value = filePath;
    }

    /**
     * Returns true if a given string is a valid PDF file Path.
     */
    public static boolean isValidPdfFilePath(String test) {
        return  FileUtil.isValidPath(test) && test.contains(".pdf") && FileUtil.checkPdfFilePath(test);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilePath // instanceof handles nulls
                && value.equals(((FilePath) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}