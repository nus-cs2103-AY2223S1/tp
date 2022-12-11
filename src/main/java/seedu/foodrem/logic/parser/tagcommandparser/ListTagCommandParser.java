package seedu.foodrem.logic.parser.tagcommandparser;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.logic.commands.tagcommands.ListTagCommand;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a ListTagCommand object.
 */
public class ListTagCommandParser implements Parser<ListTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListTagCommand
     * and returns an ListTagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (!args.trim().isBlank()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListTagCommand.getUsage()));
        }

        return new ListTagCommand();
    }
}
