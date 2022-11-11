package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteInternshipCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteInternshipCommand object
 */
public class DeleteInternshipCommandParser implements Parser<DeleteInternshipCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteInternshipCommand
     * and returns a DeleteInternshipCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteInternshipCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteInternshipCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInternshipCommand.MESSAGE_USAGE), pe);
        }
    }

}
