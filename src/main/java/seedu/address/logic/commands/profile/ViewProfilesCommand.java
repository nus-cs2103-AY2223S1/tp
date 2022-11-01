package seedu.address.logic.commands.profile;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROFILES;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Shows a list of all the profiles in the NUScheduler.
 */
public class ViewProfilesCommand extends ProfileCommand {

    public static final String COMMAND_OPTION = "v";

    public static final String MESSAGE_SUCCESS = "Listed all profiles.";
    public static final String MESSAGE_FAILURE = "Please remove extra inputs after the option flag.";
    public static final String MESSAGE_HELP = "Lists all profiles in NUScheduler.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION;
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredProfileList(PREDICATE_SHOW_ALL_PROFILES);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ViewProfilesCommand;
    }
}
