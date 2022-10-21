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

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Birthday birthday;
    private final Insurance healthInsurance;
    private final Insurance disabilityInsurance;
    private final Insurance criticalIllnessInsurance;
    private final Insurance lifeInsurance;
    private final Set<Tag> tags = new HashSet<>();
    private final Reminder reminders;

    /**
     * Every field must be present and not null, except for Insurance fields which are added by overloaded method.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Birthday birthday, Reminder reminders, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.tags.addAll(tags);
        this.reminders = reminders;
        this.healthInsurance = new HealthInsurance(false);
        this.disabilityInsurance = new DisabilityInsurance(false);
        this.criticalIllnessInsurance = new CriticalIllnessInsurance(false);
        this.lifeInsurance = new LifeInsurance(false);
    }

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Birthday birthday, Insurance healthInsurance, Insurance disabilityInsurance,
                  Insurance criticalIllnessInsurance, Insurance lifeInsurance,
                  Reminder reminders, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.tags.addAll(tags);
        this.healthInsurance = healthInsurance;
        this.disabilityInsurance = disabilityInsurance;
        this.criticalIllnessInsurance = criticalIllnessInsurance;
        this.lifeInsurance = lifeInsurance;
        this.reminders = reminders;
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

    public Birthday getBirthday() {
        return birthday;
    }

    public Insurance getHealthInsurance() {
        return healthInsurance;
    }

    public Insurance getDisabilityInsurance() {
        return disabilityInsurance;
    }

    public Insurance getCriticalIllnessInsurance() {
        return criticalIllnessInsurance;
    }

    public Insurance getLifeInsurance() {
        return lifeInsurance;
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Reminder getReminders() {
        return reminders;
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
                && otherPerson.getBirthday().equals(getBirthday())
                && otherPerson.getHealthInsurance().equals(getHealthInsurance())
                && otherPerson.getDisabilityInsurance().equals(getDisabilityInsurance())
                && otherPerson.getCriticalIllnessInsurance().equals(getCriticalIllnessInsurance())
                && otherPerson.getLifeInsurance().equals(getLifeInsurance())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, birthday,
                healthInsurance, disabilityInsurance, criticalIllnessInsurance, lifeInsurance, tags);
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
                .append("; Birthday: ")
                .append(getBirthday())
                .append("; Health Insurance:")
                .append(getHealthInsurance())
                .append("; Disability Insurance:")
                .append(getDisabilityInsurance())
                .append("; Critical Illness Insurance:")
                .append(getCriticalIllnessInsurance())
                .append("; Life Insurance:")
                .append(getLifeInsurance());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
