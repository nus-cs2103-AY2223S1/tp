package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    public static final Integer DEFAULT_RATES_PER_CLASS = 40;

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Phone nokPhone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Money moneyOwed;
    private final Money moneyPaid;
    private final Money ratesPerClass;
    private final AdditionalNotes additionalNotes;
    private Class aClass;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code Person} class when first initialized with add command.
     * // Todo: Note that this is a temporary design and subject to update in future iteration
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.nokPhone = new Phone();
        this.email = email;
        this.address = address;
        this.aClass = new Class();
        this.moneyOwed = new Money();
        this.moneyPaid = new Money();
        this.ratesPerClass = new Money(DEFAULT_RATES_PER_CLASS);
        this.additionalNotes = new AdditionalNotes("");
        this.tags.addAll(tags);
    }

    /**
     * Overloaded constructor.
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Phone nokPhone, Email email, Address address, Class aClass,
                  Money moneyOwed, Money moneyPaid, Money ratesPerClass, AdditionalNotes additionalNotes,
                  Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, additionalNotes, aClass);
        this.name = name;
        this.phone = phone;
        this.nokPhone = nokPhone;
        this.email = email;
        this.address = address;
        this.aClass = aClass;
        this.moneyOwed = moneyOwed;
        this.moneyPaid = moneyPaid;
        this.ratesPerClass = ratesPerClass;
        this.additionalNotes = additionalNotes;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Phone getNokPhone() {
        return nokPhone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Class getAClass() {
        if (aClass == null) {
            return new Class();
        }
        return aClass;
    }

    public void setClass(Class aClass) {
        this.aClass = aClass;
    }

    public Money getMoneyOwed() {
        return moneyOwed;
    }

    public Money getMoneyPaid() {
        return moneyPaid;
    }

    public Money getRatesPerClass() {
        return ratesPerClass;
    }

    public AdditionalNotes getAdditionalNotes() {
        return additionalNotes;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same phone number.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getPhone().equals(getPhone());
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
                && otherPerson.getNokPhone().equals(getNokPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getAClass().toString().equals(getAClass().toString())
                && otherPerson.getMoneyOwed().equals(getMoneyOwed())
                && otherPerson.getMoneyPaid().equals(getMoneyPaid())
                && otherPerson.getRatesPerClass().equals(getRatesPerClass())
                && otherPerson.getAdditionalNotes().equals(getAdditionalNotes())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, nokPhone, email, address, aClass, moneyOwed, moneyPaid, ratesPerClass,
                additionalNotes, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Next of Kin Phone: ")
                .append(getNokPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Class Date: ")
                .append(getAClass().toString())
                .append("; Money Owed: ")
                .append(getMoneyOwed())
                .append("; Money Paid: ")
                .append(getMoneyPaid())
                .append("; Money Per Class: ")
                .append(getRatesPerClass())
                .append("; Additional notes: ")
                .append(getAdditionalNotes());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    public int compareTo(Person person) {
        return this.aClass.startTime.compareTo(person.aClass.startTime);
    }
}
