package seedu.realtime.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.realtime.storage.JsonAdaptedListing.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.realtime.testutil.Assert.assertThrows;
import static seedu.realtime.testutil.TypicalListings.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.realtime.commons.exceptions.IllegalValueException;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.offer.Price;
import seedu.realtime.model.person.Address;
import seedu.realtime.model.person.Name;

public class JsonAdaptedListingTest {
    private static final String INVALID_ID = " ";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_ASKING_PRICE = "-1";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_ID = BENSON.getId().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_ASKING_PRICE = BENSON.getAskingPrice().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validListingDetails_returnsListing() throws Exception {
        JsonAdaptedListing listing = new JsonAdaptedListing(BENSON);
        assertEquals(BENSON, listing.toModelType());
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedListing listing =
                new JsonAdaptedListing(INVALID_ID, VALID_ADDRESS, VALID_NAME, VALID_ASKING_PRICE, VALID_TAGS);
        String expectedMessage = ListingId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, listing::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedListing listing = new JsonAdaptedListing(null, VALID_ADDRESS,
                VALID_NAME, VALID_ASKING_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ListingId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, listing::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedListing listing =
                new JsonAdaptedListing(VALID_ID, INVALID_ADDRESS, VALID_NAME, VALID_ASKING_PRICE, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, listing::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedListing listing = new JsonAdaptedListing(VALID_ID, null, VALID_ADDRESS,
                VALID_ASKING_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, listing::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedListing listing =
                new JsonAdaptedListing(VALID_ID, VALID_ADDRESS, INVALID_NAME, VALID_ASKING_PRICE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, listing::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedListing listing = new JsonAdaptedListing(VALID_ID, VALID_ADDRESS, null,
                VALID_ASKING_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, listing::toModelType);
    }

    @Test
    public void toModelType_invalidAskingPrice_throwsIllegalValueException() {
        JsonAdaptedListing listing =
                new JsonAdaptedListing(VALID_ID, VALID_ADDRESS, VALID_NAME, INVALID_ASKING_PRICE, VALID_TAGS);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, listing::toModelType);
    }

    @Test
    public void toModelType_nullAskingPrice_throwsIllegalValueException() {
        JsonAdaptedListing listing = new JsonAdaptedListing(VALID_ID, VALID_ADDRESS,
                VALID_NAME, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, listing::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedListing listing =
                new JsonAdaptedListing(VALID_ID, VALID_ADDRESS, VALID_NAME, VALID_ASKING_PRICE, invalidTags);
        assertThrows(IllegalValueException.class, listing::toModelType);
    }

}
