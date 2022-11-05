package seedu.realtime.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.realtime.commons.exceptions.IllegalValueException;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.offer.Price;
import seedu.realtime.model.person.Address;
import seedu.realtime.model.person.Name;
import seedu.realtime.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Listing}.
 */
public class JsonAdaptedListing {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Listing's %s field is missing!";

    private final String listingId;
    private final String address;
    private final String name;
    private final String askingPrice;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedListing(@JsonProperty("listingId") String listingId, @JsonProperty("address") String address,
                             @JsonProperty("name") String name, @JsonProperty("askingPrice") String askingPrice,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.listingId = listingId;
        this.address = address;
        this.name = name;
        this.askingPrice = askingPrice;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Client} into this class for Jackson use.
     */
    public JsonAdaptedListing(Listing source) {
        listingId = source.getId().value;
        address = source.getAddress().value;
        name = source.getName().fullName;
        askingPrice = source.getAskingPrice().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's {@code Listing} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted client.
     */
    public Listing toModelType() throws IllegalValueException {
        final List<Tag> listingTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            listingTags.add(tag.toModelType());
        }

        if (listingId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ListingId.class.getSimpleName()));
        }
        if (!ListingId.isValidListingId(listingId)) {
            throw new IllegalValueException(ListingId.MESSAGE_CONSTRAINTS);
        }
        final ListingId modelListingId = new ListingId(listingId);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (askingPrice == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(askingPrice)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelAskingPrice = new Price(askingPrice);


        final Set<Tag> modelTags = new HashSet<>(listingTags);
        return new Listing(modelListingId, modelAddress, modelName, modelAskingPrice);
    }
}
