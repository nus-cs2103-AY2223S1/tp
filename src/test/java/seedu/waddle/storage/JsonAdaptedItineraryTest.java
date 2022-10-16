package seedu.waddle.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.waddle.storage.JsonAdaptedItinerary.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.waddle.testutil.Assert.assertThrows;
import static seedu.waddle.testutil.TypicalItineraries.SUMMER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.UniqueItemList;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Name;
import seedu.waddle.model.itinerary.People;

public class JsonAdaptedItineraryTest {
    private static final String INVALID_NAME = "S@mmer Trip";
    private static final String INVALID_COUNTRY = "Austr@lia";
    private static final String INVALID_START_DATE = "1-2-3";
    private static final String INVALID_END_DATE = "1-2-3";
    private static final String INVALID_PEOPLE = "three";
    private static final Item INVALID_ITEM = new Item(null);

    private static final String VALID_NAME = SUMMER.getName().toString();
    private static final String VALID_COUNTRY = SUMMER.getCountry().toString();
    private static final String VALID_START_DATE = SUMMER.getStartDate().toString();
    private static final String VALID_END_DATE = SUMMER.getEndDate().toString();
    private static final String VALID_PEOPLE = SUMMER.getPeople().toString();
    private final List<JsonAdaptedItem> VALID_EMPTY_ITEM_LIST = new ArrayList<>();

    /*
    TODO: Make non-empty item list
    private final List<JsonAdaptedItem> VALID_ITEM_LIST = new ArrayList<>();
     */

    @Test
    public void toModelType_validItineraryDetails_returnsItinerary() throws Exception {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(SUMMER);
        assertEquals(SUMMER, itinerary.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary =
                new JsonAdaptedItinerary(INVALID_NAME, VALID_COUNTRY, VALID_START_DATE, VALID_END_DATE, VALID_PEOPLE, VALID_EMPTY_ITEM_LIST);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(null, VALID_COUNTRY, VALID_START_DATE, VALID_END_DATE, VALID_PEOPLE, VALID_EMPTY_ITEM_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_invalidCountry_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary =
                new JsonAdaptedItinerary(VALID_NAME, INVALID_COUNTRY, VALID_START_DATE, VALID_END_DATE, VALID_PEOPLE, VALID_EMPTY_ITEM_LIST);
        String expectedMessage = Country.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_nullCountry_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(VALID_NAME, null, VALID_START_DATE, VALID_END_DATE, VALID_PEOPLE, VALID_EMPTY_ITEM_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Country.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_invalidStartDate_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary =
                new JsonAdaptedItinerary(VALID_NAME, VALID_COUNTRY, INVALID_START_DATE, VALID_END_DATE, VALID_PEOPLE, VALID_EMPTY_ITEM_LIST);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(VALID_NAME, VALID_COUNTRY, null, VALID_END_DATE, VALID_PEOPLE, VALID_EMPTY_ITEM_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_invalidEndDate_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary =
                new JsonAdaptedItinerary(VALID_NAME, VALID_COUNTRY, VALID_START_DATE, INVALID_END_DATE, VALID_PEOPLE, VALID_EMPTY_ITEM_LIST);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(VALID_NAME, VALID_COUNTRY, VALID_START_DATE, null, VALID_PEOPLE, VALID_EMPTY_ITEM_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_invalidPeople_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary =
                new JsonAdaptedItinerary(VALID_NAME, VALID_COUNTRY, VALID_START_DATE, VALID_END_DATE, INVALID_PEOPLE, VALID_EMPTY_ITEM_LIST);
        String expectedMessage = People.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_nullPeople_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(VALID_NAME, VALID_COUNTRY, VALID_START_DATE, VALID_END_DATE, null, VALID_EMPTY_ITEM_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, People.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_invalidItemList_throwsIllegalValueException() {
        List<JsonAdaptedItem> invalidItemList = new ArrayList<>();
        invalidItemList.add(new JsonAdaptedItem(INVALID_ITEM));
        JsonAdaptedItinerary itinerary =
                new JsonAdaptedItinerary(VALID_NAME, VALID_COUNTRY, VALID_START_DATE, VALID_END_DATE, VALID_PEOPLE, invalidItemList);
        assertThrows(IllegalValueException.class, itinerary::toModelType);
    }

    /*
    TODO: Test for an invalid none-empty item list.
    @Test
    public void toModelType_invalidItemList_throwsIllegalValueException() {
        List<JsonAdaptedItem> invalidItemList = new ArrayList<>();
        invalidItemList.add(new JsonAdaptedItem(INVALID_ITEM));
        JsonAdaptedItinerary itinerary =
                new JsonAdaptedItinerary(VALID_NAME, VALID_COUNTRY, VALID_START_DATE, VALID_END_DATE, VALID_PEOPLE, invalidItemList);
        assertThrows(IllegalValueException.class, itinerary::toModelType);
    }
     */

}
