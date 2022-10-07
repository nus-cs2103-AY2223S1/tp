package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTaskCommand object.
 */
public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {

    public static final String MESSAGE_INVALID_NUMBER_OF_INDICES = "Invalid number of indices";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTaskCommand
     * and returns a DeleteTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTaskCommand parse(String args) throws ParseException {
        try {
            // split string by whitespace
            String[] indices = args.trim().split("\\s+", 2);
            if (indices.length < 2) {
                throw new ParseException(MESSAGE_INVALID_NUMBER_OF_INDICES);
            }
            Index patientIndex = ParserUtil.parseIndex(indices[0]);
            Index taskIndex = ParserUtil.parseIndex(indices[1]);

            return new DeleteTaskCommand(patientIndex, taskIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
