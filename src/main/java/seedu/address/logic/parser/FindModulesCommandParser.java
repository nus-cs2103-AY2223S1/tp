package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindModulesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;




/**
 * Parses input arguments and creates a new FindModulesCommand object
 */
public class FindModulesCommandParser implements Parser<FindModulesCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindModulesCommand
     * and returns a FindModulesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindModulesCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModulesCommand.MESSAGE_USAGE));
        }
        return new FindModulesCommand(new ModuleCodeContainsKeywordsPredicate(Arrays.asList(trimmedArgs)));
    }
}
