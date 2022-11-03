package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OCCUPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.group.Group;
import seedu.address.model.social.Social;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Occupation occupation;
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Social social;

    // Data fields
    private final Tutorial tutorial;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Group> groups = new HashSet<>();
    private String imageUrl;

    /**
     * Every field must be present and not null.
     */
    public Person(Occupation occupation, Name name, Phone phone, Email email, Tutorial tutorial, Address address,
                  Set<Tag> tags, Social social, Set<Group> groups) {
        requireAllNonNull(name, phone, email, address, tags, social, groups);
        this.occupation = occupation;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.tutorial = tutorial;
        this.address = address;
        this.tags.addAll(tags);
        this.groups.addAll(groups);
        this.social = social;
        if (social.getEmail() == null || social.getEmail().equals("<none>")) {
            this.social.addEmail(email.toString());
        }
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return this.imageUrl == null ? "file:src/main/resources/images/default_pic.png" : this.imageUrl;
    }

    public Occupation getOccupation() {
        return occupation;
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

    public Tutorial getTutorial() {
        return tutorial;
    }

    public Address getAddress() {
        return address;
    }

    public Social getSocial() {
        return social;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable group set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Group> getGroups() {
        return Collections.unmodifiableSet(groups);
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
                && otherPerson.equals(this);
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
                && otherPerson.getOccupation().equals(getOccupation())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getTutorial().equals(getTutorial())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getGroups().equals(getGroups())
                && otherPerson.getSocial().equals(getSocial());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(occupation, name, phone, email, tutorial, address, tags, social, groups);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Occupation: ")
                .append(getOccupation())
                .append("; Name: ")
                .append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Tutorial: ")
                .append(getTutorial())
                .append("; Address: ")
                .append(getAddress())
                .append("; Social: ")
                .append(getSocial());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Group> groups = getGroups();
        if (!groups.isEmpty()) {
            builder.append("; Groups: ");
            groups.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Finds the most complete edit command that does not modify the person.
     * @param index The index of the person on the list.
     * @return a {@code String} edit command that does nothing.
     */
    public String getEditString(int index) {
        StringBuilder ret = new StringBuilder(EditCommand.COMMAND_WORD + " " + index
            + " " + PREFIX_OCCUPATION + occupation
            + " " + PREFIX_NAME + name
            + (phone.toString().isEmpty() ? "" : " " + PREFIX_PHONE + phone)
            + (email.toString().isEmpty() ? "" : " " + PREFIX_EMAIL + email)
            + (tutorial.toString().isEmpty() ? "" : " " + PREFIX_TUTORIAL + tutorial)
            + (address.toString().isEmpty() ? "" : " " + PREFIX_ADDRESS + address));
        for (Tag i : tags) {
            ret.append(' ');
            ret.append(PREFIX_TAG);
            ret.append(i.tagName);
        }
        return ret.toString();
    }
}
