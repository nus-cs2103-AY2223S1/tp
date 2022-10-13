package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.offer.Offer;
import seedu.address.model.offer.Price;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;

/**
 * Jackson-friendly version of {@link Offer}.
 */
public class JsonAdaptedOffer {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Offer's %s field is missing!";

    private final String name;
    private final String listing;
    private final String offerPrice;

    /**
     * Constructs a {@code JsonAdaptedOffer} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedOffer(@JsonProperty("name") String name, @JsonProperty("listing") String listing,
                            @JsonProperty("offerPrice") String offerPrice) {
        this.name = name;
        this.listing = listing;
        this.offerPrice = offerPrice;
    }

    /**
     * Converts a given {@code Offer} into this class for Jackson use.
     */
    public JsonAdaptedOffer(Offer source) {
        name = source.getClient().fullName;
        listing = source.getListing().value;
        offerPrice = source.getOfferPrice().value;
    }

    /**
     * Converts this Jackson-friendly adapted offer object into the model's {@code Offer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted offer.
     */
    public Offer toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (listing == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(listing)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(listing);

        if (offerPrice == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Integer.class.getSimpleName()));
        }

        if (!Price.isValidPrice(offerPrice)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }

        final Price modelOfferPrice = new Price(offerPrice);


        return new Offer(modelName, modelAddress, modelOfferPrice);
    }
}
