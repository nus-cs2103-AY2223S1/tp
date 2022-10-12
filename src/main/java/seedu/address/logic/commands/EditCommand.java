package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDITIONAL_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY_OWED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY_PAID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AdditionalNotes;
import seedu.address.model.person.Address;
import seedu.address.model.person.Class;
import seedu.address.model.person.Email;
import seedu.address.model.person.Money;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.storage.ClassStorage;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_CLASS_DATE_TIME + "CLASS_DATE_TIME] "
            + "[" + PREFIX_MONEY_OWED + "MONEY_OWED] "
            + "[" + PREFIX_MONEY_PAID + "MONEY_PAID] "
            + "[" + PREFIX_ADDITIONAL_NOTES + "NOTES] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_CLASS_CONFLICT = "There is a conflict between the class timings.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        boolean isEditingClass = !editPersonDescriptor.getAClass().get().classDateTime.equals("");
        ClassStorage.saveClass(editedPerson, index.getOneBased());
        if (isEditingClass) {
            ClassStorage.removeExistingClass(personToEdit);
        }
        if (!isEditingClass && !personToEdit.getAClass().classDateTime.equals("")) {
            editedPerson.setClass(personToEdit.getAClass());
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Class updatedClassDateTime = editPersonDescriptor.getAClass().orElse(personToEdit.getAClass());
        Money updatedMoneyOwed = editPersonDescriptor.getMoneyOwed().orElse(personToEdit.getMoneyOwed());
        Money updatedMoneyPaid = editPersonDescriptor.getMoneyPaid().orElse(personToEdit.getMoneyPaid());
        AdditionalNotes updatedNotes = editPersonDescriptor.getAdditionalNotes()
                .orElse(personToEdit.getAdditionalNotes());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedClassDateTime,
                updatedMoneyOwed, updatedMoneyPaid, updatedNotes);
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
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Class aClass;
        private Money moneyOwed;
        private Money moneyPaid;
        private AdditionalNotes additionalNotes;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setClass(toCopy.aClass);
            setMoneyOwed(toCopy.moneyOwed);
            setMoneyPaid(toCopy.moneyPaid);
            setAdditionalNotes(toCopy.additionalNotes);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, aClass, moneyOwed, moneyPaid,
                    additionalNotes);
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

        public void setMoneyOwed(Money moneyOwed) {
            this.moneyOwed = moneyOwed;
        }

        public Optional<Money> getMoneyOwed() {
            return Optional.ofNullable(moneyOwed);
        }

        public void setMoneyPaid(Money moneyPaid) {
            this.moneyPaid = moneyPaid;
        }

        public Optional<Money> getMoneyPaid() {
            return Optional.ofNullable(moneyPaid);
        }

        public void setAdditionalNotes(AdditionalNotes additionalNotes) {
            this.additionalNotes = additionalNotes;
        }

        public Optional<AdditionalNotes> getAdditionalNotes() {
            return Optional.ofNullable(additionalNotes);
        }

        public void setClass(Class aClass) {
            if (aClass == null) {
                this.aClass = new Class();
            } else {
                this.aClass = aClass;
            }
        }

        public Optional<Class> getAClass() {
            return Optional.ofNullable(aClass);
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
                    && getAClass().toString().equals(e.getAClass().toString())
                    && getMoneyOwed().equals(e.getMoneyOwed())
                    && getMoneyPaid().equals(e.getMoneyPaid())
                    && getAdditionalNotes().equals(e.getAdditionalNotes());
        }
    }
}
