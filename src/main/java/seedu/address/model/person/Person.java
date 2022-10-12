package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import javafx.collections.ObservableMap;
import seedu.address.model.person.exceptions.TagTypeNotFoundException;
import seedu.address.model.tag.TagType;
import seedu.address.model.tag.UniqueTagList;


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

    private UniqueTagTypeMap tagTypeMap;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, UniqueTagTypeMap tagTypeMap) {
        requireAllNonNull(name, phone, email, address, tagTypeMap);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tagTypeMap = tagTypeMap;
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

    public String getDetailsAsString() {
        return String.format("%s %s %s %s %s", name, phone, email, address, tagTypeMap);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public ObservableMap<TagType, UniqueTagList> getTags() {
        return tagTypeMap.asUnmodifiableObservableMap();
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
        return Objects.hash(name, phone, email, address, tagTypeMap);
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

        ObservableMap<TagType, UniqueTagList> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach((tagType, tagList) -> builder.append(String.format("%s: %s", tagType.toString(),
                    tagList.toString())));
        }
        return builder.toString();
    }

    /**
     * Deletes tagType for the person if present.
     */
    public void deleteTagType(TagType tagType) throws TagTypeNotFoundException {
        if (this.tagTypeMap.contains(tagType)) {
            this.tagTypeMap.removeTagType(tagType);
        }
    }
}
