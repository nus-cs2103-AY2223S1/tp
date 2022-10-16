package soconnect.model.person.SearchPerson;

import soconnect.commons.util.StringUtil;
import soconnect.logic.commands.SearchCommand;
import soconnect.logic.parser.Prefix;
import soconnect.logic.parser.exceptions.ParseException;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.CliSyntax.INDICATOR_ADDRESS;
import static soconnect.logic.parser.CliSyntax.INDICATOR_EMAIL;
import static soconnect.logic.parser.CliSyntax.INDICATOR_NAME;
import static soconnect.logic.parser.CliSyntax.INDICATOR_PHONE;
import static soconnect.logic.parser.CliSyntax.INDICATOR_TAG;

/**
 * Translates a {@code Prefix} into the equivalent enum type.
 */
public class SearchPrefix {

    /**
     * Contains the currently acceptable {@code SearchPrefixCommand}.
     */
    public enum SearchPrefixCommand {
        NAME,
        ADDRESS,
        EMAIL,
        PHONE,
        TAG,
        NOTPREFIX
    }

    /**
     * Determines the enum type of {@code SearchPrefixCommand} based on the specified {@code Prefix} command.
     * If there is no matching prefix, a default prefix tag is assumed to show relevant search result.
     */
    public static SearchPrefixCommand convertPrefixToEnumType(Prefix prefix) {
        String prefixInString = prefix.getPrefix();
        switch (prefixInString) {
        case INDICATOR_NAME:
            return SearchPrefixCommand.NAME;
        case INDICATOR_ADDRESS:
            return SearchPrefixCommand.ADDRESS;
        case INDICATOR_EMAIL:
            return SearchPrefixCommand.EMAIL;
        case INDICATOR_PHONE:
            return SearchPrefixCommand.PHONE;
        case INDICATOR_TAG:
            return SearchPrefixCommand.TAG;
        default:
            return SearchPrefixCommand.NOTPREFIX;
        }
    }
}
