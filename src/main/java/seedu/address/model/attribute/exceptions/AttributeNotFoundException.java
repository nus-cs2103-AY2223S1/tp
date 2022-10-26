package seedu.address.model.attribute.exceptions;

public class AttributeNotFoundException extends AttributeException {

    private static final String MESSAGE = "Attribute %s is not found";

    public AttributeNotFoundException(String attributeName) {
        super(String.format(MESSAGE, attributeName));
    }

}

