package seedu.guest.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_DATE_RANGE;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_IS_ROOM_CLEAN;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_NUMBER_OF_GUESTS;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.guest.model.Model.PREDICATE_SHOW_ALL_GUESTS;

import java.util.List;
import java.util.Optional;

import seedu.guest.commons.core.Messages;
import seedu.guest.commons.core.index.Index;
import seedu.guest.commons.util.CollectionUtil;
import seedu.guest.logic.commands.exceptions.CommandException;
import seedu.guest.model.Model;
import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.IsRoomClean;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;

/**
 * Edits the details of an existing guest in the guest book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the guest identified "
            + "by the index number used in the displayed guest book. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_DATE_RANGE + "DATE_RANGE] "
            + "[" + PREFIX_NUMBER_OF_GUESTS + "NUMBER_OF_GUESTS] "
            + "[" + PREFIX_IS_ROOM_CLEAN + "IS_ROOM_CLEAN] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_GUEST_SUCCESS = "Edited Guest: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_GUEST = "This guest already exists in the guest book.";

    private final Index index;
    private final EditGuestDescriptor editGuestDescriptor;

    /**
     * @param index of the guest in the filtered guest book to edit
     * @param editGuestDescriptor details to edit the guest with
     */
    public EditCommand(Index index, EditGuestDescriptor editGuestDescriptor) {
        requireNonNull(index);
        requireNonNull(editGuestDescriptor);

        this.index = index;
        this.editGuestDescriptor = new EditGuestDescriptor(editGuestDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Guest> lastShownList = model.getFilteredGuestList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
        }

        Guest guestToEdit = lastShownList.get(index.getZeroBased());
        Guest editedGuest = createEditedGuest(guestToEdit, editGuestDescriptor);

        if (!guestToEdit.isSameGuest(editedGuest) && model.hasGuest(editedGuest)) {
            throw new CommandException(MESSAGE_DUPLICATE_GUEST);
        }

        model.setGuest(guestToEdit, editedGuest);
        model.updateFilteredGuestList(PREDICATE_SHOW_ALL_GUESTS);
        return new CommandResult(String.format(MESSAGE_EDIT_GUEST_SUCCESS, editedGuest));
    }

    /**
     * Creates and returns a {@code Guest} with the details of {@code guestToEdit}
     * edited with {@code editGuestDescriptor}.
     */
    private static Guest createEditedGuest(Guest guestToEdit, EditGuestDescriptor editGuestDescriptor) {
        assert guestToEdit != null;

        Name updatedName = editGuestDescriptor.getName().orElse(guestToEdit.getName());
        Phone updatedPhone = editGuestDescriptor.getPhone().orElse(guestToEdit.getPhone());
        Email updatedEmail = editGuestDescriptor.getEmail().orElse(guestToEdit.getEmail());
        DateRange updatedDateRange = editGuestDescriptor.getDateRange().orElse(guestToEdit.getDateRange());
        NumberOfGuests updatedNumberOfGuests = editGuestDescriptor.getNumberOfGuests()
                .orElse(guestToEdit.getNumberOfGuests());
        IsRoomClean updatedIsRoomClean = editGuestDescriptor.getIsRoomClean().orElse(guestToEdit.getIsRoomClean());
        return new Guest(updatedName, updatedPhone, updatedEmail, updatedDateRange,
                updatedNumberOfGuests, updatedIsRoomClean);
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
                && editGuestDescriptor.equals(e.editGuestDescriptor);
    }

    /**
     * Stores the details to edit the guest with. Each non-empty field value will replace the
     * corresponding field value of the guest.
     */
    public static class EditGuestDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private DateRange dateRange;
        private NumberOfGuests numberOfGuests;
        private IsRoomClean isRoomClean;

        public EditGuestDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditGuestDescriptor(EditGuestDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setDateRange(toCopy.dateRange);
            setNumberOfGuests(toCopy.numberOfGuests);
            setIsRoomClean(toCopy.isRoomClean);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, dateRange, numberOfGuests, isRoomClean);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setDateRange(DateRange dateRange) {
            this.dateRange = dateRange;
        }

        public Optional<DateRange> getDateRange() {
            return Optional.ofNullable(dateRange);
        }

        public void setNumberOfGuests(NumberOfGuests numberOfGuests) {
            this.numberOfGuests = numberOfGuests;
        }

        public Optional<NumberOfGuests> getNumberOfGuests() {
            return Optional.ofNullable(numberOfGuests);
        }

        public void setIsRoomClean(IsRoomClean isRoomClean) {
            this.isRoomClean = isRoomClean;
        }

        public Optional<IsRoomClean> getIsRoomClean() {
            return Optional.ofNullable(isRoomClean);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditGuestDescriptor)) {
                return false;
            }

            // state check
            EditGuestDescriptor e = (EditGuestDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getDateRange().equals(e.getDateRange())
                    && getNumberOfGuests().equals(e.getNumberOfGuests())
                    && getIsRoomClean().equals(e.getIsRoomClean());
        }
    }
}
