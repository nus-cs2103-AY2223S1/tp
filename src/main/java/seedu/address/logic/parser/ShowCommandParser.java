package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

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
        String[] validargs = new String[] {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
        for (int i = 0; i < validargs.length; i++) {
            if ((validargs[i].toLowerCase()).equals(trimmedArgs.toLowerCase())) {
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

        String dayKeywords = trimmedArgs;

        //NameContainsKeywordsPredicate to be changed to DayContainsKeywordsPredicate
        return new ShowCommand(new NameContainsKeywordsPredicate(Arrays.asList(dayKeywords)));
    }
}
