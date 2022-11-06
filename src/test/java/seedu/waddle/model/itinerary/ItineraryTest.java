package seedu.waddle.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITINERARY_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_DATE_WINTER;
import static seedu.waddle.testutil.TypicalItineraries.SUMMER;
import static seedu.waddle.testutil.TypicalItineraries.WINTER;

import org.junit.jupiter.api.Test;

import seedu.waddle.testutil.ItineraryBuilder;

public class ItineraryTest {

    @Test
    public void isSameItinerary() {
        // same object -> returns true
        assertTrue(SUMMER.isSameItinerary(SUMMER));

        // null -> returns false
        assertFalse(SUMMER.isSameItinerary(null));

        // same name, all other attributes different -> returns true
        Itinerary editedSummer = new ItineraryBuilder(SUMMER)
                .withCountry(VALID_COUNTRY_WINTER).withStartDate(VALID_START_DATE_WINTER)
                .withDuration(VALID_DURATION_WINTER).withPeople(VALID_PEOPLE_WINTER).build();
        assertTrue(SUMMER.isSameItinerary(editedSummer));

        // different name, all other attributes same -> returns false
        editedSummer = new ItineraryBuilder(SUMMER).withName(VALID_ITINERARY_DESC_WINTER).build();
        assertFalse(SUMMER.isSameItinerary(editedSummer));

        // name differs in case, all other attributes same -> returns false
        Itinerary editedWinter = new ItineraryBuilder(WINTER).withName(VALID_ITINERARY_DESC_WINTER
                .toLowerCase()).build();
        assertFalse(WINTER.isSameItinerary(editedWinter));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_ITINERARY_DESC_WINTER + " ";
        editedWinter = new ItineraryBuilder(WINTER).withName(nameWithTrailingSpaces).build();
        assertFalse(WINTER.isSameItinerary(editedWinter));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Itinerary summerCopy = new ItineraryBuilder(SUMMER).build();
        assertTrue(SUMMER.equals(summerCopy));

        // same object -> returns true
        assertTrue(SUMMER.equals(SUMMER));

        // null -> returns false
        assertFalse(SUMMER.equals(null));

        // different type -> returns false
        assertFalse(SUMMER.equals(5));

        // different itinerary -> returns false
        assertFalse(SUMMER.equals(WINTER));

        // different name -> returns false
        Itinerary editedSummer = new ItineraryBuilder(SUMMER).withName(VALID_ITINERARY_DESC_WINTER).build();
        assertFalse(SUMMER.equals(editedSummer));

        // different country -> returns false
        editedSummer = new ItineraryBuilder(SUMMER).withCountry(VALID_COUNTRY_WINTER).build();
        assertFalse(SUMMER.equals(editedSummer));

        // different start date -> returns false
        editedSummer = new ItineraryBuilder(SUMMER).withStartDate(VALID_START_DATE_WINTER).build();
        assertFalse(SUMMER.equals(editedSummer));

        // different end date -> returns false
        editedSummer = new ItineraryBuilder(SUMMER).withDuration(VALID_DURATION_WINTER).build();
        assertFalse(SUMMER.equals(editedSummer));

        // different people -> returns false
        editedSummer = new ItineraryBuilder(SUMMER).withPeople(VALID_PEOPLE_WINTER).build();
        assertFalse(SUMMER.equals(editedSummer));
    }
}
