package seedu.address.logic.parser;

import seedu.address.logic.commands.ListMessageCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListMessageCommand object
 */
public class ListMessageCommandParser extends MessageCommandGroupParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ListMessageCommand
     * and returns a ListMessageCommand object for execution
     * @throws ParseException if the user input does not conform to the specified format
     */
    public ListMessageCommand parse(String args) throws ParseException {
        return new ListMessageCommand();
    }
}
