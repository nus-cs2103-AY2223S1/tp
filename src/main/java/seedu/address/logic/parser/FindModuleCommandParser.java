package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCodeStartsWithKeywordPredicate;

/**
 * Parses input arguments and creates a new {@code FindModuleCommand} object.
 */
public class FindModuleCommandParser implements Parser<FindModuleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code FindModuleCommand}
     * and returns a {@code FindModuleCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleCommand.MESSAGE_USAGE));
        }

        return new FindModuleCommand(new ModuleCodeStartsWithKeywordPredicate(trimmedArgs));
    }
}
