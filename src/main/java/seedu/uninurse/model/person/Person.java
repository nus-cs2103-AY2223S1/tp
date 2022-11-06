package seedu.uninurse.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.uninurse.model.tag.TagList;

/**
 * Represents a Person in the uninurse book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final TagList tags;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, TagList tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags = tags;
    }

    /**
     * Used to return a new immutable {@code Person} when provided with a {@code Person}.
     */
    public Person(Person person) {
        requireNonNull(person);
        this.name = person.name;
        this.phone = person.phone;
        this.email = person.email;
        this.address = person.address;
        this.tags = person.tags;
    }

    /**
     * Used to return a new immutable {@code Person} when {@code TagList} is updated.
     * @param person The person to be updated.
     * @param updatedTagList The updated tags.
     */
    public Person(Person person, TagList updatedTagList) {
        requireAllNonNull(person, updatedTagList);
        this.name = person.name;
        this.phone = person.phone;
        this.email = person.email;
        this.address = person.address;
        this.tags = updatedTagList;
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

    public TagList getTags() {
        return tags;
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

    public void update() {
        return;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getName())
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nEmail: ")
                .append(getEmail())
                .append("\nAddress: ")
                .append(getAddress());

        TagList tags = getTags();
        if (!tags.isEmpty()) {
            sb.append("\nTags: ")
                    .append(tags);
        }
        return sb.toString();
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

        Person o = (Person) other;
        return o.getName().equals(getName())
                && o.getPhone().equals(getPhone())
                && o.getEmail().equals(getEmail())
                && o.getAddress().equals(getAddress())
                && o.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }
}
