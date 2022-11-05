package seedu.realtime.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.realtime.testutil.Assert.assertThrows;
import static seedu.realtime.testutil.TypicalPersons.ALICE;
import static seedu.realtime.testutil.TypicalPersons.getTypicalRealTime;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.meeting.Meeting;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.person.Client;
import seedu.realtime.model.person.Person;
import seedu.realtime.testutil.PersonBuilder;

public class RealTimeTest {

    private final RealTime realTime = new RealTime();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), realTime.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> realTime.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyRealTime_replacesData() {
        RealTime newData = getTypicalRealTime();
        realTime.resetData(newData);
        assertEquals(newData, realTime);
    }

    /*@Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Listing> newListings = Arrays.asList();
        List<Offer> newOffers = Arrays.asList();
        RealTimeStub newData = new RealTimeStub(newPersons, newListings, newOffers);

        assertThrows(DuplicatePersonException.class, () -> realTime.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateListings_throwsDuplicateListingException() {
        // Two Listings with the same identity fields
        Listing editedHouse = new ListingBuilder().build();
        List<Person> newPersons = Arrays.asList(ALICE);
        List<Listing> newListings = Arrays.asList(editedHouse, editedHouse);
        List<Offer> newOffers = Arrays.asList();
        RealTimeStub newData = new RealTimeStub(newPersons, newListings, newOffers);

        assertThrows(DuplicateListingException.class, () -> realTime.resetData(newData));
    }*/

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> realTime.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInRealTime_returnsFalse() {
        assertFalse(realTime.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInRealTime_returnsTrue() {
        realTime.addPerson(ALICE);
        assertTrue(realTime.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInRealTime_returnsTrue() {
        realTime.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(realTime.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> realTime.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyRealTime whose persons list can violate interface constraints.
     */
    private static class RealTimeStub implements ReadOnlyRealTime {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Listing> listings = FXCollections.observableArrayList();
        private final ObservableList<Offer> offers = FXCollections.observableArrayList();
        private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();

        RealTimeStub(Collection<Person> persons,
                     Collection<Listing> listings,
                     Collection<Offer> offers,
                     Collection<Meeting> meetings) {
            this.persons.setAll(persons);
            this.listings.setAll(listings);
            this.offers.setAll(offers);
            this.meetings.setAll(meetings);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Client> getClientList() {
            return null;
        }

        @Override
        public ObservableList<Listing> getListingList() {
            return listings;
        }

        @Override
        public ObservableList<Offer> getOfferList() {
            return offers;
        }

        @Override
        public ObservableList<Meeting> getMeetingList() {
            return meetings;
        }
    }
}
