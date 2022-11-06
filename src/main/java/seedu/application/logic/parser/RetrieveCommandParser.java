package seedu.application.logic.parser;

import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.RetrieveCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.logic.parser.exceptions.ParseIntegerOverflowException;
import seedu.application.logic.parser.exceptions.ParseUnknownPrefixFoundException;

/**
 * Parses input arguments and creates a new RetrieveCommand object.
 */
public class RetrieveCommandParser implements Parser<RetrieveCommand> {

    private static final int NUMBER_OF_PREFIX_REQUIRED = 0;

    /**
     * Parses the given {@code String} of arguments in the context of the RetrieveCommand
     * and returns a RetrieveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RetrieveCommand parse(String args) throws ParseException {

        if (ArgumentTokenizer.prefixFound(args).size() > NUMBER_OF_PREFIX_REQUIRED) {
            throw new ParseUnknownPrefixFoundException(Parser.MESSAGE_UNKNOWN_PREFIX_FOUND
                    + RetrieveCommand.MESSAGE_USAGE);
        }
        try {
            Index index = ParserUtil.parseIndex(args);
            return new RetrieveCommand(index);
        } catch (ParseIntegerOverflowException e) {
            // Rethrow exception if index formatted correctly but too large to store in an int
            throw e;
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RetrieveCommand.MESSAGE_USAGE), pe);
        }
    }
}
