package seedu.condonery.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.condonery.commons.exceptions.IllegalValueException;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.property.Price;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.tag.PropertyStatusEnum;
import seedu.condonery.model.tag.PropertyTypeEnum;
import seedu.condonery.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Property}.
 */
class JsonAdaptedProperty {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Property's %s field is missing!";

    private final String name;
    private final String address;
    private final String price;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedClient> interestedClients = new ArrayList<>();
    private final String propertyType;
    private final String propertyStatus;


    /**
     * Constructs a {@code JsonAdaptedProperty} with the given property details.
     */
    @JsonCreator
    public JsonAdaptedProperty(@JsonProperty("name") String name, @JsonProperty("address") String address,
                               @JsonProperty("price") String price,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                               @JsonProperty("propertyType")String propertyType,
                               @JsonProperty("propertyStatus")String propertyStatus,
                              @JsonProperty("interestedClients") List<JsonAdaptedClient> interestedClients
    ) {
        this.name = name;
        this.address = address;
        this.price = price;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (interestedClients != null) {
            this.interestedClients.addAll(interestedClients);
        }
        this.propertyType = propertyType;
        this.propertyStatus = propertyStatus;
    }

    /**
     * Converts a given {@code Property} into this class for Jackson use.
     */
    public JsonAdaptedProperty(Property source) {
        name = source.getName().fullName;
        address = source.getAddress().value;
        price = source.getPrice().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        interestedClients.addAll(source.getInterestedClients().stream()
                .map(JsonAdaptedClient::new)
                .collect(Collectors.toList()));
        propertyType = source.getPropertyTypeEnum().toString();
        propertyStatus = source.getPropertyStatusEnum().toString();
    }

    /**
     * Converts this Jackson-friendly adapted property object into the model's {@code Property} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted property.
     */
    public Property toModelType() throws IllegalValueException {
        final List<Tag> propertyTags = new ArrayList<>();
        final List<Client> interestedClients = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            propertyTags.add(tag.toModelType());
        }

        for (JsonAdaptedClient client : this.interestedClients) {
            interestedClients.add(client.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price(price);

        final Set<Tag> modelTags = new HashSet<>(propertyTags);

        final Set<Client> modelInterestedClients = new HashSet<>(interestedClients);

        if (propertyType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PropertyTypeEnum.class.getSimpleName()));
        }

        final PropertyTypeEnum modelPropertyType = PropertyTypeEnum.valueOf(propertyType);
        final PropertyStatusEnum modelPropertyStatus = PropertyStatusEnum.valueOf(propertyStatus);
        return new Property(modelName, modelAddress, modelPrice, modelTags,
               modelInterestedClients, modelPropertyType, modelPropertyStatus);
    }

}
