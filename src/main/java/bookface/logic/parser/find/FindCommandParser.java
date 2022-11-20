package bookface.logic.parser.find;

import bookface.commons.core.Messages;
import bookface.logic.commands.find.FindCommand;
import bookface.logic.parser.CommandParser;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser extends CommandParser<FindCommand> {
    public FindCommandParser() {
        super(FindCommand.MESSAGE_USAGE);
    }

    @Override
    protected FindCommand handleParsedCommand(String commandWord, String args) throws ParseException {
        FindSubcommand findType;

        try {
            findType = FindSubcommand.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }
        return findType.runParseFunction(args);
    }
}
