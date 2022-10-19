package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import seedu.address.logic.commands.event.ViewUpcomingEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewUpcomingEventCommand object
 */
public class ViewUpcomingEventCommandParser implements Parser<ViewUpcomingEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewUpcomingEventCommand
     * and returns a ViewUpcomingEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewUpcomingEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OPTION);
        String daysInput = argMultimap.getOptionArgs();

        if (daysInput.isEmpty()) {
            throw new ParseException(
                    String.format(ViewUpcomingEventCommand.MESSAGE_MISSING_DAYS, ViewUpcomingEventCommand.MESSAGE_USAGE));
        }

        try {
            int days = ParserUtil.parseDays(daysInput);
            return new ViewUpcomingEventCommand(days);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(ViewUpcomingEventCommand.MESSAGE_INVALID_EVENT_UPCOMING_DAYS,
                            ViewUpcomingEventCommand.MESSAGE_USAGE), pe);
        }
    }
}
