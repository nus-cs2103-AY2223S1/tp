package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            args = args.substring(1);
            String[] arguments = args.split(" ", 2);
            String trimmedIndex = arguments[0].trim();
            Index index = ParserUtil.parseIndex(trimmedIndex);
            if (arguments.length == 1) {
                return new DeleteCommand(index);
            }
            String trimmedField = arguments[1].trim();
            String field = ParserUtil.parseField(trimmedField);
            return new DeleteCommand(index, field);
        } catch (ParseException | StringIndexOutOfBoundsException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
