package seedu.realtime.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.realtime.storage.JsonAdaptedOffer.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.realtime.testutil.Assert.assertThrows;
import static seedu.realtime.testutil.TypicalOffers.BENSON;

import org.junit.jupiter.api.Test;

import seedu.realtime.commons.exceptions.IllegalValueException;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.offer.Price;
import seedu.realtime.model.person.Name;

public class JsonAdaptedOfferTest {
    private static final String INVALID_BUYER = "R@chel";
    private static final String INVALID_LISTING = " ";
    private static final String INVALID_OFFER_PRICE = "-1";
    private static final String VALID_BUYER = BENSON.getClient().toString();
    private static final String VALID_LISTING = BENSON.getListing().toString();
    private static final String VALID_OFFER_PRICE = BENSON.getOfferPrice().toString();

    @Test
    public void toModelType_validOfferDetails_returnsOffer() throws Exception {
        JsonAdaptedOffer offer = new JsonAdaptedOffer(BENSON);
        assertEquals(BENSON, offer.toModelType());
    }

    @Test
    public void toModelType_invalidBuyer_throwsIllegalValueException() {
        JsonAdaptedOffer offer =
                new JsonAdaptedOffer(INVALID_BUYER, VALID_LISTING, VALID_OFFER_PRICE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, offer::toModelType);
    }

    @Test
    public void toModelType_nullBuyer_throwsIllegalValueException() {
        JsonAdaptedOffer offer = new JsonAdaptedOffer(null, VALID_LISTING, VALID_OFFER_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, offer::toModelType);
    }

    @Test
    public void toModelType_invalidListing_throwsIllegalValueException() {
        JsonAdaptedOffer offer =
                new JsonAdaptedOffer(VALID_BUYER, INVALID_LISTING, VALID_OFFER_PRICE);
        String expectedMessage = ListingId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, offer::toModelType);
    }

    @Test
    public void toModelType_nullListing_throwsIllegalValueException() {
        JsonAdaptedOffer offer = new JsonAdaptedOffer(VALID_BUYER, null, VALID_OFFER_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ListingId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, offer::toModelType);
    }

    @Test
    public void toModelType_invalidOfferPrice_throwsIllegalValueException() {
        JsonAdaptedOffer offer =
                new JsonAdaptedOffer(VALID_BUYER, VALID_LISTING, INVALID_OFFER_PRICE);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, offer::toModelType);
    }

    @Test
    public void toModelType_nullOfferPrice_throwsIllegalValueException() {
        JsonAdaptedOffer offer = new JsonAdaptedOffer(VALID_BUYER, VALID_LISTING, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, offer::toModelType);
    }
}
