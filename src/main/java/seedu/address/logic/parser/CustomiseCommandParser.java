package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.CustomiseCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CustomiseCommand object
 */
public class CustomiseCommandParser implements Parser<CustomiseCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CustomiseCommand
     * and returns a CustomiseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CustomiseCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
        }

        String[] arguments = trimmedArgs.split("\\s+");

        if (arguments[0].equals("order")) {
            return new CustomiseCommand("order", arguments[1]);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
        }
    }
}
