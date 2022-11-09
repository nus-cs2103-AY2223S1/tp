package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final Country country;
    private final Set<Server> servers;
    private final Set<GameType> gameTypes;
    private final Set<Tag> tags;
    private final Set<ITimesAvailable> timeIntervals;


    /**
     * Constructs a {@code Person}.
     *
     * @param name A valid name.
     * @param minecraftName A valid minecraft name.
     * @param phone A valid phone number.
     * @param email A valid email address.
     * @param address A valid home address.
     * @param socials A valid set of social handles.
     * @param tags A valid set of tags.
     * @param servers A valid set of minecraft servers.
     * @param country A valid country.
     * @param gameTypes A valid set of minecraft game types.
     * @param timeIntervals A valid set of time intervals.
     */
    public Person(Name name, MinecraftName minecraftName, Phone phone, Email email, Address address,
                  Set<Social> socials, Set<Tag> tags, Set<Server> servers, Country country, Set<GameType> gameTypes,
                  Set<ITimesAvailable> timeIntervals) {
        requireAllNonNull(name, minecraftName);

        this.name = name;
        this.minecraftName = minecraftName;
        this.phone = phone == null ? new Phone("") : phone;
        this.email = email == null ? new Email("") : email;
        this.address = address == null ? new Address("") : address;
        this.socials = socials == null ? new HashSet<>() : socials;
        this.tags = tags == null ? new HashSet<>() : tags;
        this.servers = servers == null ? new HashSet<>() : servers;
        this.country = country == null ? new Country("") : country;
        this.gameTypes = gameTypes == null ? new HashSet<>() : gameTypes;
        this.timeIntervals = timeIntervals == null ? new HashSet<>() : timeIntervals;
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

    public Country getCountry() {
        return country;
    }

    /**
     * Returns an immutable social set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * @return A Set collection that contains {@code Social}
     */
    public Set<Social> getSocials() {
        return Collections.unmodifiableSet(socials);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * @return A Set collection that contains {@code Tag}
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable server set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * @return A Set collection that contains {@code Server}
     */
    public Set<Server> getServers() {
        return Collections.unmodifiableSet(servers);
    }

    /**
     * Returns an immutable game type set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * @return A Set collection that contains {@code GameType}
     */
    public Set<GameType> getGameType() {
        return Collections.unmodifiableSet(gameTypes);
    }

    /**
     * Returns an immutable time interval set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * @return A Set collection that contains {@code ITimesAvailable}
     */
    public Set<ITimesAvailable> getTimesAvailable() {
        return Collections.unmodifiableSet(timeIntervals);
    }

    /**
     * Returns true if both persons have the same Minecraft name.
     * Minecraft names are guaranteed to be unique,
     * so this defines a weaker notion of equality between two persons.
     * @param otherPerson A second {@code Person}.
     * @return A boolean value.
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
     * @param other The other object.
     * @return A boolean value.
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

    /**
     * Returns hashcode for purpose of the {@link #equals(Object)} method.
     * @return The hashcode.
     */
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
        builder.append("Country: ").append(getFieldOrElse(getCountry().toString())).append("\n");
        builder.append("Available timings: ").append(getFieldOrElse(getTimesAvailable())).append("\n");

        return builder.toString();
    }

    /**
     * Gets the strings to be checked against in the predicate.
     * @return The collection of strings to be checked against
     */
    public Collection<String> toPredicateCheckingString() {

        List<String> predStrings = new ArrayList<>();
        predStrings.add(getName().toString());
        predStrings.add(getMinecraftName().toString());
        predStrings.add(getPhone().toString());
        predStrings.add(getAddress().toString());
        predStrings.add(getCountry().toString());
        predStrings.addAll(getCollectionString(getServers()));
        predStrings.addAll(getCollectionString(getSocials()));
        predStrings.addAll(getCollectionString(getGameType()));
        predStrings.addAll(getCollectionString(getTags()));

        return predStrings;
    }

    /**
     * Converts a collection of attributes to a collection of Strings.
     * @param c The collection of the attributes of a person
     * @return The collection of Strings representing that attribute
     */
    private Collection<String> getCollectionString(Collection<?> c) {
        return c.stream().map(Object::toString).collect(Collectors.toList());
    }

    private String getFieldOrElse(String s) {
        return s.isEmpty() ? "N/A" : s;
    }

    private String getFieldOrElse(Collection<?> c) {
        return c.isEmpty() ? "None available" : c.toString();
    }

}
