package seedu.condonery.model.property;

import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.condonery.model.client.Client;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.tag.PropertyStatusEnum;
import seedu.condonery.model.tag.PropertyTypeEnum;
import seedu.condonery.model.tag.Tag;

/**
 * Represents a Property in Condonery.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Property {

    // Identity fields
    private final Name name;

    // Data fields
    private final Address address;
    private final Price price;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Client> interestedClients = new HashSet<>();
    private PropertyTypeEnum propertyTypeEnum;
    private PropertyStatusEnum propertyStatusEnum;
    private Path imageDirectoryPath;

    /**
     * Every field must be present and not null.
     */
    public Property(Name name, Address address, Price price, Set<Tag> tags, Set<Client> interestedClients,
                    PropertyTypeEnum propertyTypeEnum,
                    PropertyStatusEnum propertyStatusEnum) {
        this.name = name;
        this.address = address;
        this.price = price;
        this.tags.addAll(tags);
        this.propertyTypeEnum = propertyTypeEnum;
        this.propertyStatusEnum = propertyStatusEnum;
        this.interestedClients.addAll(interestedClients);
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Price getPrice() {
        return price;
    }

    public Path getImageDirectoryPath() {
        return this.imageDirectoryPath;
    }

    public Path getImagePath() {
        if (imageDirectoryPath == null) {
            return null;
        }
        return imageDirectoryPath.resolve("property-" + getCamelCaseName());
    }

    public PropertyTypeEnum getPropertyTypeEnum() {
        return propertyTypeEnum;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable interested client set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Client> getInterestedClients() {
        return Collections.unmodifiableSet(interestedClients);
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

    public PropertyStatusEnum getPropertyStatusEnum() {
        return propertyStatusEnum;
    }

    /**
     * Returns the name of the property in lowerCamelCase.
     * This function is used when getting the file name for image storage.
     * @return property name in lowerCamelCase.
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
     * Changes the imageDirectoryPath of the Property.
     * @param imageDirectoryPath Path to be saved.
     */
    public void setImageDirectoryPath(Path imageDirectoryPath) {
        this.imageDirectoryPath = imageDirectoryPath;
    }

    /**
     * Returns an immutable set containing interested client names, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<String> getInterestedClientNames() {
        HashSet<String> stringInterestedClients = new HashSet<>(interestedClients.size());
        interestedClients.forEach(interestedClient
                -> stringInterestedClients.add(interestedClient.getName().toString()));
        return Collections.unmodifiableSet(stringInterestedClients);
    }

    /**
     * Returns true if both properties have the same name.
     * This defines a weaker notion of equality between two properties.
     */
    public boolean isSameProperty(Property otherProperty) {
        if (otherProperty == this) {
            return true;
        }

        return otherProperty != null
                && otherProperty.getName().equals(getName());
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

        if (!(other instanceof Property)) {
            return false;
        }

        Property otherProperty = (Property) other;
        return otherProperty.getName().equals(getName())
            && otherProperty.getAddress().equals(getAddress())
            && otherProperty.getPrice().equals(getPrice())
            && otherProperty.getPropertyStatusEnum().equals(getPropertyStatusEnum())
            && otherProperty.getPropertyTypeEnum().equals(getPropertyTypeEnum())
            && otherProperty.getTags().equals(getTags())
            && otherProperty.getInterestedClients().equals(getInterestedClients());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, address, price, tags, interestedClients,
                propertyTypeEnum, propertyStatusEnum);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Address: ")
            .append(getAddress())
            .append("; Price: ")
            .append(getPrice())
            .append("; Property Type: ")
            .append(getPropertyTypeEnum())
            .append("; Property Status: ")
            .append(getPropertyStatusEnum());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Client> interestedClients = getInterestedClients();
        if (!interestedClients.isEmpty()) {
            builder.append("; Interested client(s): ");
            interestedClients.forEach(interestedClient -> builder.append(interestedClient.getName().toString()));
        }
        return builder.toString();
    }

}

