package seedu.realtime.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.realtime.commons.exceptions.IllegalValueException;
import seedu.realtime.model.ReadOnlyRealTime;
import seedu.realtime.model.RealTime;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.person.Client;

/**
 * An Immutable RealTime that is serializable to JSON format.
 */
@JsonRootName(value = "RealTime")
class JsonSerializableRealTime {


    public static final String MESSAGE_DUPLICATE_CLIENT = "Clients list contains duplicate client(s).";
    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_OFFER = "Offers list contains duplicate offer(s)";
    public static final String MESSAGE_DUPLICATE_LISTING = "Listings list contains duplicate listing(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedClient> clients = new ArrayList<>();
    private final List<JsonAdaptedOffer> offers = new ArrayList<>();
    private final List<JsonAdaptedListing> listings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRealTime} with the given clients and offers.
     */
    @JsonCreator
    public JsonSerializableRealTime(@JsonProperty("clients") List<JsonAdaptedClient> clients,
                                       @JsonProperty("offers") List<JsonAdaptedOffer> offers,
                                       @JsonProperty("listings") List<JsonAdaptedListing> listings) {
        this.clients.addAll(clients);
        this.offers.addAll(offers);
        this.listings.addAll(listings);
    }

    /**
     * Converts a given {@code ReadOnlyRealTime} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRealTime}.
     */
    public JsonSerializableRealTime(ReadOnlyRealTime source) {
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
        offers.addAll(source.getOfferList().stream().map(JsonAdaptedOffer::new).collect(Collectors.toList()));
        listings.addAll(source.getListingList().stream().map(JsonAdaptedListing::new).collect(Collectors.toList()));
    }

    /**
     * Converts realtime into the model's {@code RealTime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public RealTime toModelType() throws IllegalValueException {
        RealTime realTime = new RealTime();
        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client client = jsonAdaptedClient.toModelType();
            if (realTime.hasClient(client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            realTime.addClient(client);
        }

        for (JsonAdaptedOffer jsonAdaptedOffer : offers) {
            Offer offer = jsonAdaptedOffer.toModelType();
            if (realTime.hasOffer(offer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_OFFER);
            }
            realTime.addOffer(offer);
        }

        for (JsonAdaptedListing jsonAdaptedListing : listings) {
            Listing listing = jsonAdaptedListing.toModelType();
            if (realTime.hasListing(listing)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LISTING);
            }
            realTime.addListing(listing);
        }
        return realTime;
    }

}
