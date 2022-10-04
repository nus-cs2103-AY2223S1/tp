package bookface.logic.parser.add;

import bookface.commons.core.Messages;
import bookface.logic.commands.add.AddCommand;
import bookface.logic.parser.CommandParser;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser extends CommandParser<AddCommand> {
    public AddCommandParser(String messageUsage) {
        super(messageUsage);
    }

    @Override
    protected AddCommand handleParsedCommand(String commandWord, String arguments) throws ParseException {
        AddCommands addType;
        try {
            addType = AddCommands.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            //todo return Messages.UKNOWN COMMAND instead?
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        return addType.runParseFunction(arguments);
    }
}
