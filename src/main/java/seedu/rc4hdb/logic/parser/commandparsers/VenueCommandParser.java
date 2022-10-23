package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.rc4hdb.logic.commands.misccommands.HelpCommand;
import seedu.rc4hdb.logic.commands.venuecommands.BookCommand;
import seedu.rc4hdb.logic.commands.venuecommands.VenueCommand;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new VenueCommand object.
 */
public class VenueCommandParser implements CommandParser<VenueCommand> {

    private static final Pattern VENUE_COMMAND_FORMAT = Pattern.compile("(?<secondCommandWord>\\S+) (?<arguments>.+)");

    @Override
    public VenueCommand parse(String userInput) throws ParseException {
        final Matcher matcher = VENUE_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String secondCommandWord = matcher.group("secondCommandWord");
        final String args = matcher.group("arguments");

        switch (secondCommandWord) {

        case BookCommand.COMMAND_WORD:
            return new BookCommandParser().parse(args);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
