package bookface.logic.parser.primary;

import bookface.commons.core.Messages;
import bookface.logic.commands.Command;
import bookface.logic.commands.HelpCommand;
import bookface.logic.parser.CommandParser;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class PrimaryParser extends CommandParser<Command> {
    public PrimaryParser() {
        super(HelpCommand.MESSAGE_USAGE);
    }

    @Override
    protected Command handleParsedCommand(String commandWord, String args) throws ParseException {
        PrimaryCommands taskType;
        try {
            taskType = PrimaryCommands.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
        return taskType.runParseFunction(args);
    }
}
