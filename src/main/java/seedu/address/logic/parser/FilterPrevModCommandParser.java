package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FilterPrevModCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.PrevModContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterPrevModCommand object
 */
public class FilterPrevModCommandParser implements Parser<FilterPrevModCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterPrevModCommand
     * and returns a FilterPrevModCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterPrevModCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPrevModCommand.MESSAGE_USAGE));
        }

        String keyword = trimmedArgs;
        String[] keywords = trimmedArgs.split(" ");
        ParserUtil.parsePreviousModules(Arrays.asList(keywords));

        return new FilterPrevModCommand(new PrevModContainsKeywordsPredicate(keyword));
    }
}
