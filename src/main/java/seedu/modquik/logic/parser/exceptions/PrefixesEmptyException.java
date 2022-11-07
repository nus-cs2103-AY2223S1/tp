package seedu.modquik.logic.parser.exceptions;

import java.util.Arrays;

import seedu.modquik.commons.core.Messages;
import seedu.modquik.logic.parser.Prefix;

/**
 * Represents userinput command is contains prefixes that should not be empty (empty string).
 */
public class PrefixesEmptyException extends ParseException {
    /**
     * Creates an instance.
     *
     * @param prefixes The list of prefixes that are missing in the userinput
     */
    public PrefixesEmptyException(Prefix[] prefixes, String messageUsage) {
        super(String.format(
                Messages.MESSAGE_EMPTY_PREFIXES, Arrays.toString(prefixes), messageUsage
        ));
    }
}
