package seedu.rc4hdb.storage.csv.exceptions;

import java.io.IOException;

/**
 * Represents an exception thrown when the CSV file read has an invalid format.
 */
public class InvalidCsvFileFormatException extends IOException {

    public static final String MESSAGE_INVALID_CSV_FILE_FORMAT = "The CSV file provided is in the incorrect format. "
            + "Refer to the user guide for the correct file format.";

    public InvalidCsvFileFormatException() {
        super(MESSAGE_INVALID_CSV_FILE_FORMAT);
    }

}
