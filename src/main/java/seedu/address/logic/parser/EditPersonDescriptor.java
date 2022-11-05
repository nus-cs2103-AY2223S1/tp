package seedu.address.logic.parser;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.IncomeLevel;
import seedu.address.model.person.Monthly;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.ClientTag;
import seedu.address.model.tag.NormalTag;
import seedu.address.model.tag.PlanTag;
import seedu.address.model.tag.RiskTag;
import seedu.address.model.util.MaximumSortedList;

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
    private RiskTag riskTag;
    private PlanTag planTag;
    private ClientTag clientTag;
    private Set<NormalTag> tags;
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
        setClientTag(toCopy.clientTag);
        setTags(toCopy.tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone, email, address, income, monthly,
                riskTag, planTag, clientTag, tags);
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
    public void setClientTag(ClientTag clientTag) {
        this.clientTag = clientTag;
    }

    public Optional<ClientTag> getClientTag() {
        return Optional.ofNullable(clientTag);
    }
    public void setIncome(IncomeLevel income) {
        this.income = income;
    }
    public Optional<IncomeLevel> getIncome() {
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
        ClientTag updatedClientTag = editPersonDescriptor.getClientTag().orElse(personToEdit.getClientTag());
        Set<NormalTag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        MaximumSortedList<Appointment> originalAppointments = personToEdit.getAppointments();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedIncomeLevel,
                updateMonthly, updatedRiskTag, updatedPlanTag, updatedClientTag, updatedTags, originalAppointments);

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
                && getPlanTag().equals(e.getPlanTag())
                && getClientTag().equals(e.getClientTag())
                && getIncome().equals(e.getIncome())
                && getTags().equals(e.getTags());
    }
}
