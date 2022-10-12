package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.GoToCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCodeMatchesKeywordPredicate;

/**
 * Parses input arguments and creates a new GoToCommand object
 */
public class GoToCommandParser implements Parser<GoToCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the GoToCommand
     * and returns a GoToCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GoToCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE));
        }

        return new GoToCommand(new ModuleCodeMatchesKeywordPredicate(trimmedArgs));
    }
}
