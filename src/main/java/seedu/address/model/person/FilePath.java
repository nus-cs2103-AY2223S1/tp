package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.SetPersonFileCommand;

/**
 * Represents a Person's pdf file in the address book.
 * Guarantees: immutable; is always valid
 */
public class FilePath {

    public static final String VALIDATION_REGEX = ".+\\.pdf$";

    public static final String MESSAGE_EMPTY_FILEPATH = "File Path has not been initiated\n"
            + "Set a file path for the client by using the command " + SetPersonFileCommand.COMMAND_WORD;

    public static final String MESSAGE_CONSTRAINTS =
            "File Path should be a valid absolute file path to a pdf in your directory\n"
            + "Example: "
            + "C:/Users/Ryzen/repos/CS2103T/tp/data/Test_PDF.pdf\n"
            + "Check if the users file path is correct";

    public static final String MESSAGE_CHANGED_FILEPATH = "File path cannot access PDF File\n"
            + "It may have been moved, edited, or deleted.\n"
            + "Check if the users file path is correct\n";

    public final String value;

    /**
     * Constructs a {@code FilePath}.
     *
     * @param filePath A valid file path or empty file path.
     */
    public FilePath(String filePath) {
        requireNonNull(filePath);
        this.value = filePath;
    }

    /**
     * Returns true if value is empty.
     */
    public boolean isEmpty() {
        return value.equals(Person.EMPTY_FIELD_VALUE);
    }

    /**
     * Returns value of file path.
     */
    public String getValue() {
        return value;
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
