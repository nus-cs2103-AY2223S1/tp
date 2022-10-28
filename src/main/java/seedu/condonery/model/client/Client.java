package seedu.condonery.model.client;

import static seedu.condonery.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.tag.Tag;

/**
 * Represents a Client in Condonery.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final Name name;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Property> interestedProperties = new HashSet<>();

    private Path imageDirectoryPath;

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Address address, Set<Tag> tags, Set<Property> interestedProperties) {
        requireAllNonNull(name, address, tags);
        this.name = name;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
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

    /**
     * Returns an immutable property set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Property> getInterestedProperties() {
        return Collections.unmodifiableSet(interestedProperties);
    }

    /**
     * Returns an immutable set containing tag names, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<String> getTagNames() {
        HashSet<String> stringTags = new HashSet<>(tags.size());
        tags.forEach(tag -> stringTags.add(tag.tagName));
        return Collections.unmodifiableSet(stringTags);
    }

    public Path getImageDirectoryPath() {
        return this.imageDirectoryPath;
    }

    public Path getImagePath() {
        requireAllNonNull(imageDirectoryPath);
        return imageDirectoryPath.resolve("client-" + getCamelCaseName());
    }

    /**
     * Returns an immutable set containing interested property names, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<String> getInterestedPropertyNames() {
        HashSet<String> stringProperties = new HashSet<>(interestedProperties.size());
        interestedProperties.forEach(property -> stringProperties.add(property.getName().toString()));
        return Collections.unmodifiableSet(stringProperties);
    }

    /**
     * Returns true if both properties have the same name.
     * This defines a weaker notion of equality between two properties.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    /**
     * Returns the name of the client in lowerCamelCase.
     * This function is used when getting the file name for image storage.
     * @return client name in lowerCamelCase.
     */
    public String getCamelCaseName() {
        String[] words = name.toString().split("[\\W_]+");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0) {
                word = word.isEmpty() ? word : word.toLowerCase();
            } else {
                word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
            }
            builder.append(word);
        }
        return builder.toString();
    }

    /**
     * Changes the imageDirectoryPath of the Client.
     * @param imageDirectoryPath Path to be saved.
     */
    public void setImageDirectoryPath(Path imageDirectoryPath) {
        this.imageDirectoryPath = imageDirectoryPath;
    }

    /**
     * Returns true if both properties have the same identity and data fields.
     * This defines a stronger notion of equality between two properties.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getName().equals(getName())
            && otherClient.getAddress().equals(getAddress())
            && otherClient.getTags().equals(getTags())
            && otherClient.getInterestedProperties().equals(getInterestedProperties());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, address, tags, interestedProperties);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Address: ")
            .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Property> interestedProperties = getInterestedProperties();
        if (!interestedProperties.isEmpty()) {
            builder.append("; Interested Properties: ");
            interestedProperties.forEach(builder::append);
        }

        return builder.toString();
    }
}

