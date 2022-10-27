package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.condonery.logic.commands.property.FilterPropertyCommand;
import seedu.condonery.logic.parser.Parser;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.property.PropertyTagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterPropertyCommandParser implements Parser<FilterPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterPropertyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPropertyCommand.MESSAGE_USAGE));
        }

        String[] tagKeywords = trimmedArgs.split("\\s+");

        return new FilterPropertyCommand(new PropertyTagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
    }

}
