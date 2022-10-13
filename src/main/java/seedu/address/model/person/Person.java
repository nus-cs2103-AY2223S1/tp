package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.item.AbstractContainerItem;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.item.EntryType;
import seedu.address.model.item.exceptions.ItemCannotBeParentException;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person implements DisplayItem {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Fields fields = new Fields();

    private Set<AbstractContainerItem> parents = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Fields fields) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.fields.addAll(fields);
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

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Fields getFields() {
        return fields;
    }

    public void addField(String fieldName) {
        fields.addField(fieldName);
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
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
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
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        //        if (!fields.isEmpty()) {
        //            builder.append("; Fields: ")
        //                   .append(fields.toString());
        //        }
        return builder.toString();
    }

    @Override
    public EntryType getEntryType() {
        return EntryType.USER;
    }

    @Override
    public boolean stronglyEqual(DisplayItem o) {
        return equals(o);
    }

    @Override
    public boolean weaklyEqual(DisplayItem o) {
        if (!(o instanceof Person)) {
            return false;
        }
        return isSamePerson((Person) o);
    }

    @Override
    public void setParent(DisplayItem o) throws ItemCannotBeParentException {
        if (o == null) {
            return;
        }
        if (!(o instanceof AbstractContainerItem) || parents.contains(o)) {
            throw new ItemCannotBeParentException(o);
        }

        parents.add((AbstractContainerItem) o);
    }

    public void removeParent(AbstractContainerItem deletedParent) {
        parents.removeIf(p -> (p.equals(deletedParent) || p.isPartOfContext(deletedParent)));
    }

    @Override
    public boolean isPartOfContext(DisplayItem o) {
        if (o == null || parents.contains(o)) {
            return true;
        }
        for (AbstractContainerItem parent : parents) {
            if (parent.isPartOfContext(o)) {
                return true;
            }
        }
        return false;
    }
}
