package seedu.address.logic.parser.event;

import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import seedu.address.logic.commands.event.ViewEventsCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewEventsCommand object
 */
public class ViewEventsCommandParser implements Parser<ViewEventsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewEventsCommand
     * and returns a ViewEventsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewEventsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String viewEventsOption = PREFIX_OPTION + ViewEventsCommand.COMMAND_OPTION;

        if (!trimmedArgs.equals(viewEventsOption)) {
            throw new ParseException(ViewEventsCommand.MESSAGE_FAILURE);
        }

        return new ViewEventsCommand();
    }
}
