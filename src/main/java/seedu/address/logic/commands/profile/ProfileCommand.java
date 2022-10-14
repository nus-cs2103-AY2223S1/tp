package seedu.address.logic.commands.profile;

import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import seedu.address.logic.commands.Command;

/**
 * Represents a Profile command with hidden internal logic and the ability to be executed.
 */
public abstract class ProfileCommand extends Command {

    public static final String COMMAND_WORD = "profile";

    public static final String PROFILE_FORMAT = "Format: " + COMMAND_WORD + " -option\n";

    public static final String VALID_FLAGS = "Please use one of the following valid flags: "
            + PREFIX_OPTION + AddProfileCommand.COMMAND_OPTION + ", "
            + PREFIX_OPTION + DeleteProfileCommand.COMMAND_OPTION + ", "
            + PREFIX_OPTION + EditProfileCommand.COMMAND_OPTION + ", "
            + PREFIX_OPTION + FindProfileCommand.COMMAND_OPTION + ", "
            + PREFIX_OPTION + ViewProfilesCommand.COMMAND_OPTION + ".";

    public static final String OPTION_UNKNOWN = "That is not a valid option flag.\n";

    public static final String OPTION_NO_MULTIPLE = "Multiple option flags are not allowed.\n" + PROFILE_FORMAT;

    public static final String OPTION_WRONG_ORDER = "Option flag should be in front.\n" + PROFILE_FORMAT;

    public static final String OPTION_WRONG_ORDER_NO_MULTIPLE =
            "Only one option flag should be specified in front.\n" + PROFILE_FORMAT;

    public static final String MESSAGE_DUPLICATE_NAME = "This name already exists in the address book";
    public static final String MESSAGE_DUPLICATE_EMAIL = "This email already exists in the address book";
}
