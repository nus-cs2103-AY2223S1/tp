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
 * Adds existing profiles as attendees to an existing event in NUScheduler.
 */
public class AddProfilesToEventCommand extends EventCommand {

    public static final String COMMAND_OPTION = "ap";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION
            + ": Adds profiles in the displayed profile list to an event identified by the index "
            + "in the displayed event list.\n"
            + "Parameters: EVENT_INDEX (must be a positive integer less than or equal to 1000) "
            + PREFIX_PROFILE + "PROFILE_INDEX...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " 1 "
            + PREFIX_PROFILE + "1 "
            + PREFIX_PROFILE + "3";

    public static final String MESSAGE_EDIT_ATTENDEES_SUCCESS = "Edited list of attendees of the event:\n%1$s";
    public static final String MESSAGE_HELP = "Adds existing profiles to an existing event as "
            + "attendees in NUScheduler.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " EVENT_INDEX "
            + PREFIX_PROFILE + "PROFILE_INDEX...\n";
    public static final String MESSAGE_ATTENDEES_NOT_ADDED = "At least one profile to add must be specified.";
    public static final String MESSAGE_INVALID_PROFILE_INDEX = "One or more profile indexes specified are invalid.";

    private final Index eventIndex;
    private final Set<Index> profileIndexes;

    /**
     * @param eventIndex of the event in the filtered event list to add profiles to
     * @param profileIndexes list of profile indexes from the filtered profile list to add
     */
    public AddProfilesToEventCommand(Index eventIndex, Set<Index> profileIndexes) {
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

        List<Profile> lastShownProfileList = model.getFilteredProfileList();
        List<Profile> profilesToAdd = new ArrayList<>();

        for (Index profileIndex : profileIndexes) {
            if (profileIndex.getZeroBased() >= lastShownProfileList.size()) {
                throw new CommandException(Messages.MESSAGE_MULTIPLE_INVALID_PROFILE_DISPLAYED_INDEX);
            }
            Profile p = lastShownProfileList.get(profileIndex.getZeroBased());
            profilesToAdd.add(p);
        }

        Event eventCopy = new Event(event.getTitle(), event.getStartDateTime(), event.getEndDateTime(),
                event.getTags(), event.getAttendees());

        model.addEventToAttendees(eventCopy, profilesToAdd);
        model.addEventAttendees(eventCopy, profilesToAdd);
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
        if (!(other instanceof AddProfilesToEventCommand)) {
            return false;
        }

        // state check
        AddProfilesToEventCommand a = (AddProfilesToEventCommand) other;
        return eventIndex.equals(a.eventIndex)
                && profileIndexes.equals(a.profileIndexes);
    }
}
