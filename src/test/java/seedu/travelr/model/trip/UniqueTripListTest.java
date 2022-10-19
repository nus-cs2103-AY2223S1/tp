package seedu.travelr.model.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_EVENT_SIGHTSEEING;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_TITLE_GERMANY;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_TITLE_JAPAN;
import static seedu.travelr.testutil.Assert.assertThrows;
import static seedu.travelr.testutil.TypicalTrips.GERMANY;
import static seedu.travelr.testutil.TypicalTrips.JAPAN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.travelr.model.trip.exceptions.DuplicateTripException;
import seedu.travelr.model.trip.exceptions.TripNotFoundException;
import seedu.travelr.testutil.TripBuilder;

public class UniqueTripListTest {

    private final UniqueTripList uniqueTripList = new UniqueTripList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueTripList.contains(JAPAN));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueTripList.add(JAPAN);
        assertTrue(uniqueTripList.contains(JAPAN));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTripList.add(JAPAN);
        Trip editedJapanTrip = new TripBuilder(JAPAN).withTitle(VALID_TITLE_JAPAN).withEvents(VALID_EVENT_SIGHTSEEING)
                .build();
        assertTrue(uniqueTripList.contains(editedJapanTrip));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueTripList.add(JAPAN);
        assertThrows(DuplicateTripException.class, () -> uniqueTripList.add(JAPAN));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrip(null, JAPAN));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrip(JAPAN, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(TripNotFoundException.class, () -> uniqueTripList.setTrip(JAPAN, JAPAN));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueTripList.add(JAPAN);
        uniqueTripList.setTrip(JAPAN, JAPAN);
        UniqueTripList expectedUniquePersonList = new UniqueTripList();
        expectedUniquePersonList.add(JAPAN);
        assertEquals(expectedUniquePersonList, uniqueTripList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueTripList.add(JAPAN);
        Trip editedJapanTrip = new TripBuilder(JAPAN).withTitle(VALID_TITLE_GERMANY).withEvents(VALID_EVENT_SIGHTSEEING)
                .build();
        uniqueTripList.setTrip(JAPAN, editedJapanTrip);
        UniqueTripList expectedUniquePersonList = new UniqueTripList();
        expectedUniquePersonList.add(editedJapanTrip);
        assertEquals(expectedUniquePersonList, uniqueTripList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueTripList.add(JAPAN);
        uniqueTripList.setTrip(JAPAN, GERMANY);
        UniqueTripList expectedUniquePersonList = new UniqueTripList();
        expectedUniquePersonList.add(GERMANY);
        assertEquals(expectedUniquePersonList, uniqueTripList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueTripList.add(JAPAN);
        uniqueTripList.add(GERMANY);
        assertThrows(DuplicateTripException.class, () -> uniqueTripList.setTrip(JAPAN, GERMANY));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(TripNotFoundException.class, () -> uniqueTripList.remove(JAPAN));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueTripList.add(JAPAN);
        uniqueTripList.remove(JAPAN);
        UniqueTripList expectedUniquePersonList = new UniqueTripList();
        assertEquals(expectedUniquePersonList, uniqueTripList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrips((UniqueTripList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueTripList.add(JAPAN);
        UniqueTripList expectedUniquePersonList = new UniqueTripList();
        expectedUniquePersonList.add(GERMANY);
        uniqueTripList.setTrips(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniqueTripList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrips((List<Trip>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueTripList.add(JAPAN);
        List<Trip> personList = Collections.singletonList(GERMANY);
        uniqueTripList.setTrips(personList);
        UniqueTripList expectedUniquePersonList = new UniqueTripList();
        expectedUniquePersonList.add(GERMANY);
        assertEquals(expectedUniquePersonList, uniqueTripList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Trip> listWithDuplicatePersons = Arrays.asList(JAPAN, JAPAN);
        assertThrows(DuplicateTripException.class, () -> uniqueTripList.setTrips(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTripList.asUnmodifiableObservableList().remove(0));
    }
}
