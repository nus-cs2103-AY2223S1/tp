package seedu.rc4hdb.commons.exceptions;

import static java.util.Objects.requireNonNull;

/**
 * Represents an error during conversion of data from one format to another
 */
public class DataConversionException extends Exception {

    /**
     * Constructs a DataConversionException caused be {@code cause}.
     */
    public DataConversionException(Exception cause) {
        super(cause);
        requireNonNull(cause);
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        String[] messageSegment = message.split(":");
        if (messageSegment.length > 1) {
            return message.substring(messageSegment[0].length() + 1).trim();
        }
        return "";
    }
}
