package seedu.address.wrapper.exceptions;

/**
 * Represents an error which occurs during parsing of a {@link org.json.JSONObject}.
 */
public class JsonParseException extends RuntimeException {

    public JsonParseException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code JsonParseException} with the specified detail {@code message} and {@code cause}.
     */
    public JsonParseException(String message, Throwable cause) {
        super(message, cause);
    }
}


