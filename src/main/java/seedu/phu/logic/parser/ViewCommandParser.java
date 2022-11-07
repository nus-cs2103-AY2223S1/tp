package seedu.phu.logic.parser;

import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.phu.commons.core.index.Indexes;
import seedu.phu.logic.commands.ViewCommand;
import seedu.phu.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        try {
            String trimmedArgs = args.trim();
            String[] selectedIndex = trimmedArgs.split("\\s+");
            if (selectedIndex.length > 1) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }
            Indexes index = new Indexes(selectedIndex);

            return new ViewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
        }
    }
}
