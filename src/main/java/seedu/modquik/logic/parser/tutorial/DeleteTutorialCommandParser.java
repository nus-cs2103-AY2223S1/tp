package seedu.modquik.logic.parser.tutorial;

import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.modquik.commons.core.index.Index;
import seedu.modquik.logic.commands.tutorial.DeleteTutorialCommand;
import seedu.modquik.logic.parser.Parser;
import seedu.modquik.logic.parser.ParserUtil;
import seedu.modquik.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteTutorialCommandParser implements Parser<DeleteTutorialCommand> {

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
