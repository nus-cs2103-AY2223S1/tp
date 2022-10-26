package seedu.address.model.attribute.exceptions;

/**
 * Encapsulates an exception for checked exceptions during the evaluation
 * of Attribute-related commands
 */
public class AttributeException extends Exception {

    /**
     * Constructs an AttributeException
     * @param msg
     */
    public AttributeException(String msg) {
        super(msg);
    }

}
