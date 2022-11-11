package seedu.studmap.logic.imports.exceptions;

import seedu.studmap.logic.imports.ImportCsv;

/**
 * Represents an error which occurs during execution of an {@link ImportCsv}.
 */
public class ImportException extends Exception {

    public ImportException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ImportException} with the specified detail {@code message} and {@code cause}.
     */
    public ImportException(String message, Throwable cause) {
        super(message, cause);
    }
}
