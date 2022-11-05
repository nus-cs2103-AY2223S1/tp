package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FilterCurrModCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.CurrModContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterCurrModCommand object
 */
public class FilterCurrModCommandParser implements Parser<FilterCurrModCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCurrModCommand
     * and returns a FilterCurrModCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCurrModCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCurrModCommand.MESSAGE_USAGE));
        }

        String keyword = trimmedArgs;
        String[] keywords = trimmedArgs.split(" ");
        ParserUtil.parseCurrentModules(Arrays.asList(keywords));

        return new FilterCurrModCommand(new CurrModContainsKeywordsPredicate(keyword));
    }
}
