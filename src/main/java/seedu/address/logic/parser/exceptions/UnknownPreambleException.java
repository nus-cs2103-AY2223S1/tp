package seedu.address.logic.parser.exceptions;
import seedu.address.commons.core.Messages;

/**
 * Represents userinput command is not recognised.
 */
public class UnknownPreambleException extends ParseException {
    public UnknownPreambleException(String preamble) {
        super(String.format(Messages.MESSAGE_UNKNOWN_COMMAND, preamble));
    }
}
