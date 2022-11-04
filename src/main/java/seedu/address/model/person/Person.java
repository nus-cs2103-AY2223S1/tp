package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in Survin. Guarantees: details are present and
 * not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Gender gender;
    private final Birthdate birthdate;
    private final Race race;
    private final Religion religion;

    // Data fields
    private final Address address;
    private final Set<Survey> surveys = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Gender gender, Birthdate birthdate, Race race,
            Religion religion, Set<Survey> surveys, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, gender, birthdate, race, religion, surveys, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.birthdate = birthdate;
        this.race = race;
        this.religion = religion;
        this.surveys.addAll(surveys);
        this.tags.addAll(tags);
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

    public Gender getGender() {
        return gender;
    }

    public Birthdate getBirthdate() {
        return birthdate;
    }

    public Race getRace() {
        return race;
    }

    public Religion getReligion() {
        return religion;
    }

    /**
     * Returns an immutable survey set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Survey> getSurveys() {
        return Collections.unmodifiableSet(surveys);
    }

    public boolean hasMultipleSurveys() {
        return surveys.size() > 1;
    }

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if the following fields between both persons are equal
     * (case-sensitive): 1. Name 2. Phone number 3. Email 4. Survey This defines a
     * weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone()) && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same identity and data fields. This
     * defines a stronger notion of equality between two persons.
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
        return otherPerson.getName().equals(getName()) && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail()) && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getGender().equals(getGender()) && otherPerson.getBirthdate().equals(getBirthdate())
                && otherPerson.getRace().equals(getRace()) && otherPerson.getReligion().equals(getReligion())
                && otherPerson.getSurveys().equals(getSurveys()) && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, gender, birthdate, race, religion, surveys, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName()).append("; Phone: ").append(getPhone()).append("; Email: ").append(getEmail())
                .append("; Address: ").append(getAddress()).append("; Gender: ").append(getGender())
                .append("; Birthdate: ").append(getBirthdate()).append("; Race: ").append(getRace())
                .append("; Religion: ").append(getReligion());

        Set<Survey> surveys = getSurveys();
        if (!surveys.isEmpty()) {
            builder.append("; Surveys: ");
            surveys.forEach(builder::append);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
