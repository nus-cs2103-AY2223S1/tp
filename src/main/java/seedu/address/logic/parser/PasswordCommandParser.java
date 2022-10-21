package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_PASSWORD;

import seedu.address.logic.commands.PasswordCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PasswordCommand object
 */
public class PasswordCommandParser implements Parser<PasswordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PasswordCommand
     * and returns a PasswordCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PasswordCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_OLD_PASSWORD, PREFIX_NEW_PASSWORD);

        if (!argMultimap.getValue(PREFIX_NEW_PASSWORD).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PasswordCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getValue(PREFIX_OLD_PASSWORD).isPresent()) {
            return new PasswordCommand(argMultimap.getValue(PREFIX_OLD_PASSWORD).get(),
                    argMultimap.getValue(PREFIX_NEW_PASSWORD).get());
        }
        return new PasswordCommand(argMultimap.getValue(PREFIX_NEW_PASSWORD).get());
    }
}
