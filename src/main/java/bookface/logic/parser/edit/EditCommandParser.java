package bookface.logic.parser.edit;

import bookface.commons.core.Messages;
import bookface.logic.commands.edit.EditCommand;
import bookface.logic.parser.CommandParser;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser extends CommandParser<EditCommand> {

    public EditCommandParser() {
        super(EditCommand.MESSAGE_USAGE);
    }

    @Override
    protected EditCommand handleParsedCommand(String commandWord, String args) throws ParseException {
        EditSubcommand findType;

        try {
            findType = EditSubcommand.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE));
        }
        return findType.runParseFunction(args);
    }
}
