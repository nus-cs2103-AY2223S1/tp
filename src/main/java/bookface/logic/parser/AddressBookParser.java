package bookface.logic.parser;

import bookface.commons.core.Messages;
import bookface.logic.commands.Command;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser extends CommandParser<Command> {
    public AddressBookParser(String messageUsage) {
        super(messageUsage);
    }

    @Override
    protected Command handleParsedCommand(String commandWord, String arguments) throws ParseException {
        MainCommands taskType;
        try {
            taskType = MainCommands.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
        return taskType.runParseFunction(arguments);
    }
}
