package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PURPOSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventTitle;
import seedu.address.model.event.Purpose;
import seedu.address.model.event.StartTime;
import seedu.address.model.event.UidList;

/**
 * Edits the details of an existing event in the application.
 */
public class EditEventCommand extends Command {

    public static final String COMMAND_WORD = "editEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the event identified "
            + "by the index number used in the displayed event list. "
            + "Existing values will be overwritten by the input values. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_EVENT_TITLE + "EVENT_TITLE] "
            + "[" + PREFIX_START_DATE + "DATE] "
            + "[" + PREFIX_START_TIME + "TIME] "
            + "[" + PREFIX_PURPOSE + "PURPOSE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EVENT_TITLE + "Banana Sale "
            + PREFIX_PURPOSE + "20 cent discount on all bananas";

    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Event: %1$s";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book.";

    private final Index index;

    private final EditEventDescriptor editEventDescriptor;

    /**
     * Constructor of the EditEventCommand class.
     * @param index of the event to be edited in the filtered event list.
     * @param editEventDescriptor details to edit the event with.
     */
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

        int zeroBased = index.getZeroBased();

        if (zeroBased >= currentEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToEdit = currentEventList.get(zeroBased);
        Event editedEvent = createEditedEvent(eventToEdit, editEventDescriptor);

        if (!eventToEdit.isSameEvent(editedEvent) && model.hasEvent(editedEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.setEvent(eventToEdit, editedEvent);
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedEvent));
    }

    /**
     * Creates and returns a {@code Event} with the details of {@code eventToEdit} that is edited
     * with details of {@code editEventDescriptor}.
     */
    private static Event createEditedEvent(Event eventToEdit, EditEventDescriptor editEventDescriptor) {
        assert eventToEdit != null;
        EventTitle updatedEventTitle = editEventDescriptor.getEventTitle().orElse(eventToEdit.getEventTitle());
        Date updatedDate = editEventDescriptor.getDate().orElse(eventToEdit.getStartDate());
        StartTime updatedTime = editEventDescriptor.getTime().orElse(eventToEdit.getStartTime());
        Purpose updatedPurpose = editEventDescriptor.getPurpose().orElse(eventToEdit.getPurpose());
        UidList uids = eventToEdit.getUids();
        return new Event(updatedEventTitle, updatedDate, updatedTime, updatedPurpose, uids);
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

    /**
     * Static class that stores the details to edit the event with.
     * Each field that is non-null will replace the corresponding field value of the event.
     */
    public static class EditEventDescriptor {
        private EventTitle eventTitle;
        private Date date;
        private StartTime time;
        private Purpose purpose;

        public EditEventDescriptor() {}

        /**
         * Constructor to create a {@code EditEventDescriptor} object.
         */
        public EditEventDescriptor(EditEventDescriptor copy) {
            setEventTitle(copy.eventTitle);
            setDate(copy.date);
            setTime(copy.time);
            setPurpose(copy.purpose);
        }

        /**
         * Returns true if at least one field is non-null.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(eventTitle, date, time, purpose);
        }

        public void setEventTitle(EventTitle eventTitle) {
            this.eventTitle = eventTitle;
        }

        public Optional<EventTitle> getEventTitle() {
            return Optional.ofNullable(eventTitle);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTime(StartTime time) {
            this.time = time;
        }

        public Optional<StartTime> getTime() {
            return Optional.ofNullable(time);
        }

        public void setPurpose(Purpose purpose) {
            this.purpose = purpose;
        }

        public Optional<Purpose> getPurpose() {
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
