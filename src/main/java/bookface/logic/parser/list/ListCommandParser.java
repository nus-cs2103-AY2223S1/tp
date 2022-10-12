package bookface.logic.parser.list;

import bookface.commons.core.Messages;
import bookface.logic.commands.list.ListCommand;
import bookface.logic.parser.CommandParser;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ListCommandParser extends CommandParser<ListCommand> {
    public ListCommandParser() {
        super(ListCommand.MESSAGE_USAGE);
    }

    @Override
    protected ListCommand handleParsedCommand(String commandWord, String args) throws ParseException {
        ListSubcommand listType;
        try {
            listType = ListSubcommand.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            //todo return Messages.UKNOWN COMMAND instead?
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ListCommand.MESSAGE_USAGE));
        }
        return listType.runParseFunction(args);
    }
}
