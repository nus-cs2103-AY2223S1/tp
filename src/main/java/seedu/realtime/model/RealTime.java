package seedu.realtime.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.listing.UniqueListingList;
import seedu.realtime.model.meeting.Meeting;
import seedu.realtime.model.meeting.UniqueMeetingList;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.offer.UniqueOfferList;
import seedu.realtime.model.person.Address;
import seedu.realtime.model.person.Client;
import seedu.realtime.model.person.Name;
import seedu.realtime.model.person.Person;
import seedu.realtime.model.person.UniqueClientList;
import seedu.realtime.model.person.UniquePersonList;

/**
 * Wraps all data at the real-time level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class RealTime implements ReadOnlyRealTime {

    private final UniqueClientList clients;
    private final UniquePersonList persons;
    private final UniqueListingList listings;
    private final UniqueOfferList offers;
    private final UniqueMeetingList meetings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        clients = new UniqueClientList();
        listings = new UniqueListingList();
        offers = new UniqueOfferList();
        meetings = new UniqueMeetingList();
    }

    public RealTime() {}

    /**
     * Creates an RealTime using the Persons in the {@code toBeCopied}
     */
    public RealTime(ReadOnlyRealTime toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }


    /**
     * Replaces the contents of the client list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        this.clients.setClients(clients);
    }

    /**
     * Replaces the contents of the listing list with {@code listings}.
     * {@code listings} must not contain duplicate listings.
     */
    public void setListings(List<Listing> listings) {
        this.listings.setListings(listings);
    }

    /**
     * Replaces the contents of the offer list with {@code offers}.
     * {@code clients} must not contain duplicate offers.
     */
    public void setOffers(List<Offer> offers) {
        this.offers.setOffers(offers);
    }

    /**
     * Resets the existing data of this {@code RealTime} with {@code newData}.
     */
    public void resetData(ReadOnlyRealTime newData) {
        requireNonNull(newData);
        setOffers(newData.getOfferList());
        setClients(newData.getClientList());
        setPersons(newData.getPersonList());
        setListings(newData.getListingList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in realtime.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to realtime.
     * The person must not already exist in realtime.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Gets the person with the given name {@code name}.
     * @param name name of the person
     * @return person with given name
     */
    public Person getPerson(Name name) {
        return persons.getPerson(name);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in realtime.
     * The person identity of {@code editedPerson} must not be the same as another existing person in realtime.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code RealTime}.
     * {@code key} must exist in realtime.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes all listings owned by {@code key} from this {@code RealTime}
     */
    public void removeAllListingOwnedBy(Client key) {
        listings.deleteListingsOfClient(key);
    }

    public void removeAllOffersMadeBy(Client key) {
        offers.deleteOffersOfClient(key);
    }

    public void removeAllMeetingsWith(Client key) {
        meetings.deleteMeetingsWithClient(key);
    }


    //// client-level operations

    /**
     * Returns true if a client with the same identity as {@code client} exists in realtime.
     */
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clients.contains(client);
    }

    /**
     * Adds a client to realtime.
     * The client must not already exist in realtime.
     */
    public void addClient(Client p) {
        clients.add(p);
    }

    /**
     * Gets the vn with the given name {@code name}.
     * @param name name of the client
     * @return client with given name
     */
    public Client getClient(Name name) {
        return clients.getClient(name);
    }

    /**
     * Replaces the given client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in realtime.
     * The client identity of {@code editedClient} must not be the same as another existing client in realtime.
     */
    public void setClient(Client target, Client editedClient) {
        requireNonNull(editedClient);

        clients.setClient(target, editedClient);
    }

    /**
     * Removes {@code key} from this {@code RealTime}.
     * {@code key} must exist in realtime.
     */
    public void removeClient(Client key) {
        clients.remove(key);
    }

    //// listing-level operations

    /**
     * Returns true if a listing with the same identity as {@code listing} exists in realtime.
     */
    public boolean hasListing(Listing listing) {
        requireNonNull(listing);
        return listings.contains(listing);
    }

    /**
     * Adds a lsiting to realtime.
     * The listing must not already exist in realtime.
     */
    public void addListing(Listing l) {
        listings.add(l);
    }

    /**
     * Gets the listing with the given id {@code id}.
     * @param id id of the listing
     * @return listing with given id
     */
    public Listing getListing(ListingId id) {
        return listings.getListing(id);
    }

    /**
     * Replaces the given listing {@code target} in the list with {@code editedListing}.
     * {@code target} must exist in realtime.
     * The listing identity of {@code editedListing} must not be the same
     * as another existing listing in realtime.
     */
    public void setListing(Listing target, Listing editedListing) {
        requireNonNull(editedListing);
        listings.setListing(target, editedListing);
    }

    /**
     * Removes {@code listing} from this {@code RealTime}.
     * {@code listing} must exist in realtime.
     */
    public void removeListing(Listing listing) {
        listings.remove(listing);
    }

    public void removeAllOffersFor(Listing target) {
        offers.deleteOffersForListing(target);
    }

    public void removeAllMeetingsAbout(Listing target) {
        meetings.deleteMeetingsAboutListing(target);
    }

    //// offer-level operations

    /**
     * Returns true if an offer with the same identity as {@code offer} exists in realtime.
     */
    public boolean hasOffer(Offer offer) {
        requireNonNull(offer);
        return offers.contains(offer);
    }

    /**
     * Adds an offer to realtime.
     * The offer must not already exist in realtime.
     */
    public void addOffer(Offer o) {
        offers.add(o);
    }

    /**
     * Gets the offer from the given name {@code name} and listing address {@code address}.
     * @param name name of the person in offer
     * @param address listing address of offer
     * @return offer with given name and listing address
     */
    public Offer getOffer(Name name, Address address) {
        return offers.getOffer(name, address);
    }

    /**
     * Replaces the given offer {@code target} in the list with {@code editedOffer}.
     * {@code target} must exist in realtime.
     * The offer identity of {@code editedOffer} must not be the same as another existing offer in realtime.
     */
    public void setOffer(Offer target, Offer editedOffer) {
        requireNonNull(editedOffer);

        offers.setOffer(target, editedOffer);
    }

    /**
     * Removes {@code key} from this {@code RealTime}.
     * {@code key} must exist in realtime.
     */
    public void removeOffer(Offer key) {
        offers.remove(key);
    }

    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in realtime.
     */
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    /**
     * Removes {@code key} from this {@code RealTime}.
     * {@code key} must exist in realtime.
     */
    public void removeMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * Adds a meeting to realtime.
     * The meeting must not already exist in realtime.
     */
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    /**
     * Gets the meeting from the given name {@code name} and listing address {@code address}.
     * @param name name of the person in meeting
     * @param address listing address of meeting
     * @return meeting with given name and listing address
     */
    public Meeting getMeeting(Name name, Address address) {
        return meetings.getMeeting(name, address);
    }

    /**
     * Replaces the given meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in realtime.
     * The meeting identity of {@code editedMeeting} must not be the same as another existing offer in realtime.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireNonNull(editedMeeting);

        meetings.setMeeting(target, editedMeeting);
    }

    //// util methods

    @Override
    public String toString() {
        int clientListSize = clients.asUnmodifiableObservableList().size();
        int personListSize = persons.asUnmodifiableObservableList().size();
        int listingListSize = listings.asUnmodifiableObservableList().size();
        int offerListSize = offers.asUnmodifiableObservableList().size();
        return String.format("%d clients, %d listings, %d offers", clientListSize, listingListSize, offerListSize);
        // TODO: refine later
    }

    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Listing> getListingList() {
        return listings.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Offer> getOfferList() {
        return offers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RealTime) // instanceof handles nulls
                && (clients.equals(((RealTime) other).clients))
                && (listings.equals(((RealTime) other).listings))
                && (offers.equals(((RealTime) other).offers));
    }

    @Override
    public int hashCode() {
        return clients.hashCode();
    }
}
