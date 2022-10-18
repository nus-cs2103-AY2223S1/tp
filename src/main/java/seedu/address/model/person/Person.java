package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.portfolio.Plan;
import seedu.address.model.portfolio.Risk;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the FinBook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Income income;
    private final MeetingDate meetingDate;
    private final Set<Tag> tags = new HashSet<>();
    private final Risk risk;
    private final Set<Plan> plans = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Income income, MeetingDate meetingDate,
                  Set<Tag> tags, Risk risk, Set<Plan> plan) {
        requireAllNonNull(name, phone, email, address, income, meetingDate, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.income = income;
        this.meetingDate = meetingDate;
        this.tags.addAll(tags);
        this.risk = risk;
        this.plans.addAll(plan);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Income getIncome() {
        return income;
    }

    public MeetingDate getMeetingDate() {
        return meetingDate;
    }

    public Risk getRisk() {
        return risk;
    }

    public Set<Plan> getPlans() {
        return Collections.unmodifiableSet(plans);
    }
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getIncome().equals(getIncome())
                && otherPerson.getMeetingDate().equals(getMeetingDate())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getRisk().equals(getRisk())
                && otherPerson.getPlans().equals(getPlans());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, income, meetingDate, tags, risk, plans);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Income: ")
                .append(getIncome())
                .append("; MeetingDate: ")
                .append(getMeetingDate());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        builder.append("; Risk: ")
                .append(getRisk());

        Set<Plan> plans = getPlans();
        if (!plans.isEmpty()) {
            builder.append("; Plans: ");
            plans.forEach(builder::append);
        }

        return builder.toString();
    }

}
