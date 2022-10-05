package seedu.address.logic.parser.profile;

import static seedu.address.commons.core.Messages.MESSAGE_FLAG_NOT_SPECIFIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.profile.AddProfileCommand;
import seedu.address.logic.commands.profile.EditProfileCommand;
import seedu.address.logic.commands.profile.ProfileCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ProfileCommand object
 */
public class ProfileCommandParser implements Parser<ProfileCommand> {
    private static final Pattern PROFILE_COMMAND_FORMAT = Pattern.compile(
            "(?<profileOption>\\S+)(\\s+\\S+(.*))?");

    /**
     * Parses the given {@code String} of arguments in the context of the ProfileCommand
     * and returns an ProfileCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ProfileCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_OPTION, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG);

        String options = argMultimap.getValue(PREFIX_OPTION)
                .orElseThrow(() -> new ParseException(MESSAGE_FLAG_NOT_SPECIFIED));

        final Matcher profileCommandMatcher = PROFILE_COMMAND_FORMAT.matcher(options);

        String profileOption = "";
        if (profileCommandMatcher.matches()) {
            profileOption = profileCommandMatcher.group("profileOption");
        }

        switch (profileOption) {
        case AddProfileCommand.COMMAND_OPTION:
            return new AddProfileCommandParser().parse(args);
        case EditProfileCommand.COMMAND_OPTION:
            return new EditProfileCommandParser().parse(args);
        default:
            throw new ParseException(ProfileCommand.OPTION_UNKNOWN);
        }
    }

}
