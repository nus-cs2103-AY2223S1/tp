package seedu.address.model.attribute.exceptions;

public class DuplicateAttributeException extends AttributeException {

    private static final String MESSAGE = "Duplicate attribute found. You provided: %s, previously stored: %s";

    public DuplicateAttributeException(String existingAttributeName, String newAttributeName) {
        super(String.format(MESSAGE, newAttributeName, existingAttributeName));
    }

}
