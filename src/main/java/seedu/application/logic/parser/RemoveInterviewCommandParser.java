package seedu.application.logic.parser;

import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.RemoveInterviewCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.logic.parser.exceptions.ParseIntegerOverflowException;

/**
 * Parses input arguments and creates a new RemoveInterviewCommand object
 */
public class RemoveInterviewCommandParser implements Parser<RemoveInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveInterviewCommand
     * and returns a RemoveInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveInterviewCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new RemoveInterviewCommand(index);
        } catch (ParseIntegerOverflowException e) {
            // Rethrow exception if index formatted correctly but too large to store in an int
            throw e;
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveInterviewCommand.MESSAGE_USAGE), pe);
        }
    }

}
