package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a RemoveTaskCommand object.
 */
public class RemoveTaskCommandParser implements Parser<RemoveTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveTaskCommand
     * and returns a RemoveTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new RemoveTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
