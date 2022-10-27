package seedu.address.logic.parser.deletecommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.deletecommands.DeletePetCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePetCommand object.
 */
public class DeletePetCommandParser implements Parser<DeletePetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeletePetCommand
     * and returns a DeletePetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePetCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeletePetCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePetCommand.MESSAGE_USAGE), pe);
        }
    }
}
