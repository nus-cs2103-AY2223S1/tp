package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Themes.DARK_THEME_STRING;
import static seedu.address.commons.core.Themes.LIGHT_THEME_STRING;

import seedu.address.commons.core.Themes.Theme;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ThemeCommand object
 */
public class ThemeCommandParser implements Parser<ThemeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ThemeCommand
     * and returns a ThemeCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ThemeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.equals(LIGHT_THEME_STRING)) {
            return new ThemeCommand(Theme.LIGHT);
        } else if (trimmedArgs.equals(DARK_THEME_STRING)) {
            return new ThemeCommand(Theme.DARK);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
        }
    }
}
