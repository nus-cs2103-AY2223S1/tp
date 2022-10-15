package seedu.waddle.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_WINTER;
import static seedu.waddle.testutil.Assert.assertThrows;
import static seedu.waddle.testutil.TypicalItineraries.SUMMER;
import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.exceptions.DuplicateItineraryException;
import seedu.waddle.testutil.ItineraryBuilder;


public class WaddleTest {

    private final Waddle waddle = new Waddle();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), waddle.getItineraryList());}

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> waddle.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWaddle_replacesData() {
        Waddle newData = getTypicalWaddle();
        waddle.resetData(newData);
        assertEquals(newData, waddle);
    }

    @Test
    public void resetData_withDuplicateItineraries_throwsDuplicateItineraryException() {
        Itinerary editedSummer = new ItineraryBuilder(SUMMER).withCountry(VALID_COUNTRY_WINTER)
                .withPeople(VALID_PEOPLE_WINTER).build();
        List<Itinerary> newItineraries = Arrays.asList(SUMMER, editedSummer);
        WaddleStub newData = new WaddleStub(newItineraries);

        assertThrows(DuplicateItineraryException.class, () -> waddle.resetData(newData));
    }

    @Test
    public void hasItinerary_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> waddle.hasItinerary(null));
    }

    @Test
    public void hasItinerary_itineraryNotInWaddle_returnsFalse() {
        assertFalse(waddle.hasItinerary(SUMMER));
    }

    @Test
    public void hasItinerary_itineraryInWaddle_returnsTrue() {
        waddle.addItinerary(SUMMER);
        assertTrue(waddle.hasItinerary(SUMMER));
    }

    @Test
    public void hasItinerary_itineraryWithSameIdentityFieldsInWaddle_returnsTrue() {
        waddle.addItinerary(SUMMER);
        Itinerary editedSummer = new ItineraryBuilder(SUMMER).withCountry(VALID_COUNTRY_WINTER)
                .withPeople(VALID_PEOPLE_WINTER).build();
        assertTrue(waddle.hasItinerary(editedSummer));
    }

    @Test
    public void getItineraryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> waddle.getItineraryList().remove(0));
    }

    /**
     * A stub ReadOnlyWaddle whose itinerary list can violate interface constraints.
     */
    private static class WaddleStub implements ReadOnlyWaddle {
        private final ObservableList<Itinerary> itineraries = FXCollections.observableArrayList();

        WaddleStub(Collection<Itinerary> itineraries) {
            this.itineraries.setAll(itineraries);
        }

        @Override
        public ObservableList<Itinerary> getItineraryList() {
            return itineraries;
        }
    }

}
