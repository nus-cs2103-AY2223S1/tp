package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FilterPlanModCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.PlanModContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterPlanModCommand object
 */
public class FilterPlanModCommandParser implements Parser<FilterPlanModCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterPlanModCommand
     * and returns a FilterPlanModCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterPlanModCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPlanModCommand.MESSAGE_USAGE));
        }

        String keyword = trimmedArgs;
        String[] keywords = trimmedArgs.split(" ");
        ParserUtil.parsePlannedModules(Arrays.asList(keywords));

        return new FilterPlanModCommand(new PlanModContainsKeywordsPredicate(keyword));
    }
}
