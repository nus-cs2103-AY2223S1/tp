package seedu.modquik.logic.parser.reminder;
import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.modquik.commons.core.index.Index;
import seedu.modquik.logic.commands.reminder.UnmarkReminderCommand;
import seedu.modquik.logic.parser.Parser;
import seedu.modquik.logic.parser.ParserUtil;
import seedu.modquik.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkReminderCommand object
 */
public class UnmarkReminderCommandParser implements Parser<UnmarkReminderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkReminderCommand
     * and returns a UnmarkReminderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkReminderCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnmarkReminderCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkReminderCommand.MESSAGE_USAGE), pe);
        }
    }
}
