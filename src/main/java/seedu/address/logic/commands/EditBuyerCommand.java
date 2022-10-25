package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.address.Address;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.Email;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.buyer.Priority;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.pricerange.PriceRange;

/**
 * Edits the details of an existing buyer in the address book.
 */
public class EditBuyerCommand extends Command {

    public static final String COMMAND_WORD = "editbuyer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the buyer identified "
            + "by the index number used in the displayed buyer list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + " NAME] "
            + "[" + PREFIX_PHONE + " PHONE] "
            + "[" + PREFIX_EMAIL + " EMAIL] "
            + "[" + PREFIX_ADDRESS + " ADDRESS] "
            + "[" + PREFIX_PRICE_RANGE + " PRICE RANGE] "
            + "[" + PREFIX_CHARACTERISTICS + " DESIRED CHARACTERISTICS] "
            + "[" + PREFIX_PRIORITY + "high]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Buyer: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This buyer already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index                of the buyer in the filtered buyer list to edit
     * @param editPersonDescriptor details to edit the buyer with
     */
    public EditBuyerCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Buyer> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Buyer buyerToEdit = lastShownList.get(index.getZeroBased());
        Buyer editedBuyer = createEditedPerson(buyerToEdit, editPersonDescriptor);

        if (!buyerToEdit.isSamePerson(editedBuyer) && model.hasPerson(editedBuyer)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(buyerToEdit, editedBuyer);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedBuyer));
    }

    /**
     * Creates and returns a {@code Buyer} with the details of {@code buyerToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Buyer createEditedPerson(Buyer buyerToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert buyerToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(buyerToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(buyerToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(buyerToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(buyerToEdit.getAddress());
        PriceRange updatedPriceRange = editPersonDescriptor
                .getPriceRange()
                .orElse(buyerToEdit.getPriceRange().orElse(null));
        Characteristics updatedCharacteristics = editPersonDescriptor
                .getDesiredCharacteristics()
                .orElse(buyerToEdit.getDesiredCharacteristics().orElse(null));
        Priority updatedPriority = editPersonDescriptor.getPriority().orElse(buyerToEdit.getPriority());

        Buyer newBuyer = new Buyer(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedPriceRange, updatedCharacteristics, updatedPriority);

        return newBuyer;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBuyerCommand)) {
            return false;
        }

        // state check
        EditBuyerCommand e = (EditBuyerCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the buyer with. Each non-empty field value will replace the
     * corresponding field value of the buyer.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private PriceRange priceRange;
        private Characteristics characteristics;
        private Priority priority;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setPriceRange(toCopy.priceRange);
            setDesiredCharacteristics(toCopy.characteristics);
            setPriority(toCopy.priority);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, priceRange, characteristics, priority);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setPriceRange(PriceRange priceRange) {
            this.priceRange = priceRange;
        }

        public Optional<PriceRange> getPriceRange() {
            return Optional.ofNullable(priceRange);
        }

        public void setDesiredCharacteristics(Characteristics characteristics) {
            this.characteristics = characteristics;
        }

        public Optional<Characteristics> getDesiredCharacteristics() {
            return Optional.ofNullable(characteristics);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getPriceRange().equals(e.getPriceRange())
                    && getDesiredCharacteristics().equals(e.getDesiredCharacteristics())
                    && getPriority().equals(e.getPriority());
        }
    }
}
