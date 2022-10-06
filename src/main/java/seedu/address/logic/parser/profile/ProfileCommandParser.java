package seedu.address.logic.parser.profile;

import static seedu.address.commons.core.Messages.MESSAGE_FLAG_NOT_SPECIFIED;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.profile.AddProfileCommand;
import seedu.address.logic.commands.profile.EditProfileCommand;
import seedu.address.logic.commands.profile.ProfileCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ProfileCommand object
 */
public class ProfileCommandParser implements Parser<ProfileCommand> {
    private static final Pattern PROFILE_COMMAND_FORMAT = Pattern.compile(
            "\\s+(-(?<profileOption>\\S*)(\\s+(?!-)\\S+)?)(\\s+(?!-)\\S*.)*");

    /**
     * Parses the given {@code String} of arguments in the context of the ProfileCommand
     * and returns an ProfileCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ProfileCommand parse(String args) throws ParseException {
        if (!args.matches("\\s+-.*")) {
            throw new ParseException(String.format(MESSAGE_FLAG_NOT_SPECIFIED, ProfileCommand.PROFILE_FORMAT));
        }

        final Matcher profileCommandMatcher = PROFILE_COMMAND_FORMAT.matcher(args);

        String profileOption = "";
        if (profileCommandMatcher.matches()) {
            profileOption = profileCommandMatcher.group("profileOption");
        } else {
            throw new ParseException(ProfileCommand.OPTION_NO_MULTIPLE);
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
