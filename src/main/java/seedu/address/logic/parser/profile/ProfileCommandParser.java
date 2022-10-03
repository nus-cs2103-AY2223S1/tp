package seedu.address.logic.parser.profile;

import static seedu.address.commons.core.Messages.MESSAGE_FLAG_NOT_SPECIFIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.profile.AddProfileCommand;
import seedu.address.logic.commands.profile.ProfileCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ProfileCommand object
 */
public class ProfileCommandParser implements Parser<ProfileCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ProfileCommand
     * and returns an ProfileCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ProfileCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_OPTION, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL);

        String option = argMultimap.getValue(PREFIX_OPTION)
                .orElseThrow(() -> new ParseException(MESSAGE_FLAG_NOT_SPECIFIED));

        switch (option) {
        case AddProfileCommand.COMMAND_OPTION:
            return new AddProfileCommandParser().parse(args);
        default:
            throw new ParseException(ProfileCommand.OPTION_UNKNOWN);
        }
    }

}
