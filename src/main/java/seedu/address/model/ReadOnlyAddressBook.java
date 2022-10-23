package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.listing.Listing;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.offer.Offer;
import seedu.address.model.person.Client;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

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
