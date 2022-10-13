package nus.climods.logic.parser;

import static nus.climods.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import nus.climods.logic.commands.ExitCommand;
import nus.climods.logic.parser.exceptions.ParseException;

/**
 * Parser that parses the exit command from user.
 */
public class ExitCommandParser implements Parser<ExitCommand> {

    private static final String DEFAULT_ERROR_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExitCommand.MESSAGE_USAGE);

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand and returns an AddCommand object
     * for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExitCommand parse(String args) throws ParseException {
        String trimmedInput = args.trim();
        if (trimmedInput.isEmpty()) {
            throw new ParseException(DEFAULT_ERROR_MESSAGE);
        }
        return new ExitCommand();
    }
}
