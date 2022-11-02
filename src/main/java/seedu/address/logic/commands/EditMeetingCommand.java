package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_END_TIME_BEFORE_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;


/**
 * Edits the details of an existing meeting in MyInsuRec.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "editMeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by their index number. \n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: " + PREFIX_INDEX + "INDEX "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_START_TIME + "START TIME] "
            + "[" + PREFIX_END_TIME + "END TIME] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INDEX + "1 "
            + PREFIX_DATE + "23122022 ";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in MyInsuRec.";

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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);

        if (model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        // update meeting list
        model.setMeeting(meetingToEdit, editedMeeting);

        // update meeting in client
        Client client = meetingToEdit.getClient();
        client.removeMeeting(meetingToEdit);
        client.addMeeting(editedMeeting);

        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting), CommandSpecific.MEETING);
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code EditMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor)
            throws CommandException {
        assert meetingToEdit != null;

        MeetingDate updatedDate = editMeetingDescriptor.getDate().orElse(meetingToEdit.getMeetingDate());
        Description updatedDescription = editMeetingDescriptor.getDescription().orElse(meetingToEdit.getDescription());
        MeetingTime updatedEndTime = editMeetingDescriptor.getEndTime().orElse(meetingToEdit.getMeetingEndTime());
        MeetingTime updatedStartTime = editMeetingDescriptor.getStartTime().orElse(meetingToEdit.getMeetingStartTime());
        if (MeetingDate.isBeforeToday(updatedDate)) {
            throw new CommandException(MeetingDate.MESSAGE_INVALID_DATE);
        }
        if (updatedEndTime.isBefore(updatedStartTime)) {
            throw new CommandException(MESSAGE_END_TIME_BEFORE_START_TIME);
        }
        Meeting meeting = new Meeting(meetingToEdit.getClient(), updatedDescription, updatedDate,
                updatedStartTime, updatedEndTime);
        return meeting;
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
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private MeetingDate date;
        private MeetingTime startTime;
        private MeetingTime endTime;
        private Description description;

        public EditMeetingDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            date = toCopy.date;
            startTime = toCopy.startTime;
            endTime = toCopy.endTime;
            description = toCopy.description;
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, startTime, endTime, description);
        }

        public void setDate(MeetingDate date) {
            this.date = date;
        }

        public Optional<MeetingDate> getDate() {
            return Optional.ofNullable(date);
        }

        public void setStartTime(MeetingTime startTime) {
            this.startTime = startTime;
        }

        public Optional<MeetingTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(MeetingTime endTime) {
            this.endTime = endTime;
        }

        public Optional<MeetingTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
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

            return getDate().equals(e.getDate())
                    && getEndTime().equals(e.getEndTime())
                    && getStartTime().equals(e.getStartTime())
                    && getDescription().equals(e.getDescription());
        }
    }
}
