package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROFILE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.profile.Profile;

/**
 * Deletes attendees by their displayed index in the list from an existing event in NUScheduler.
 */
public class DeleteProfilesFromEventCommand extends EventCommand {

    public static final String COMMAND_OPTION = "dp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION
            + ": Deletes profiles in the displayed list of attendees of an event identified by the index "
            + "in the displayed event list.\n"
            + "Parameters: EVENT_INDEX (must be a positive integer less than or equal to 1000) "
            + PREFIX_PROFILE + "PROFILE_INDEX...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " 1 "
            + PREFIX_PROFILE + "1 "
            + PREFIX_PROFILE + "2";

    public static final String MESSAGE_EDIT_ATTENDEES_SUCCESS = "Edited list of attendees of the event:\n%1$s";
    public static final String MESSAGE_HELP = "Deletes attendees from an existing event in NUScheduler.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " EVENT_INDEX "
            + PREFIX_PROFILE + "PROFILE_INDEX...\n";
    public static final String MESSAGE_ATTENDEES_NOT_DELETED = "At least one profile to delete must be specified.";
    public static final String MESSAGE_INVALID_PROFILE_INDEX = "One or more profile indexes specified are invalid.";

    private final Index eventIndex;
    private final Set<Index> profileIndexes;

    /**
     * @param eventIndex of the event in the filtered event list to delete profiles from
     * @param profileIndexes list of profile indexes from the event's list of attendees to delete
     */
    public DeleteProfilesFromEventCommand(Index eventIndex, Set<Index> profileIndexes) {
        requireNonNull(eventIndex);
        requireNonNull(profileIndexes);

        this.eventIndex = eventIndex;
        this.profileIndexes = profileIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownEventList = model.getFilteredEventList();

        if (eventIndex.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event event = lastShownEventList.get(eventIndex.getZeroBased());

        List<Profile> profilesToDelete = new ArrayList<>();

        for (Index profileIndex : profileIndexes) {
            if (profileIndex.getZeroBased() >= event.numberOfAttendees()) {
                throw new CommandException(Messages.MESSAGE_MULTIPLE_INVALID_PROFILE_DISPLAYED_INDEX);
            }
            Profile p = event.getAttendee(profileIndex.getZeroBased());
            profilesToDelete.add(p);
        }

        Event eventCopy = new Event(event.getTitle(), event.getStartDateTime(), event.getEndDateTime(),
                event.getTags(), event.getAttendees());

        model.removeEventFromAttendees(event, profilesToDelete);
        model.deleteEventAttendees(eventCopy, profilesToDelete);
        model.setEvent(event, eventCopy);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_ATTENDEES_SUCCESS, event));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteProfilesFromEventCommand)) {
            return false;
        }

        // state check
        DeleteProfilesFromEventCommand d = (DeleteProfilesFromEventCommand) other;
        return eventIndex.equals(d.eventIndex)
                && profileIndexes.equals(d.profileIndexes);
    }
}
