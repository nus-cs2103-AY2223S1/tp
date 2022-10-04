package seedu.address.logic.commands.profile;

import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import seedu.address.logic.commands.Command;

/**
 * Represents a Profile command with hidden internal logic and the ability to be executed.
 */
public abstract class ProfileCommand extends Command {

    public static final String COMMAND_WORD = "profile";

    public static final String OPTION_UNKNOWN = "That is not a valid option flag.\n"
            + "Please use one of the following valid flags: "
            + PREFIX_OPTION + AddProfileCommand.COMMAND_OPTION + ", "
            + PREFIX_OPTION + EditProfileCommand.COMMAND_OPTION + ".";

}
