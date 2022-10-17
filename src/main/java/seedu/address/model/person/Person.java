package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private final Set<GameType> gameTypes;
    private final Set<Tag> tags;

    /**
     * Every field must be present and not null.
     */


    public Person(Name name, MinecraftName minecraftName, Phone phone, Email email, Address address,
                  Set<Social> socials, Set<Tag> tags, Set<Server> servers, TimeZone timeZone, Set<GameType> gameTypes) {
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
        this.gameTypes = gameTypes == null ? new HashSet<>() : gameTypes;
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

    public Set<GameType> getGameType() {
        return Collections.unmodifiableSet(gameTypes);
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

        if (otherPerson == null) {
            return false;
        }

        return otherPerson.getMinecraftName().equals(getMinecraftName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of quality between two persons.
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

    /**
     * Gets the string to be displayed in the main UI page.
     * @return The display string
     */
    public String toDisplayString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("Phone: ").append(getFieldOrElse(getPhone().toString())).append("\n");
        builder.append("Email: ").append(getFieldOrElse(getEmail().toString())).append("\n");
        builder.append("Address: ").append(getFieldOrElse(getAddress().toString())).append("\n");
        builder.append("Timezone: ").append(getFieldOrElse(getTimeZone().toString())).append("\n");
        builder.append("Servers: ").append(getFieldOrElse(getServers())).append("\n");
        builder.append("Timezone: ").append(getFieldOrElse(getTimeZone().toString()));
        builder.append("Preferred Game Types: ").append(getFieldOrElse(getGameType().toString()));
        builder.append("Socials: ").append(getFieldOrElse(getSocials()));

        return builder.toString();
    }

    private String getFieldOrElse(String s) {
        return s.isEmpty() ? "N/A" : s;
    }

    private String getFieldOrElse(Collection<?> c) {
        return c.isEmpty() ? "None available" : c.toString();
    }

}
