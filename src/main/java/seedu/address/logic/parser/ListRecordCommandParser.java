package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListRCommand object
 */
public class ListRecordCommandParser implements Parser<ListRecordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListRCommand
     * and returns a ListRCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListRecordCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListRecordCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRecordCommand.MESSAGE_USAGE), pe);
        }
    }
}
