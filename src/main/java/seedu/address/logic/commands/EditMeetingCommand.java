package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "editmeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DESCRIPTION + "NAME] "
            + "[" + PREFIX_DATE + "PHONE] "
            + "[" + PREFIX_LOCATION + "EMAIL] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "91234567 "
            + PREFIX_LOCATION + "johndoe@example.com";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book.";

    private final Index index;
    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * @param index of the meeting in the filtered meeting list to edit
     * @param editMeetingDescriptor details to edit the meeting with
     */
    public EditMeetingCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(editMeetingDescriptor);

        this.index = index;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editMeetingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);

        if (!meetingToEdit.isSameMeeting(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting));
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        String updatedDescription = editMeetingDescriptor.getDescription().orElse(meetingToEdit.getDescription());
        String updatedDate = editMeetingDescriptor.getDate().orElse(meetingToEdit.getDateAndTime());
        String updatedLocation = editMeetingDescriptor.getLocation().orElse(meetingToEdit.getLocation());
        ArrayList<Person> persons = editMeetingDescriptor.getPeople().orElse(meetingToEdit.getArrayListPersonToMeet());

        return new Meeting(persons, updatedDescription, updatedDate, updatedLocation);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMeetingCommand)) {
            return false;
        }

        // state check
        EditMeetingCommand e = (EditMeetingCommand) other;
        return index.equals(e.index)
                && editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditMeetingDescriptor {
        private String description;
        private String date;
        private String location;
        private ArrayList<Person> people;

        public EditMeetingDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setDescription(toCopy.description);
            setDate(toCopy.date);
            setLocation(toCopy.location);
            setPeople(toCopy.people);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, date, location, people);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Optional<String> getDate() {
            return Optional.ofNullable(date);
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Optional<String> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setPeople(ArrayList<Person> people) {
            this.people = people;
        }

        public Optional<ArrayList<Person>> getPeople() {
            return Optional.ofNullable(people);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMeetingDescriptor)) {
                return false;
            }

            // state check
            EditMeetingDescriptor e = (EditMeetingDescriptor) other;

            return getDescription().equals(e.getDescription())
                    && getDate().equals(e.getDate())
                    && getLocation().equals(e.getLocation())
                    && getPeople().equals(e.getPeople());
        }
    }
}
