package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listing.ListingId;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Name;


/**
 * Edits the details of an existing meeting in the address book.
 */
public class EditMeetingCommand extends Command {
    public static final String COMMAND_WORD = "editM";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_LISTING_ID + "ADDRESS LISTING ID] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DATETIME + "Date and Time]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATETIME + "2022-09-20 12:59";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book";

    private final Index index;
    private final EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor;


    /**
     * Constructor
     * @param index index of Meeting to edit
     * @param editMeetingDescriptor descriptor
     */
    public EditMeetingCommand(Index index, EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(editMeetingDescriptor);
        this.index = index;
        this.editMeetingDescriptor = new EditMeetingCommand.EditMeetingDescriptor(editMeetingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastshownlist = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastshownlist.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting toEdit = lastshownlist.get(index.getZeroBased());
        Meeting edited = createEditedMeeting(toEdit, editMeetingDescriptor);

        if (!toEdit.isSameMeeting(edited) && model.hasMeeting(edited)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.setMeeting(toEdit, edited);
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, edited));
    }

    private static Meeting createEditedMeeting(Meeting toedit,
                                               EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor) {
        assert toedit != null;

        Name updatedName = editMeetingDescriptor.getName().orElse(toedit.getClient());
        ListingId updatedListingId = editMeetingDescriptor.getListing().orElse(toedit.getListing());
        LocalDateTime updatedDateTime = editMeetingDescriptor.getDateTime().orElse(toedit.getdateTime());

        return new Meeting(updatedName, updatedListingId, updatedDateTime);
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
        private Name name;
        private ListingId listing;
        private LocalDateTime dateTime;

        public EditMeetingDescriptor() {}

        /**
         * Constructor.
         */
        public EditMeetingDescriptor(EditMeetingCommand.EditMeetingDescriptor toCopy) {
            setName(toCopy.name);
            setListing(toCopy.listing);
            setDateTime(toCopy.dateTime);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, listing, dateTime);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setListing(ListingId listing) {
            this.listing = listing;
        }

        public Optional<ListingId> getListing() {
            return Optional.ofNullable(listing);
        }

        public void setDateTime(LocalDateTime newDate) {
            this.dateTime = newDate;
        }

        public Optional<LocalDateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            // instanceof handles nulls
            if (!(other instanceof EditMeetingCommand.EditMeetingDescriptor)) {
                return false;
            }

            // state check
            EditMeetingCommand.EditMeetingDescriptor e = (EditMeetingCommand.EditMeetingDescriptor) other;

            return getName().equals(e.getName())
                    && getListing().equals(e.getListing())
                    && getDateTime().equals(e.getDateTime());
        }
    }
}
