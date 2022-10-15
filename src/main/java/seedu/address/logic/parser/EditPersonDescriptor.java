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
import seedu.address.model.person.Monthly;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.NormalTag;
import seedu.address.model.tag.PlanTag;
import seedu.address.model.tag.RiskTag;

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
    private Monthly monthly;
    private PlanTag planTag;
    private RiskTag riskTag;
    private Set<NormalTag> tags;
    private Set<Appointment> appointments;
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
        setMonthly(toCopy.monthly);
        setRiskTag(toCopy.riskTag);
        setPlanTag(toCopy.planTag);
        setTags(toCopy.tags);
        setAppointments(toCopy.appointments);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone, email, address, income, monthly, riskTag, planTag,
                tags, appointments);
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

    public void setRiskTag(RiskTag riskTag) {
        this.riskTag = riskTag;
    }

    public Optional<RiskTag> getRiskTag() {
        return Optional.ofNullable(riskTag);
    }

    public void setPlanTag(PlanTag planTag) {
        this.planTag = planTag;
    }

    public Optional<PlanTag> getPlanTag() {
        return Optional.ofNullable(planTag);
    }
    /**
     * Sets {@code appointments} to this object's {@code appointments}.
     * A defensive copy of {@code appointments} is used internally.
     */
    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = (appointments != null) ? new HashSet<>(appointments) : null;
    }

    public Optional<Set<Appointment>> getAppointments() {
        return Optional.ofNullable(appointments);
    }

    public void setIncome(IncomeLevel income) {
        this.income = income;
    }
    private Optional<IncomeLevel> getIncome() {
        return Optional.ofNullable(income);
    }

    public void setMonthly(Monthly monthly) {
        this.monthly = monthly;
    }
    public Optional<Monthly> getMonthly() {
        return Optional.ofNullable(monthly);
    }
    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<NormalTag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<NormalTag>> getTags() {
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
        Monthly updateMonthly = editPersonDescriptor.getMonthly().orElse(personToEdit.getMonthly());
        RiskTag updatedRiskTag = editPersonDescriptor.getRiskTag().orElse(personToEdit.getRiskTag());
        PlanTag updatedPlanTag = editPersonDescriptor.getPlanTag().orElse(personToEdit.getPlanTag());
        Set<NormalTag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedIncomeLevel,
                updateMonthly, updatedRiskTag, updatedPlanTag, updatedTags);
    }


    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with appointments added from {@code editPersonDescriptor}.
     */
    public static Person createEditedPersonByAddingAppointments(Person personToEdit,
                         EditPersonDescriptor editPersonDescriptor) throws CommandException {
        assert personToEdit != null;

        Set<Appointment> currentAppointments = personToEdit.getAppointments();
        Set<Appointment> newAppointments = editPersonDescriptor.getAppointments().get();
        for (Appointment newAppointment:newAppointments) {
            if (currentAppointments.contains(newAppointment)) {
                throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
        }
        editPersonDescriptor.appointments.forEach(currentAppointments::add);

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Set<NormalTag> tags = personToEdit.getTags();
        Monthly monthly = personToEdit.getMonthly();
        RiskTag risktag = personToEdit.getRiskTag();
        PlanTag planTag = personToEdit.getPlanTag();
        IncomeLevel income = personToEdit.getIncome();
        Person newPerson = new Person(name, phone, email, address, income, monthly, risktag, planTag,
                tags, currentAppointments);
        return newPerson;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * overwritten with appointments from {@code editPersonDescriptor}.
     */
    public static Person createEditedPersonByOverwritingAppointments(Person personToEdit,
                                                                   EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Monthly monthly = personToEdit.getMonthly();
        PlanTag planTag = personToEdit.getPlanTag();
        RiskTag riskTag = personToEdit.getRiskTag();
        Set<NormalTag> tags = personToEdit.getTags();
        IncomeLevel income = personToEdit.getIncome();
        Set<Appointment> newAppointmentsOnly = editPersonDescriptor.getAppointments().get();
        Person newPerson = new Person(name, phone, email, address, income, monthly, riskTag, planTag,
                tags, newAppointmentsOnly);

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
                && getRiskTag().equals(e.getRiskTag())
                && getMonthly().equals(e.getMonthly())
                && getTags().equals(e.getTags())
                && getAppointments().equals(e.getAppointments());
    }
}
