package seedu.rc4hdb.storage.csv.exceptions;

import java.io.IOException;

/**
 * Represents an exception thrown when the CSV file read has an invalid format.
 */
public class InvalidCsvFileFormatException extends IOException {

    public static final String DEFAULT_ERROR_MESSAGE = "Refer to the user guide for the correct file format.";

    /**
     * Creates a default InvalidCsvFileFormatException.
     */
    public InvalidCsvFileFormatException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

}
