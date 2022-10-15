package seedu.waddle.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_WINTER;
import static seedu.waddle.testutil.Assert.assertThrows;
import static seedu.waddle.testutil.TypicalItineraries.SUMMER;
import static seedu.waddle.testutil.TypicalItineraries.WINTER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.waddle.model.itinerary.exceptions.DuplicateItineraryException;
import seedu.waddle.model.itinerary.exceptions.ItineraryNotFoundException;
import seedu.waddle.testutil.ItineraryBuilder;

public class UniqueItineraryListTest {

    private final UniqueItineraryList uniqueItineraryList = new UniqueItineraryList();

    @Test
    public void contains_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.contains(null));
    }

    @Test
    public void contains_itineraryNotInList_returnsFalse() {
        assertFalse(uniqueItineraryList.contains(SUMMER));
    }

    @Test
    public void contains_itineraryInList_returnsTrue() {
        uniqueItineraryList.add(SUMMER);
        assertTrue(uniqueItineraryList.contains(SUMMER));
    }

    @Test
    public void contains_itineraryWithSameIdentityFieldsInList_returnsTrue() {
        uniqueItineraryList.add(SUMMER);
        Itinerary editedSummer = new ItineraryBuilder(SUMMER).withCountry(VALID_COUNTRY_WINTER)
                .withPeople(VALID_PEOPLE_WINTER).build();
        assertTrue(uniqueItineraryList.contains(editedSummer));
    }

    @Test
    public void add_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.add(null));
    }

    @Test
    public void add_duplicateItinerary_throwsDuplicateItineraryException() {
        uniqueItineraryList.add(SUMMER);
        assertThrows(DuplicateItineraryException.class, () -> uniqueItineraryList.add(SUMMER));
    }

    @Test
    public void setItinerary_nullTargetItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.setItinerary(null, SUMMER));
    }

    @Test
    public void setItinerary_nullEditedItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.setItinerary(SUMMER, null));
    }

    @Test
    public void setItinerary_targetItineraryNotInList_throwsItineraryNotFoundException() {
        assertThrows(ItineraryNotFoundException.class, () -> uniqueItineraryList.setItinerary(SUMMER, SUMMER));
    }

    @Test
    public void setItinerary_editedItineraryIsSameItinerary_success() {
        uniqueItineraryList.add(SUMMER);
        uniqueItineraryList.setItinerary(SUMMER, SUMMER);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(SUMMER);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItinerary_editedItineraryHasSameIdentity_success() {
        uniqueItineraryList.add(SUMMER);
        Itinerary editedSummer = new ItineraryBuilder(SUMMER).withCountry(VALID_COUNTRY_WINTER)
                .withPeople(VALID_PEOPLE_WINTER).build();
        uniqueItineraryList.setItinerary(SUMMER, editedSummer);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(editedSummer);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItinerary_editedItineraryHasDifferentIdentity_success() {
        uniqueItineraryList.add(SUMMER);
        uniqueItineraryList.setItinerary(SUMMER, WINTER);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(WINTER);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItinerary_editedItineraryHasNonUniqueIdentity_throwsDuplicateItineraryException() {
        uniqueItineraryList.add(SUMMER);
        uniqueItineraryList.add(WINTER);
        assertThrows(DuplicateItineraryException.class, () -> uniqueItineraryList.setItinerary(SUMMER, WINTER));
    }

    @Test
    public void remove_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.remove(null));
    }

    @Test
    public void remove_itineraryDoesNotExist_throwsItineraryNotFoundException() {
        assertThrows(ItineraryNotFoundException.class, () -> uniqueItineraryList.remove(SUMMER));
    }

    @Test
    public void remove_existingItinerary_removesItinerary() {
        uniqueItineraryList.add(SUMMER);
        uniqueItineraryList.remove(SUMMER);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItinerary_nullUniqueItineraryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.setItineraries((UniqueItineraryList) null));
    }

    @Test
    public void setItinerary_uniqueItineraryList_replacesOwnListWithProvidedUniqueItineraryList() {
        uniqueItineraryList.add(SUMMER);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(WINTER);
        uniqueItineraryList.setItineraries(expectedUniqueItineraryList);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItinerary_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.setItineraries((List<Itinerary>) null));
    }

    @Test
    public void setItinerary_list_replacesOwnListWithProvidedList() {
        uniqueItineraryList.add(SUMMER);
        List<Itinerary> itineraryList = Collections.singletonList(WINTER);
        uniqueItineraryList.setItineraries(itineraryList);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(WINTER);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItinerary_listWithDuplicateItinerary_throwsDuplicateItineraryException() {
        List<Itinerary> listWithDuplicateItinerary = Arrays.asList(SUMMER, SUMMER);
        assertThrows(DuplicateItineraryException.class,
                () -> uniqueItineraryList.setItineraries(listWithDuplicateItinerary));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueItineraryList.asUnmodifiableObservableList().remove(0));
    }
}
