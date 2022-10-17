package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RemoveFieldCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code RemoveFieldCommand} object
 */
public class RemoveFieldCommandParser implements Parser<RemoveFieldCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemoveFieldCommand}
     * and returns a {@code RemoveFieldCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveFieldCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveFieldCommand.MESSAGE_USAGE));
        }
        requireNonNull(trimmedArgs);
        return new RemoveFieldCommand(trimmedArgs);
    }
}
