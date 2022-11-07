package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnMarkTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnMarkTaskCommand object
 */
public class UnMarkTaskCommandParser implements Parser<UnMarkTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnMarkTaskCommand
     * and returns a UnMarkTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnMarkTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnMarkTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnMarkTaskCommand.MESSAGE_USAGE), pe);
        }
    }

}

