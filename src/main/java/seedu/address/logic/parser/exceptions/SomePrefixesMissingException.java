package seedu.address.logic.parser.exceptions;

import java.util.Arrays;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Prefix;

/**
 * Represents userinput command is missing some prefixes.
 */
public class SomePrefixesMissingException extends ParseException {
    /**
     * Creates an instance.
     *
     * @param prefixes The list of prefixes that are missing in the userinput
     */
    public SomePrefixesMissingException(Prefix[] prefixes) {
        super(String.format(
                Messages.MESSAGE_MISSING_PREFIXES_SOME, Arrays.toString(prefixes), ""
        ));
    }
}
