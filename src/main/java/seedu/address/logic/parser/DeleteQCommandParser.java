package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteQCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteQCommand object
 */
public class DeleteQCommandParser implements Parser<DeleteQCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteQCommand
     * and returns a DeleteQCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteQCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteQCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(DeleteQCommand.MESSAGE_USAGE), pe);
        }
    }
}
