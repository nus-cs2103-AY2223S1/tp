package seedu.address.model.attribute.exceptions;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Encapsulates an exception for checked exceptions during the evaluation
 * of Attribute-related commands
 */
public class AttributeException extends CommandException {

    /**
     * Constructs an AttributeException
     * 
     * @param msg
     */
    public AttributeException(String msg) {
        super(msg);
    }

}
