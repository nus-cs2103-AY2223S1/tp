package seedu.condonery.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.condonery.model.client.Client;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.property.Price;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.tag.PropertyStatusEnum;
import seedu.condonery.model.tag.PropertyTypeEnum;
import seedu.condonery.model.tag.Tag;
import seedu.condonery.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PropertyBuilder {

    public static final String DEFAULT_NAME = "Bishan";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_PRICE = "100000";
    public static final Set<Tag> DEFAULT_TAGS = new HashSet<Tag>() {{
        }};
    public static final PropertyTypeEnum DEFAULT_PROPERTY_TYPE = PropertyTypeEnum.CONDO;
    public static final PropertyStatusEnum DEFAULT_PROPERTY_STATUS = PropertyStatusEnum.AVAILABLE;
    public static final Set<Client> DEFAULT_CLIENTS = new HashSet<Client>() {{
        }};
    private Name name;
    private Address address;
    private Price price;
    private Set<Tag> tags;
    private Set<Client> clients;
    private PropertyTypeEnum propertyTypeEnum;
    private PropertyStatusEnum propertyStatusEnum;

    /**
     * Creates a {@code PropertyBuilder} with the default details.
     */
    public PropertyBuilder() {
        name = new Name(DEFAULT_NAME);
        address = new Address(DEFAULT_ADDRESS);
        price = new Price(DEFAULT_PRICE);
        tags = DEFAULT_TAGS;
        clients = DEFAULT_CLIENTS;
        propertyTypeEnum = DEFAULT_PROPERTY_TYPE;
        propertyStatusEnum = DEFAULT_PROPERTY_STATUS;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code propertyToCopy}.
     */
    public PropertyBuilder(Property propertyToCopy) {
        name = propertyToCopy.getName();
        address = propertyToCopy.getAddress();
        price = propertyToCopy.getPrice();
        tags = new HashSet<>(propertyToCopy.getTags());
        clients = new HashSet<>(propertyToCopy.getInterestedClients());
        propertyTypeEnum = propertyToCopy.getPropertyTypeEnum();
        propertyStatusEnum = propertyToCopy.getPropertyStatusEnum();
    }

    /**
     * Sets the {@code Name} of the {@code Property} that we are building.
     */
    public PropertyBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Property} that we are building.
     */
    public PropertyBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code clients} into a {@code Set<Client>} and set it to the {@code Property} that we are building.
     */
    public PropertyBuilder withClients(Client... clients) {
        this.clients = SampleDataUtil.getClientSet(clients);
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
     * Sets the {@code PropertyType} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPropertyType(String propertyType) {
        this.propertyTypeEnum = PropertyTypeEnum.valueOf(propertyType);
        return this;
    }

    /**
     * Sets the {@code PropertyStatus} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPropertyStatus(String propertyStatus) {
        this.propertyStatusEnum = PropertyStatusEnum.valueOf(propertyStatus);
        return this;
    }

    public Property build() {
        return new Property(name, address, price, tags, clients, propertyTypeEnum, propertyStatusEnum);
    }

}
