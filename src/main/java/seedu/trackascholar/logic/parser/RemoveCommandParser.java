package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.trackascholar.logic.commands.RemoveCommand;
import seedu.trackascholar.logic.parser.exceptions.ParseException;
import seedu.trackascholar.model.applicant.ApplicationStatus;

/**
 * Parses input arguments and creates a new RemoveCommand object.
 */
public class RemoveCommandParser implements Parser<RemoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns a RemoveCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public RemoveCommand parse(String args) throws ParseException {
        try {
            ApplicationStatus status = ParserUtil.parseCompletedApplicationStatus(args);
            return new RemoveCommand(status);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE), pe);
        }
    }

}
