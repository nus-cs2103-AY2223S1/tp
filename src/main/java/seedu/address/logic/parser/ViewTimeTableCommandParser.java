package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.schedule.ViewTimeTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewTimeTableCommand object
 */
public class ViewTimeTableCommandParser implements Parser<ViewTimeTableCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewTimeTableCommand
     * and returns a ViewTimeTableCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewTimeTableCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ViewTimeTableCommand();
        } else {
            if (trimmedArgs.equals("0")) {
                return new ViewTimeTableCommand(0);
            } else if (trimmedArgs.equals("1")) {
                return new ViewTimeTableCommand(1);
            }
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTimeTableCommand.MESSAGE_USAGE));

        }

    }

}
