package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowCommand object
 */
public class ShowCommandParser implements Parser<ShowCommand> {

    /**
     * Returns a boolean expression if argument is valid
     * @param args Arguments that are passed into parser
     */
    public static boolean isValidArgument(String args) {
        String trimmedArgs = args.trim();
        String[] validArgs = new String[] {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (int i = 0; i < validArgs.length; i++) {
            if (validArgs[i].equalsIgnoreCase(trimmedArgs)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ShowCommand
     * and returns a ShowCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || !(isValidArgument(args))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }

        String dayKeyword = trimmedArgs;
        dayKeyword = dayKeyword.substring(0, 1).toUpperCase() + dayKeyword.substring(1).toLowerCase();

        return new ShowCommand(dayKeyword);
    }
}
