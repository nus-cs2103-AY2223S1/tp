package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROFILE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.util.Set;

/**
 * Deletes attendees by their displayed index in the list from an existing event in NUScheduler.
 */
public class DeleteProfilesFromEventCommand extends EventCommand {

    public static final String COMMAND_OPTION = "dp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION
            + ": Deletes profiles in the displayed list of attendees of an event identified by the index "
            + "in the displayed event list.\n"
            + "Parameters: EVENT_INDEX (must be a positive integer) "
            + PREFIX_PROFILE + "PROFILE_INDEX...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " 1 "
            + PREFIX_PROFILE + "1 "
            + PREFIX_PROFILE + "2";

    public static final String MESSAGE_EDIT_ATTENDEES_SUCCESS = "Edited list of attendees of the event:\n%1$s";
    public static final String MESSAGE_HELP = "Deletes attendees from an existing event in NUScheduler.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " EVENT_INDEX "
            + PREFIX_PROFILE + "PROFILE_INDEX...\n";
    public static final String MESSAGE_NOT_ADDED = "At least one profile to delete must be specified.";
    public static final String MESSAGE_INVALID_PROFILE_INDEX = "One or more profile indexes specified are invalid.";

    private final Index eventIndex;
    private final Set<Index> profileIndexes;

    public DeleteProfilesFromEventCommand(Index eventIndex, Set<Index> profileIndexes) {
        requireNonNull(eventIndex);
        requireNonNull(profileIndexes);

        this.eventIndex = eventIndex;
        this.profileIndexes = profileIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
