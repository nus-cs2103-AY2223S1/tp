package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.SetPersonFileCommand;

/**
 * Represents a Person's pdf file in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPdfFilePath(String)}
 */
public class FilePath {

    public static final String EMPTY_FILEPATH = "";

    public static final String MESSAGE_EMPTY_FILEPATH = "File Path has not been initiated\n"
            + "Set a file path for the client by using the command " + SetPersonFileCommand.COMMAND_WORD;

    public static final String MESSAGE_CONSTRAINTS =
            "File Path should be a valid absolute file path to a pdf in your directory\n"
            + "Example: "
            + "C:/Users/Ryzen/repos/CS2103T/tp/data/Test_PDF.pdf";

    public final String value;

    /**
     * Constructs a {@code FilePath}.
     *
     * @param filePath A valid file path or empty file path.
     */
    public FilePath(String filePath) {
        requireNonNull(filePath);
        if (filePath.equals("")) {
            this.value = EMPTY_FILEPATH;
        } else {
            checkArgument(isValidPdfFilePath(filePath), MESSAGE_CONSTRAINTS);
            this.value = filePath;
        }
    }

    /**
     * Constructs an empty {@code FilePath}.
     */
    public FilePath() {
        this.value = EMPTY_FILEPATH;
    }

    /**
     * Returns true if a given string is a valid PDF file or is empty.
     */
    public static boolean isValidFilePath(String test) {
        return isEmptyPdfFilePath(test) || isValidPdfFilePath(test);
    }

    /**
     * Returns true if a given string is a valid PDF file Path.
     */
    public static boolean isValidPdfFilePath(String test) {
        return test.contains(".pdf") && FileUtil.checkPdfFilePath(test);
    }

    /**
     * Returns true if the given file path is empty
     */
    public static boolean isEmptyPdfFilePath(String test) {
        return test.equals(EMPTY_FILEPATH);
    }

    public boolean isEmpty() {
        return value.isEmpty();
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
