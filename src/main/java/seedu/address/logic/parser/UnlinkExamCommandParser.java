package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnlinkExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * UnlinkExamCommandParser parses the arguments provided by the user and creates a new UnlinkExamCommand object.
 */
public class UnlinkExamCommandParser implements Parser<UnlinkExamCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnlinkExamCommand
     * and returns a UnlinkedExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnlinkExamCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnlinkExamCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnlinkExamCommand.MESSAGE_USAGE), pe);
        }
    }
}
