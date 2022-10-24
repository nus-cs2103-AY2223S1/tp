package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindEventsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventTitleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindEventsCommand object
 */
public class FindEventsCommandParser implements Parser<FindEventsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindEventsCommand
     * and returns a FindEventsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEventsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventsCommand.MESSAGE_USAGE));
        }

        String[] eventTitleKeywords = trimmedArgs.split("\\s+");

        return new FindEventsCommand(new EventTitleContainsKeywordsPredicate(Arrays.asList(eventTitleKeywords)));
    }

}
