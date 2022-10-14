package seedu.nutrigoals.logic.parser;

import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.nutrigoals.logic.commands.ListCommand;
import seedu.nutrigoals.logic.parser.exceptions.ParseException;
import seedu.nutrigoals.model.meal.DateTime;
import seedu.nutrigoals.model.meal.IsFoodAddedOnThisDatePredicate;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            DateTime dateToday = new DateTime();
            return new ListCommand(new IsFoodAddedOnThisDatePredicate(dateToday));
        }

        try {
            DateTime date = ParserUtil.parseDate(args);
            return new ListCommand(new IsFoodAddedOnThisDatePredicate(date));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE), pe);
        }
    }
}
