package bookface.logic.parser.delete;

import bookface.commons.core.Messages;
import bookface.logic.commands.delete.DeleteCommand;
import bookface.logic.parser.CommandParser;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser extends CommandParser<DeleteCommand> {
    public DeleteCommandParser() {
        super(DeleteCommand.MESSAGE_USAGE);
    }

    @Override
    protected DeleteCommand handleParsedCommand(String commandWord, String args) throws ParseException {
        DeleteSubcommand deleteType;
        try {
            deleteType = DeleteSubcommand.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            //todo return Messages.UKNOWN COMMAND instead?
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MESSAGE_USAGE));
        }
        return deleteType.runParseFunction(args);
    }
}
