package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteListingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteListingCommandParser implements Parser<DeleteListingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteListingCommand
     * and returns a DeleteListingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteListingCommand parse(String args) throws ParseException {
        Index index = ParserUtil.parseIndex(args.trim());
        return new DeleteListingCommand(index);
    }

}
