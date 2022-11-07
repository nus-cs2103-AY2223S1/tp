package seedu.realtime.model;

import static java.util.Objects.requireNonNull;
import static seedu.realtime.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.realtime.commons.core.GuiSettings;
import seedu.realtime.commons.core.LogsCenter;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.meeting.Meeting;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.person.Address;
import seedu.realtime.model.person.Client;
import seedu.realtime.model.person.Name;
import seedu.realtime.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final RealTime realTime;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Listing> filteredListings;
    private final FilteredList<Offer> filteredOffers;
    private final FilteredList<Meeting> filteredMeetings;

    /**
     * Initializes a ModelManager with the given realTime and userPrefs.
     */
    public ModelManager(ReadOnlyRealTime realTime, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(realTime, userPrefs);

        logger.fine("Initializing with address book: " + realTime + " and user prefs " + userPrefs);

        this.realTime = new RealTime(realTime);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredClients = new FilteredList<>(this.realTime.getClientList());
        filteredPersons = new FilteredList<>(this.realTime.getPersonList());
        filteredListings = new FilteredList<>(this.realTime.getListingList());
        filteredOffers = new FilteredList<>(this.realTime.getOfferList());
        filteredMeetings = new FilteredList<>(this.realTime.getMeetingList());
    }

    public ModelManager() {
        this(new RealTime(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getRealTimeFilePath() {
        return userPrefs.getRealTimeFilePath();
    }

    @Override
    public void setRealTimeFilePath(Path realTimeFilePath) {
        requireNonNull(realTimeFilePath);
        userPrefs.setRealTimeFilePath(realTimeFilePath);
    }

    //=========== RealTime ================================================================================

    @Override
    public void setRealTime(ReadOnlyRealTime realTime) {
        this.realTime.resetData(realTime);
    }

    @Override
    public ReadOnlyRealTime getRealTime() {
        return realTime;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return realTime.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        realTime.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        realTime.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public Person getPerson(Name name) {
        return realTime.getPerson(name);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        realTime.setPerson(target, editedPerson);
    }


    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return realTime.hasClient(client);
    }

    @Override
    public void deleteClient(Client target) {
        realTime.removeClient(target);
    }

    @Override
    public void deleteListingsOwnedBy(Client target) {
        realTime.removeAllListingOwnedBy(target);
    }

    @Override
    public void deleteOffersMadeBy(Client target) {
        realTime.removeAllOffersMadeBy(target);
    }

    @Override
    public void deleteMeetingsWith(Client target) {
        realTime.removeAllMeetingsWith(target);
    }

    @Override
    public void addClient(Client client) {
        realTime.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public Client getClient(Name name) {
        return realTime.getClient(name);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        realTime.setClient(target, editedClient);
    }


    @Override
    public boolean hasListing(Listing listing) {
        requireNonNull(listing);
        return realTime.hasListing(listing);
    }

    @Override
    public void deleteListing(Listing target) {
        realTime.removeListing(target);
    }

    @Override
    public void addListing(Listing listing) {
        realTime.addListing(listing);
        updateFilteredListingList(PREDICATE_SHOW_ALL_LISTINGS);
    }

    /**
     * Gets the listing with the given id {@code id}.
     * @param id id of the listing
     * @return listing with given id
     */
    public Listing getListing(ListingId id) {
        return realTime.getListing(id);
    }

    @Override
    public void setListing(Listing target, Listing editedListing) {
        requireAllNonNull(target, editedListing);
        realTime.setListing(target, editedListing);
    }

    @Override
    public void deleteOffersFor(Listing target) {
        realTime.removeAllOffersFor(target);
    }

    @Override
    public void deleteMeetingsAbout(Listing target) {
        realTime.removeAllMeetingsAbout(target);
    }

    @Override
    public boolean hasOffer(Offer offer) {
        requireNonNull(offer);
        return realTime.hasOffer(offer);
    }

    @Override
    public void deleteOffer(Offer target) {
        realTime.removeOffer(target);
    }

    @Override
    public void addOffer(Offer offer) {
        realTime.addOffer(offer);
        updateFilteredOfferList(PREDICATE_SHOW_ALL_OFFERS);
    }

    @Override
    public Offer getOffer(Name name, Address address) {
        return realTime.getOffer(name, address);
    }

    @Override
    public void setOffer(Offer target, Offer editedOffer) {
        requireAllNonNull(target, editedOffer);

        realTime.setOffer(target, editedOffer);
    }

    @Override
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return realTime.hasMeeting(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        realTime.removeMeeting(meeting);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        realTime.addMeeting(meeting);
        updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
    }

    @Override
    public Meeting getMeeting(Name name, Address address) {
        return realTime.getMeeting(name, address);
    }

    @Override
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        realTime.setMeeting(target, editedMeeting);
    }

    //=========== Filtered List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedRealTime}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }


    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    @Override
    public ObservableList<Listing> getFilteredListingList() {
        return filteredListings;
    }

    @Override
    public void updateFilteredListingList(Predicate<Listing> predicate) {
        requireNonNull(predicate);
        filteredListings.setPredicate(predicate);
    }

    @Override
    public ObservableList<Offer> getFilteredOfferList() {
        return filteredOffers;
    }

    @Override
    public void updateFilteredOfferList(Predicate<Offer> predicate) {
        requireNonNull(predicate);
        filteredOffers.setPredicate(predicate);
    }

    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return filteredMeetings;
    }

    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
        requireNonNull(predicate);
        filteredMeetings.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return realTime.equals(other.realTime)
                && userPrefs.equals(other.userPrefs)
                && filteredClients.equals(other.filteredClients)
                && filteredListings.equals(other.filteredListings)
                && filteredOffers.equals(other.filteredOffers)
                && filteredMeetings.equals(other.filteredMeetings);
    }
}
