package seedu.address.logic.parser;

import static seedu.address.logic.commands.AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.IncomeLevel;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the person with. Each non-empty field value will replace the
 * corresponding field value of the person.
 */
public class EditPersonDescriptor {
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private IncomeLevel income;
    private Set<Tag> tags;
    private Appointment appointment;
    public EditPersonDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditPersonDescriptor(EditPersonDescriptor toCopy) {
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setEmail(toCopy.email);
        setAddress(toCopy.address);
        setIncome(toCopy.income);
        setTags(toCopy.tags);
        setAppointment(toCopy.appointment);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone, email, address, income, tags, appointment);
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

    /**
     * Sets {@code appointments} to this object's {@code appointments}.
     * A defensive copy of {@code appointments} is used internally.
     */
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Optional<Appointment> getAppointment() {
        return Optional.ofNullable(appointment);
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }
    public void setIncome(IncomeLevel income) {
        this.income = income;
    }
    private Optional<IncomeLevel> getIncome() {
        return Optional.ofNullable(income);
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

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        IncomeLevel updatedIncomeLevel = editPersonDescriptor.getIncome().orElse(personToEdit.getIncome());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedIncomeLevel, updatedTags);
    }


    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with appointments added from {@code editPersonDescriptor}.
     */
    public static Person createEditedPersonByAddingAppointment(Person personToEdit,
                         EditPersonDescriptor editPersonDescriptor) throws CommandException {
        assert personToEdit != null;

        Set<Appointment> currentAppointments = personToEdit.getAppointments();
        Appointment newAppointment = editPersonDescriptor.getAppointment().get();
        for (Appointment currentAppointment:currentAppointments) {
            if (currentAppointment.equals(newAppointment)) {
                throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
        }
        currentAppointments.add(newAppointment);

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Set<Tag> tags = personToEdit.getTags();
        IncomeLevel income = personToEdit.getIncome();
        Person newPerson = new Person(name, phone, email, address, income, tags, currentAppointments);
        return newPerson;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * overwritten with appointments from {@code editPersonDescriptor}.
     */
    public static Person createEditedPersonByDeletingAllAppointments(Person personToEdit) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Set<Tag> tags = personToEdit.getTags();
        IncomeLevel income = personToEdit.getIncome();
        Set<Appointment> emptyAppointments = new HashSet<>();
        Person newPerson = new Person(name, phone, email, address, income, tags, emptyAppointments);

        return newPerson;
    }
    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with appointments added from {@code editPersonDescriptor}.
     */
    public static Person createEditedPersonByOverwritingAppointment(Person personToEdit,
                         EditPersonDescriptor editPersonDescriptor) throws CommandException {
        assert personToEdit != null;

        Appointment newAppointment = editPersonDescriptor.getAppointment().get();

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Set<Tag> tags = personToEdit.getTags();
        IncomeLevel income = personToEdit.getIncome();
        Set<Appointment> appointments = new HashSet<>();
        appointments.add(newAppointment);
        Person newPerson = new Person(name, phone, email, address, income, tags, appointments);
        return newPerson;
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
                && getTags().equals(e.getTags())
                && getAppointment().equals(e.getAppointment());
    }
}
