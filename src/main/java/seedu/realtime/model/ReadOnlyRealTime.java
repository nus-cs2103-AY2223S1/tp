package seedu.realtime.model;

import javafx.collections.ObservableList;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.meeting.Meeting;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.person.Client;
import seedu.realtime.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyRealTime {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Client> getClientList();

    /**
     * Returns an unmodifiable view of the listings list.
     * This list will not contain any duplicate listings.
     */
    ObservableList<Listing> getListingList();

    /**
     * Returns an unmodifiable view of the offers list.
     * This list will not contain any duplicate offers.
     */
    ObservableList<Offer> getOfferList();

    /**
     * Returns an unmodifaibale view of the meetings list.
     * This list will not contain any duplicate meetings.
     */
    ObservableList<Meeting> getMeetingList();
}
