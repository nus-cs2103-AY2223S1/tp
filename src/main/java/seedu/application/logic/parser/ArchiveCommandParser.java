package seedu.application.logic.parser;

import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.ArchiveCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.logic.parser.exceptions.ParseIntegerOverflowException;

/**
 * Parses input arguments and creates a new ArchiveCommand object.
 */
public class ArchiveCommandParser implements Parser<ArchiveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ArchiveCommand
     * and returns a ArchiveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ArchiveCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ArchiveCommand(index);
        } catch (ParseIntegerOverflowException e) {
            // Rethrow exception if index formatted correctly but too large to store in an int
            throw e;
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ArchiveCommand.MESSAGE_USAGE), pe);
        }
    }

}
