package seedu.address.model.attribute.exceptions;

/**
 * Encapsulates an DuplicateAttributeException
 */
public class DuplicateAttributeException extends AttributeException {

    private static final String MESSAGE = "Duplicate attribute found. You provided: %s, previously stored: %s";

    /**
     * Constructs a DuplicateAttributeException
     * @param existingAttributeName
     * @param newAttributeName
     */
    public DuplicateAttributeException(String existingAttributeName, String newAttributeName) {
        super(String.format(MESSAGE, newAttributeName, existingAttributeName));
    }

}
