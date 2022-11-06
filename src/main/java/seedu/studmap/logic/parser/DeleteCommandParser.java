package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.ParserUtil.separatePreamble;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.DeleteCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements IndexCommandParser<DeleteCommand> {

    /**
     * Returns empty prefix list.
     * @return Empty prefix list
     */
    @Override
    public Prefix[] getPrefixes() {
        return new Prefix[0];
    }

    /**
     * Returns Usage Message for DeleteCommand.
     * @return Usage Message for DeleteCommand.
     */
    @Override
    public String getUsageMessage() {
        return DeleteCommand.MESSAGE_USAGE;
    }

    /**
     * Returns a DeleteCommand object for execution.
     * @param argMultimap        Argument multimap to process
     * @param indexListGenerator students affected by this command, wrapped in a function to be used later.
     * @return DeleteCommand object for execution
     * @throws ParseException if a ParseException is detected
     */
    @Override
    public DeleteCommand getIndexCommand(ArgumentMultimap argMultimap, IndexListGenerator indexListGenerator)
            throws ParseException {
        return new DeleteCommand(indexListGenerator);
    }

    /**
     * Checks if there is only one preamble, and throws ParseException if not.
     * @param preamble Preamble provided in command input
     * @throws ParseException if there is more or less than one preamble
     */
    @Override
    public void validatePreamble(String preamble) throws ParseException {
        if (separatePreamble(preamble).length != 1) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}
