package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddFieldCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code AddFieldCommand} object
 */
public class AddFieldCommandParser implements Parser<AddFieldCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddFieldCommand}
     * and returns a {@code AddFieldCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddFieldCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFieldCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        String prefixValue = nameKeywords[0];
        String prefixName = nameKeywords[1];
        requireNonNull(prefixValue);
        requireNonNull(prefixName);
        if (!prefixValue.endsWith("/") || prefixValue.startsWith("/")) {
            throw new ParseException("Invalid Prefix Format");
        }
        Prefix prefix = new Prefix(prefixValue);
        return new AddFieldCommand(prefix, prefixName);
    }
}
