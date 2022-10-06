package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new DeleteTutorialCommand object
 */
public class DeleteTutorialCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTutorialCommand
     * and returns a DeleteTutorialCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTutorialCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTutorialCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTutorialCommand.MESSAGE_USAGE), pe);
        }
    }
}
