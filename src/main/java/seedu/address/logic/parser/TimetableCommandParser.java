package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TimetableCommand;
import seedu.address.logic.commands.TimetableIndexCommand;
import seedu.address.logic.commands.TimetableUserCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses commands to show timetable.
 */
public class TimetableCommandParser implements Parser<TimetableCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TimetableCommand
     * and returns a TimetableCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TimetableCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        String preamble = argMultimap.getPreamble();


        if (preamble.equals("user")) {
            return new TimetableUserCommand();
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(preamble);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableCommand.MESSAGE_USAGE), pe);
        }

        return new TimetableIndexCommand(index);
    }
}
