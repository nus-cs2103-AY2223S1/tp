package seedu.address.logic.parser.profile;

import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import seedu.address.logic.commands.profile.ViewProfilesCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewProfilesCommand object
 */
public class ViewProfilesCommandParser implements Parser<ViewProfilesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewProfilesCommand
     * and returns a ViewProfilesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewProfilesCommand parse(String args) throws ParseException {
        assert args != null;

        String trimmedArgs = args.trim();
        String viewProfilesOption = PREFIX_OPTION + ViewProfilesCommand.COMMAND_OPTION;

        if (!trimmedArgs.equals(viewProfilesOption)) {
            throw new ParseException(ViewProfilesCommand.MESSAGE_FAILURE);
        }

        return new ViewProfilesCommand();
    }
}
