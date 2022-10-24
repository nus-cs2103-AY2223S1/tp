package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.module.Module;
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
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Module> modules = new HashSet<>();
    private final Github githubProfile;
    private final Telegram telegramUsername;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Module> modules,
                  Github githubProfile, Telegram telegramUsername) {
        requireAllNonNull(name, phone, email, address, tags, modules, githubProfile, telegramUsername);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.modules.addAll(modules);
        this.tags.addAll(tags);
        this.githubProfile = githubProfile;
        this.telegramUsername = telegramUsername;
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
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Set<Module> getModules() {
        return Collections.unmodifiableSet(modules);
    }

    public Github getGithub() {
        return githubProfile;
    }

    public Telegram getTelegram() {
        return telegramUsername;
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
                && otherPerson.getModules().equals(getModules())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getGithub().equals(getGithub())
                && otherPerson.getTelegram().equals(getTelegram());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, modules, githubProfile, telegramUsername);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nEmail: ")
                .append(getEmail())
                .append("\nAddress: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            tags.forEach(builder::append);
        }
        Set<Module> modules = getModules();
        if (!modules.isEmpty()) {
            builder.append("\nModules: ");
            modules.forEach(builder::append);
        }

        builder.append("\nGithub profile: ")
                .append(getGithub())
                .append("\nTelegram Username: @")
                .append(getTelegram());

        return builder.toString();
    }

}
