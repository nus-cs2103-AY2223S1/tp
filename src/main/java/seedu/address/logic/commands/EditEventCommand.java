package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PURPOSE;

public class EditEventCommand extends Command {

    public static final String COMMAND_WORD = "editEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the event identified "
            + "by the index number used in the displayed event list. "
            + "Existing values will be overwritten by the input values. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_EVENT_TITLE + "EVENT_TITLE]"
            + "[" + PREFIX_DATE + "DATE]"
            + "[" + PREFIX_TIME + "TIME]"
            + "[" + PREFIX_PURPOSE + "PURPOSE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EVENT_TITLE + "Banana Sale "
            + PREFIX_PURPOSE + "20% discount on all bananas store-wide";

    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Event: %1$s";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book.";

    private final Index index;

    private final EditEventDescriptor editEventDescriptor;

    public EditEventCommand(Index index, EditEventDescriptor editEventDescriptor) {
        requireNonNull(index);
        requireNonNull(editEventDescriptor);

        this.index = index;
        this.editEventDescriptor = new EditEventDescriptor(editEventDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> currentEventList = model.getFilteredEventList();

        Integer zeroBased = index.getZeroBased();

        if (zeroBased >= currentEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToEdit = currentEventList.get(zeroBased);
        Event editedEvent = createEditedEvent(eventToEdit, editEventDescriptor);

        if (!eventToEdit.equals(editedEvent) && model.hasEvent(editedEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.setEvent(eventToEdit, editedEvent);
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedEvent));
    }

    private static Event createEditedEvent(Event eventToEdit, EditEventDescriptor editEventDescriptor) {
        assert eventToEdit != null;

        String updatedEventTitle = editEventDescriptor.getEventTitle().orElse(eventToEdit.getEventTitle());
        String updatedDate = editEventDescriptor.getDate().orElse(eventToEdit.getDate());
        String updatedTime = editEventDescriptor.getDate().orElse(eventToEdit.getTime());
        String updatedPurpose = editEventDescriptor.getPurpose().orElse(eventToEdit.getPurpose());
        return new Event(updatedEventTitle, updatedDate, updatedTime, updatedPurpose);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditEventCommand)) {
            return false;
        }

        EditEventCommand otherCommand = (EditEventCommand) other;
        return index.equals(otherCommand.index)
                && editEventDescriptor.equals(((EditEventCommand) other).editEventDescriptor);
    }

    public static class EditEventDescriptor {
        private String eventTitle;
        private String date;
        private String time;
        private String purpose;

        public EditEventDescriptor() {}

        public EditEventDescriptor(EditEventDescriptor copy) {
            setEventTitle(copy.eventTitle);
            setDate(copy.date);
            setTime(copy.time);
            setPurpose(copy.purpose);


        }
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(eventTitle, date, time, purpose);
        }

        public void setEventTitle(String eventTitle) {
            this.eventTitle = eventTitle;
        }

        public Optional<String> getEventTitle() {
            return Optional.ofNullable(eventTitle);
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Optional<String> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Optional<String> getTime() {
            return Optional.ofNullable(time);
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public Optional<String> getPurpose() {
            return Optional.ofNullable(purpose);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditEventDescriptor)) {
                return false;
            }

            EditEventDescriptor otherEventDescriptor = (EditEventDescriptor) other;

            return getEventTitle().equals(otherEventDescriptor.getEventTitle())
                    && getDate().equals(otherEventDescriptor.getDate())
                    && getTime().equals(otherEventDescriptor.getTime())
                    && getPurpose().equals(otherEventDescriptor.getPurpose());
        }
    }

}
