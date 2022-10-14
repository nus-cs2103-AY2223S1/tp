package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.travelr.model.Model.PREDICATE_SHOW_ALL_TRIPS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.travelr.commons.core.Messages;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.commons.util.CollectionUtil;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;

/**
 * Edits the details of a Trip in Travelr.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the Trip identified "
            + "by the index number used in the displayed trip list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DESC + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESC + "Consectetur adipiscing elit.";

    public static final String MESSAGE_EDIT_TRIP_SUCCESS = "Edited Trip: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TRIP = "This trip already exists in the address book.";

    private final Index index;
    private final EditTripDescriptor editTripDescriptor;

    /**
     * @param index              of the person in the filtered trip list to edit
     * @param editTripDescriptor details to edit the trip with
     */
    public EditCommand(Index index, EditTripDescriptor editTripDescriptor) {
        requireNonNull(index);
        requireNonNull(editTripDescriptor);

        this.index = index;
        this.editTripDescriptor = new EditTripDescriptor(editTripDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Trip> lastShownList = model.getFilteredTripList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
        }

        Trip tripToEdit = lastShownList.get(index.getZeroBased());
        Trip editedTrip = createEditedTrip(tripToEdit, editTripDescriptor);

        if (!tripToEdit.isSameTrip(editedTrip) && model.hasTrip(editedTrip)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRIP);
        }

        model.setTrip(tripToEdit, editedTrip);
        model.updateFilteredTripList(PREDICATE_SHOW_ALL_TRIPS);
        return new CommandResult(String.format(MESSAGE_EDIT_TRIP_SUCCESS, editedTrip));
    }

    /**
     * Creates and returns a {@code Trip} with the details of {@code tripToEdit}
     * edited with {@code editTripDescriptor}.
     */
    private static Trip createEditedTrip(Trip tripToEdit, EditTripDescriptor editTripDescriptor) {
        assert tripToEdit != null;

        Title updatedTitle = editTripDescriptor.getName().orElse(tripToEdit.getTitle());
        Description updatedDescription = editTripDescriptor.getDescription().orElse(tripToEdit.getDescription());
        Set<Event> updatedTags = editTripDescriptor.getTags().orElse(tripToEdit.getEvents());

        return new Trip(updatedTitle, updatedDescription, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editTripDescriptor.equals(e.editTripDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditTripDescriptor {
        private Title title;
        private Description description;
        private Set<Event> events;

        public EditTripDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTripDescriptor(EditTripDescriptor toCopy) {
            setName(toCopy.title);
            setDescription(toCopy.description);
            setTags(toCopy.events);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, description, events);
        }

        public void setName(Title title) {
            this.title = title;
        }

        public Optional<Title> getName() {
            return Optional.ofNullable(title);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Event> tags) {
            this.events = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Event>> getTags() {
            return (events != null) ? Optional.of(Collections.unmodifiableSet(events)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTripDescriptor)) {
                return false;
            }

            // state check
            EditTripDescriptor e = (EditTripDescriptor) other;

            return getName().equals(e.getName())
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags());
        }
    }
}
