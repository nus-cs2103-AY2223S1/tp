package seedu.application.logic.parser;

import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.RemoveInterviewCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.logic.parser.exceptions.ParseIntegerOverflowException;
import seedu.application.logic.parser.exceptions.ParseUnknownPrefixFoundException;

/**
 * Parses input arguments and creates a new RemoveInterviewCommand object
 */
public class RemoveInterviewCommandParser implements Parser<RemoveInterviewCommand> {
    private static final int NUMBER_OF_PREFIX_REQUIRED = 0;

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveInterviewCommand
     * and returns a RemoveInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveInterviewCommand parse(String args) throws ParseException {
        if (ArgumentTokenizer.prefixFound(args).size() > NUMBER_OF_PREFIX_REQUIRED) {
            throw new ParseUnknownPrefixFoundException(Parser.MESSAGE_UNKNOWN_PREFIX_FOUND
                    + RemoveInterviewCommand.MESSAGE_USAGE);
        }

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
