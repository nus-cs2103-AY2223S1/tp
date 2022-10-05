package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListRCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new ListRCommand object
 */
public class ListRCommandParser implements Parser<ListRCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListRCommand
     * and returns a ListRCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListRCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListRCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRCommand.MESSAGE_USAGE), pe);
        }
    }
}
