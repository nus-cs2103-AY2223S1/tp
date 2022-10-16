package seedu.address.logic.parser.profile;

import static seedu.address.commons.core.Messages.MESSAGE_FLAG_NOT_SPECIFIED;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.profile.AddProfileCommand;
import seedu.address.logic.commands.profile.DeleteProfileCommand;
import seedu.address.logic.commands.profile.EditProfileCommand;
import seedu.address.logic.commands.profile.FindProfileCommand;
import seedu.address.logic.commands.profile.ProfileCommand;
import seedu.address.logic.commands.profile.ViewProfilesCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ProfileCommand object
 */
public class ProfileCommandParser implements Parser<ProfileCommand> {
    private static final Pattern PROFILE_COMMAND_FORMAT = Pattern.compile(
            "\\s+(-(?<profileOption>\\S*))(\\s+(?!-)\\S+)*");

    /**
     * Parses the given {@code String} of arguments in the context of the ProfileCommand
     * and returns an ProfileCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ProfileCommand parse(String args) throws ParseException {

        if (!args.matches("\\s+-.*")) {
            Matcher matcher = Pattern.compile("(\\s+-\\S*)").matcher(args);
            long numFlags = matcher.results().count();
            if (numFlags == 0) {
                throw new ParseException(String.format(
                        MESSAGE_FLAG_NOT_SPECIFIED, ProfileCommand.PROFILE_FORMAT + ProfileCommand.VALID_FLAGS));
            } else if (numFlags == 1) {
                throw new ParseException(ProfileCommand.OPTION_WRONG_ORDER);
            } else {
                throw new ParseException(ProfileCommand.OPTION_WRONG_ORDER_NO_MULTIPLE);
            }
        }

        final Matcher profileCommandMatcher = PROFILE_COMMAND_FORMAT.matcher(args);

        if (!profileCommandMatcher.matches()) {
            throw new ParseException(ProfileCommand.OPTION_NO_MULTIPLE);
        }

        String profileOption = profileCommandMatcher.group("profileOption");

        switch (profileOption) {
        case AddProfileCommand.COMMAND_OPTION:
            return new AddProfileCommandParser().parse(args);
        case DeleteProfileCommand.COMMAND_OPTION:
            return new DeleteProfileCommandParser().parse(args);
        case EditProfileCommand.COMMAND_OPTION:
            return new EditProfileCommandParser().parse(args);
        case FindProfileCommand.COMMAND_OPTION:
            return new FindProfileCommandParser().parse(args);
        case ViewProfilesCommand.COMMAND_OPTION:
            return new ViewProfilesCommandParser().parse(args);
        default:
            throw new ParseException(ProfileCommand.OPTION_UNKNOWN + ProfileCommand.VALID_FLAGS);
        }
    }

    /**
     *
     * @return
     */
    public static Map<String, String> getProfileCommands() {
        return new HashMap<>() {{
            put(AddProfileCommand.COMMAND_OPTION, AddProfileCommand.MESSAGE_USAGE);
            put(DeleteProfileCommand.COMMAND_OPTION, DeleteProfileCommand.MESSAGE_USAGE);
            put(EditProfileCommand.COMMAND_OPTION, EditProfileCommand.MESSAGE_USAGE);
            put(FindProfileCommand.COMMAND_OPTION, FindProfileCommand.MESSAGE_USAGE);
            put(ViewProfilesCommand.COMMAND_OPTION, "List Commands");
        }};
    }

}
