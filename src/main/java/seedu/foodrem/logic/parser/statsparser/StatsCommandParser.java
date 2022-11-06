package seedu.foodrem.logic.parser.statsparser;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.logic.commands.statscommands.StatsCommand;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a StatsCommand object.
 */
public class StatsCommandParser implements Parser<StatsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the StatsCommand
     * and returns a StatsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatsCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (!args.trim().isBlank()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.getUsage()));
        }

        return new StatsCommand();
    }
}
