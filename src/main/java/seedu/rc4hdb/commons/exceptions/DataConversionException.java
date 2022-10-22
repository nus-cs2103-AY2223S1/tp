package seedu.rc4hdb.commons.exceptions;

/**
 * Represents an error during conversion of data from one format to another
 */
public class DataConversionException extends Exception {
    public DataConversionException(Exception cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return null;
        }

        String[] messageSegment = super.getMessage().split(":");
        if (messageSegment.length >= 2) {
            return messageSegment[1].trim();
        }
        return "";
    }
}
