package seedu.address.model.attribute.exceptions;

/**
 * Encapsulates an AttributeNotFoundException
 */
public class AttributeNotFoundException extends AttributeException {

    private static final String MESSAGE = "Attribute %s is not found";

    /**
     * Cosntructs an AttributeNotFoundException
     * @param attributeName
     */
    public AttributeNotFoundException(String attributeName) {
        super(String.format(MESSAGE, attributeName));
    }

}

