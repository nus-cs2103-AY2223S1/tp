package seedu.foodrem.logic.parser.generalcommandparser;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.logic.commands.generalcommands.ExitCommand;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a ExitCommand object.
 */
public class ExitCommandParser implements Parser<ExitCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ExitCommand
     * and returns an ExitCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExitCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (!args.trim().isBlank()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ExitCommand.getUsage()));
        }

        return new ExitCommand();
    }
}
