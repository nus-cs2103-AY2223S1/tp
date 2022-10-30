package longtimenosee.model.person;

import static longtimenosee.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import longtimenosee.model.policy.AssignedPolicy;
import longtimenosee.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    public static final Comparator<Person> DEFAULT_COMPARATOR = new Comparator<Person>() {
        public int compare(Person p1, Person p2) {
            return p1.getLocalDateTime().compareTo(p2.getLocalDateTime());
        }
    };
    public static final String SORT_DEFAULT = "default";

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final LocalDateTime timeStamp;
    private final Set<Tag> tags = new HashSet<>();

    // indicate pinned client
    private Boolean pin = false;

    private Set<AssignedPolicy> assignedPolicies = new HashSet<>();
    private Birthday birthday;
    //private PersonNotes notes; //TODO: Just a string
    private Income income;
    private RiskAppetite ra;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  Birthday birthday, Income income, RiskAppetite ra) {
        requireAllNonNull(name, phone, email, address, tags, birthday, income, ra);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.timeStamp = LocalDateTime.now();
        this.tags.addAll(tags);
        this.birthday = birthday;
        this.income = income;
        this.ra = ra;
    }
    /**
     * Constructor for person with pin.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  Birthday birthday, Income income, RiskAppetite ra,
                  boolean pin) {
        requireAllNonNull(name, phone, email, address, tags, birthday, income, ra);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.timeStamp = LocalDateTime.now();
        this.tags.addAll(tags);
        this.birthday = birthday;
        this.income = income;
        this.ra = ra;
        this.pin = pin;
    }

    /**
     * Constructor for person with assigned policies.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  Birthday birthday, Income income, RiskAppetite ra,
                  Set<AssignedPolicy> policies, boolean pin) {
        requireAllNonNull(name, phone, email, address, tags, birthday, income, ra);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.timeStamp = LocalDateTime.now();
        this.tags.addAll(tags);
        this.birthday = birthday;
        this.income = income;
        this.ra = ra;
        this.pin = pin;
        this.assignedPolicies.addAll(policies);
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

    public LocalDateTime getLocalDateTime() {
        return this.timeStamp;
    }

    /**
     * Returns an immutable policy set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<AssignedPolicy> getAssignedPolicies() {
        return Collections.unmodifiableSet(assignedPolicies);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void setPin(boolean b) {
        this.pin = b;
    }

    public boolean getPin() {
        return this.pin;
    }

    public boolean addPolicy(AssignedPolicy assignedPolicy) {
        return this.assignedPolicies.add(assignedPolicy);
    }

    public boolean removePolicy(AssignedPolicy assignedPolicy) {
        return assignedPolicies.remove(assignedPolicy);
    }

    public Birthday getBirthday() {
        return this.birthday;
    }

    public RiskAppetite getRiskAppetite() {
        return this.ra;
    }

    public Income getIncome() {
        return this.income;
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
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getRiskAppetite().equals(getRiskAppetite())
                && otherPerson.getIncome().equals(getIncome());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, birthday, income, ra);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\n" + "Name: ")
                .append(getName() + "\n")
                .append("Phone: ")
                .append(getPhone() + "\n")
                .append("Email: ")
                .append(getEmail() + "\n")
                .append("Address: ")
                .append(getAddress() + "\n")
                .append("Birthday: ")
                .append(getBirthday() + "\n")
                .append("Income: ")
                .append(getIncome() + "\n")
                .append("RiskLevel: ")
                .append(getRiskAppetite());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
