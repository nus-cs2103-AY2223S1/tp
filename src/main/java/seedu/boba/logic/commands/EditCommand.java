package seedu.boba.logic.commands;

import javafx.collections.transformation.FilteredList;
import seedu.boba.commons.core.Messages;
import seedu.boba.commons.core.index.Index;
import seedu.boba.commons.util.CollectionUtil;
import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.customer.BirthdayMonth;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Name;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;
import seedu.boba.model.customer.exceptions.PersonNotFoundException;
import seedu.boba.model.tag.Tag;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_BIRTHDAY_MONTH;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_REWARD;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.boba.model.BobaBotModel.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.boba.model.customer.Customer.BIRTHDAY_TAG;

/**
 * Edits the details of an existing Customer in bobaBot.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the customer identified "
            + "by the phone number/ email address used to register for membership. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: p/PHONE_NUMBER or e/EMAIL \n"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_BIRTHDAY_MONTH + "BIRTHDAY_MONTH] "
            + "[" + PREFIX_REWARD + "REWARD] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " p/98349032  or  " + COMMAND_WORD + " e/example@gmail.com"
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Customer: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CUSTOMER = "This customer already exists in bobaBot";

    private final EditPersonDescriptor editPersonDescriptor;

    private Phone phoneIdentifier = null;
    private Email emailIdentifier = null;
    private Index index;

    /**
     * @param phoneIdentifier      current phone number of the customer
     * @param editPersonDescriptor details to edit the customer with
     */
    public EditCommand(Phone phoneIdentifier, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(phoneIdentifier);
        requireNonNull(editPersonDescriptor);

        this.phoneIdentifier = phoneIdentifier;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    /**
     * @param emailIdentifier      current email address of the customer
     * @param editPersonDescriptor details to edit the customer with
     */
    public EditCommand(Email emailIdentifier, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(emailIdentifier);
        requireNonNull(editPersonDescriptor);

        this.emailIdentifier = emailIdentifier;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(BobaBotModel bobaBotModel) throws CommandException, ParseException {
        requireNonNull(bobaBotModel);
        bobaBotModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        try {
            this.index = !isNull(this.phoneIdentifier)
                    ? Index.fromZeroBased(bobaBotModel.findNum(phoneIdentifier))
                    : Index.fromZeroBased(bobaBotModel.findEmail(emailIdentifier));
        } catch (PersonNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_INFORMATION);
        }

        List<Customer> lastShownList = bobaBotModel.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_INFORMATION);
        }
        Customer customerToEdit = lastShownList.get(index.getZeroBased());
        Customer editedCustomer = createEditedPerson(customerToEdit, editPersonDescriptor);
        LocalDate currentDate = LocalDate.now();
        String currentMonth = String.valueOf(currentDate.getMonth().getValue());
        if (editedCustomer.getTags().contains(BIRTHDAY_TAG)
                && !editedCustomer.getBirthdayMonth().value.equals(currentMonth)) {
            editedCustomer.removeBirthdayTag();
        } else if (!editedCustomer.getTags().contains(BIRTHDAY_TAG)
                && editedCustomer.getBirthdayMonth().value.equals(currentMonth)) {
            editedCustomer.addBirthdayTag();
        }

        Predicate<Customer> filterPersonToEdit = p -> !p.equals(customerToEdit);
        FilteredList<Customer> filteredListWithoutTarget = bobaBotModel.getBobaBot().getPersonList()
                .filtered(filterPersonToEdit);

        if (filteredListWithoutTarget.contains(editedCustomer)) {
            throw new CommandException(MESSAGE_DUPLICATE_CUSTOMER);
        }

        bobaBotModel.setPerson(customerToEdit, editedCustomer);
        bobaBotModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedCustomer));
    }

    /**
     * Creates and returns a {@code Customer} with the details of {@code customerToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Customer createEditedPerson(Customer customerToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert customerToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(customerToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(customerToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(customerToEdit.getEmail());
        BirthdayMonth updatedBirthdayMonth = editPersonDescriptor.getBirthdayMonth()
                .orElse(customerToEdit.getBirthdayMonth());
        Reward updatedReward = editPersonDescriptor.getReward().orElse(customerToEdit.getReward());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(customerToEdit.getTags());

        return new Customer(updatedName, updatedPhone, updatedEmail, updatedBirthdayMonth, updatedReward, updatedTags);
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
        return (isNull(emailIdentifier) && phoneIdentifier.equals(e.phoneIdentifier)
                || isNull(phoneIdentifier) && emailIdentifier.equals(e.emailIdentifier))
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the customer with. Each non-empty field value will replace the
     * corresponding field value of the customer.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private BirthdayMonth birthdayMonth;
        private Reward reward;
        private Set<Tag> tags;

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
            setBirthdayMonth(toCopy.birthdayMonth);
            setReward(toCopy.reward);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, reward, tags);
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

        public void setBirthdayMonth(BirthdayMonth birthdayMonth) {
            this.birthdayMonth = birthdayMonth;
        }

        public Optional<BirthdayMonth> getBirthdayMonth() {
            return Optional.ofNullable(birthdayMonth);
        }

        public void setReward(Reward reward) {
            this.reward = reward;
        }

        public Optional<Reward> getReward() {
            return Optional.ofNullable(reward);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
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
                    && getBirthdayMonth().equals(e.getBirthdayMonth())
                    && getReward().equals(e.getReward())
                    && getTags().equals(e.getTags());
        }
    }
}
