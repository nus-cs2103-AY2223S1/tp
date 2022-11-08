package bookface.logic.parser.primary;

import bookface.commons.core.Messages;
import bookface.logic.commands.Command;
import bookface.logic.parser.CommandParser;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class PrimaryParser extends CommandParser<Command> {
    public PrimaryParser() {
        super(Command.MESSAGE_USAGE);
    }

    @Override
    protected Command handleParsedCommand(String commandWord, String args) throws ParseException {
        PrimaryCommand taskType;
        try {
            taskType = PrimaryCommand.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(Messages.MESSAGE_UNKNOWN_COMMAND, Command.MESSAGE_USAGE));
        }
        return taskType.runParseFunction(args);
    }
}
