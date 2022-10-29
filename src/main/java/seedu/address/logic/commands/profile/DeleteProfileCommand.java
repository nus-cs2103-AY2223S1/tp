package seedu.address.logic.commands.profile;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.profile.EventsAttending;
import seedu.address.model.profile.Profile;

/**
 * Deletes a profile identified using it's displayed index from the NUScheduler.
 */
public class DeleteProfileCommand extends ProfileCommand {

    public static final String COMMAND_OPTION = "d";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION
            + ": Deletes the profile identified by the index number used in the displayed profile list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " 1";

    public static final String MESSAGE_MISSING_INDEX = "Index is required!\n%1$s";
    public static final String MESSAGE_DELETE_PROFILE_SUCCESS = "Deleted Profile:\n%1$s";
    public static final String MESSAGE_HELP = "Deletes an existing profile in NUScheduler.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " INDEX";

    private final Index targetIndex;

    public DeleteProfileCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Profile> lastShownList = model.getFilteredProfileList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROFILE_DISPLAYED_INDEX);
        }

        Profile profileToDelete = lastShownList.get(targetIndex.getZeroBased());
        EventsAttending eventsToRefresh = profileToDelete.getEventsToAttend();

        model.deleteProfile(profileToDelete);
        model.refreshEvents(eventsToRefresh);
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_DELETE_PROFILE_SUCCESS, profileToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteProfileCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteProfileCommand) other).targetIndex)); // state check
    }
}
