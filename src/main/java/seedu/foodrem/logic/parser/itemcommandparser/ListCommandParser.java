package seedu.foodrem.logic.parser.itemcommandparser;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.logic.commands.itemcommands.ListCommand;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a ListCommand object.
 */
public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (!args.trim().isBlank()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.getUsage()));
        }

        return new ListCommand();
    }
}
