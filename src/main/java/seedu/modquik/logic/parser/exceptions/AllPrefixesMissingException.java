package seedu.modquik.logic.parser.exceptions;

import java.util.Arrays;

import seedu.modquik.commons.core.Messages;
import seedu.modquik.logic.parser.Prefix;

/**
 * Represents userinput command is missing some prefixes.
 */
public class AllPrefixesMissingException extends ParseException {
    /**
     * Creates an instance.
     *
     * @param prefixes The list of prefixes that are missing in the userinput
     */
    public AllPrefixesMissingException(Prefix[] prefixes, String messageUsage) {
        super(String.format(
                Messages.MESSAGE_MISSING_PREFIXES_ALL, Arrays.toString(prefixes), messageUsage
        ));
    }
}
