package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.address.Address;
import seedu.address.model.property.Description;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Property objects.
 */
public class PropertyBuilder {

    public static final String DEFAULT_NAME = "Peak Residence";
    public static final String DEFAULT_PRICE = "3000000";
    public static final String DEFAULT_ADDRESS = "333 Thompson Road";
    public static final String DEFAULT_DESCRIPTION = "A 5-storey condo on top of a hill - "
            + "Peak Residence offers you serenity away from the hustle and bustle with breathtaking views all around.";


    private PropertyName name;
    private Price price;
    private Address address;
    private Description description;
    private Set<Tag> tags;

    /**
     * Creates a {@code PropertyBuilder} with the default details.
     */
    public PropertyBuilder() {
        name = new PropertyName(DEFAULT_NAME);
        price = new Price(DEFAULT_PRICE);
        description = new Description(DEFAULT_DESCRIPTION);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PropertyBuilder with the data of {@code propertyToCopy}.
     */
    public PropertyBuilder(Property propertyToCopy) {
        name = propertyToCopy.getName();
        price = propertyToCopy.getPrice();
        description = propertyToCopy.getDescription();
        address = propertyToCopy.getAddress();
        tags = new HashSet<>(propertyToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Property} that we are building.
     */
    public PropertyBuilder withName(String name) {
        this.name = new PropertyName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Property} that we are building.
     */
    public PropertyBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Property} that we are building.
     */
    public PropertyBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Property} that we are building.
     */
    public PropertyBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Property build() {
        return new Property(name, price, address, description, tags);
    }
}
