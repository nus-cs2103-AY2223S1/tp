package seedu.foodrem.logic.parser.generalcommandparser;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.logic.commands.generalcommands.ResetCommand;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a ResetCommand object.
 */
public class ResetCommandParser implements Parser<ResetCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ResetCommand
     * and returns an ResetCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ResetCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (!args.trim().isBlank()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ResetCommand.getUsage()));
        }

        return new ResetCommand();
    }
}
