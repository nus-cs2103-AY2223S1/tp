package seedu.realtime.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.realtime.commons.core.GuiSettings;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.meeting.Meeting;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.person.Address;
import seedu.realtime.model.person.Client;
import seedu.realtime.model.person.Name;
import seedu.realtime.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Listing> PREDICATE_SHOW_ALL_LISTINGS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Offer> PREDICATE_SHOW_ALL_OFFERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Meeting> PREDICATE_SHOW_ALL_MEETINGS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getRealTimeFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setRealTimeFilePath(Path realTimeFilePath);

    /**
     * Replaces address book data with the data in {@code realTime}.
     */
    void setRealTime(ReadOnlyRealTime realTime);

    /** Returns the RealTime */
    ReadOnlyRealTime getRealTime();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Gets the person with the given name {@code name}.
     * @param name name of the person
     * @return person with given name
     */
    Person getPerson(Name name);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a listing with the same identity as {@code listing} exists in the address book.
     */
    boolean hasListing(Listing listing);

    /**
     * Deletes the given listing.
     * The listing must exist in the address book.
     */
    void deleteListing(Listing target);

    /**
     * Adds the given listing.
     * {@code listing} must not already exist in the address book.
     */
    void addListing(Listing listing);

    /**
     * Gets the listing with the given id {@code id}.
     * @param id id of the listing
     * @return listing with given id
     */
    Listing getListing(ListingId id);

    /**
     * Replaces the given listing {@code target} with {@code editedListing}.
     * {@code target} must exist in the address book.
     * The listing identity of {@code editedListing} must not be the same
     * as another existing listing in the address book.
     */
    void setListing(Listing listing, Listing editedListing);

    /**
     * Deletes all offers for the specified listing.
     */
    void deleteOffersFor(Listing target);

    /**
     * Delete all meetings about the specified listing.
     */
    void deleteMeetingsAbout(Listing target);

    /** Returns an unmodifiable view of the filtered listings list */
    ObservableList<Listing> getFilteredListingList();

    /**
     * Updates the filter of the filtered listings list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredListingList(Predicate<Listing> predicate);

    /**
     * Returns true if an offer with the same identity as {@code offer} exists in the address book.
     */
    boolean hasOffer(Offer offer);

    /**
     * Deletes the given offer.
     * The offer must exist in the address book.
     */
    void deleteOffer(Offer target);

    /**
     * Adds the given offer.
     * {@code offer} must not already exist in the address book.
     */
    void addOffer(Offer offer);

    /**
     * Gets the offer from the given name {@code name} and listing address {@code address}.
     * @param name name of the client in offer
     * @param address listing address of offer
     * @return offer with given name and listing address
     */
    public Offer getOffer(Name name, Address address);

    /**
     * Replaces the given offer {@code target} with {@code editedOffer}.
     * {@code target} must exist in the address book.
     * The offer identity of {@code editedOffer} must not be the same as another existing offer in the address book.
     */
    void setOffer(Offer target, Offer editedOffer);

    /** Returns an unmodifiable view of the filtered offer list */
    ObservableList<Offer> getFilteredOfferList();

    /**
     * Updates the filter of the filtered offer list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOfferList(Predicate<Offer> predicate);

    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in the address book.
     */
    boolean hasMeeting(Meeting meeting);

    /**
     * Adds the given meeting.
     * {@code meeting} must not already exist in the address book.
     */
    void addMeeting(Meeting meeting);

    /**
     * Deletes given meeting.
     * {@code meeting} must not exist in the given address book.
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Gets the meeting from the given name {@code name} and listing address {@code address}.
     * @param name name of the client in meeting
     * @param address listing address of meeting
     * @return meeting with given name and listing address
     */
    public Meeting getMeeting(Name name, Address address);

    /**
     * Replaces the given meeting {@code target} with {@code editedMeeting}.
     * {@code target} must exist in the address book.
     * The meeting identity of {@code editedMeeting} must not be the same as another existing meeting in the address
     * book.
     */
    void setMeeting(Meeting target, Meeting editedMeeting);

    /** Returns an unmodifiable view of the filtered meeting list */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Updates the filter of the filtered meeting list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMeetingList(Predicate<Meeting> predicate);

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the given client.
     * The client must exist in the address book.
     */
    void deleteClient(Client target);

    /**
     * Deletes all the listings owned by the given client.
     */
    void deleteListingsOwnedBy(Client target);

    /**
     * Delete all the offers made by the given client.
     */
    void deleteOffersMadeBy(Client target);

    /**
     * Delete all the meetings with the given client.
     */
    void deleteMeetingsWith(Client target);
    /**
     * Adds the given client.
     * {@code client} must not already exist in the address book.
     */
    void addClient(Client client);

    /**
     * Gets the client with the given name {@code name}.
     * @param name name of the client
     * @return client with given name
     */
    Client getClient(Name name);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the address book.
     */
    void setClient(Client target, Client editedClient);

    /** Returns an unmodifiable view of the filtered client list */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);

}
