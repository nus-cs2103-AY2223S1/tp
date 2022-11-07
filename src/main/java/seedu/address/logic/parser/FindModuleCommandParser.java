package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindModuleCommand object
 */
public class FindModuleCommandParser implements Parser<FindModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindModuleCommand
     * and returns a FindModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindModuleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        return new FindModuleCommand(Arrays.asList(keywords));
    }

}
