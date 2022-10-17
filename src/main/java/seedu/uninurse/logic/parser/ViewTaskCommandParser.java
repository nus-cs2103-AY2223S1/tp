package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.ViewTaskCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewTaskCommand object.
 */
public class ViewTaskCommandParser implements Parser<ViewTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewTaskCommand
     * and returns a ViewTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
