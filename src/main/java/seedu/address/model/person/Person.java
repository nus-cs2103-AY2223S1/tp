package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.Objects;

import seedu.address.model.server.Server;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final MinecraftName minecraftName;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Social> socials;
    private final TimeZone timeZone;
    private final Set<Server> servers;
    private final Set<Tag> tags;

    /**
     * Every field must be present and not null.
     */


    public Person(Name name, MinecraftName minecraftName, Phone phone, Email email, Address address, Set<Social> socials, Set<Tag> tags, Set<Server> servers, TimeZone timeZone) {
        requireAllNonNull(name, minecraftName);

        this.name = name;
        this.minecraftName = minecraftName;
        this.phone = phone == null ? new Phone("") : phone;
        this.email = email == null ? new Email("") : email;
        this.address = address == null ? new Address("") : address;
        this.socials = socials == null ? new HashSet<>() : socials;
        this.tags = tags == null ? new HashSet<>() : tags;
        this.servers = servers == null ? new HashSet<>() : servers;
        this.timeZone = timeZone == null ? new TimeZone("") : timeZone;
    }

    public Name getName() {
        return name;
    }

    public MinecraftName getMinecraftName() {
        return minecraftName;
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

    public Set<Social> getSocials() {
        return Collections.unmodifiableSet(socials);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Set<Server> getServers() {
        return Collections.unmodifiableSet(servers);
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Returns true if both persons have the same Minecraft name.
     * Minecraft names are guaranteed to be unique,
     * so this defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getMinecraftName().equals(getMinecraftName());
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
                && otherPerson.getMinecraftName().equals(getMinecraftName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append(getName()).append("; Minecraft Username: ").append(getMinecraftName());
        return builder.toString();

    }

}
