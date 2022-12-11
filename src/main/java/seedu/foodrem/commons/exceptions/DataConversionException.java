package seedu.foodrem.commons.exceptions;

/**
 * Represents an error during conversion of data from one format to another
 */
public class DataConversionException extends Exception {
    /**
     * Constructs a DataConversionException.
     */
    public DataConversionException(Exception cause) {
        super(cause);
    }
}
