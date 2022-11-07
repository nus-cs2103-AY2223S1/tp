package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.property.DeletePropertyCommand;
import seedu.condonery.logic.parser.Parser;
import seedu.condonery.logic.parser.ParserUtil;
import seedu.condonery.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePropertyCommand object
 */
public class DeletePropertyCommandParser implements Parser<DeletePropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeletePropertyCommand
     * and returns a Command object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeletePropertyCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeletePropertyCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePropertyCommand.MESSAGE_USAGE), pe);
        }
    }
}
