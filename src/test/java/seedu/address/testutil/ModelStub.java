package seedu.address.testutil;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.ListingId;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.offer.Offer;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addListing(Listing listing) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addOffer(Offer offer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Person getPerson(Name name) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Listing getListing(ListingId id) {
        return new ListingBuilder().build();
    }

    @Override
    public Offer getOffer(Name name, Address address) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasListing(Listing listing) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasOffer(Offer offer) {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteListingsOwnedBy(Client target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteMeetingsWith(Client target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteOffersMadeBy(Client target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteListing(Listing target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteOffer(Offer offer) {
        throw new AssertionError("This method shoud not be called.");
    }
    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setListing(Listing target, Listing editedListing) {
    }

    @Override
    public void deleteOffersFor(Listing target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteMeetingsAbout(Listing target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setOffer(Offer target, Offer editedOffer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Listing> getFilteredListingList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Offer> getFilteredOfferList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredListingList(Predicate<Listing> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredOfferList(Predicate<Offer> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasMeeting(Meeting meeting) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addMeeting(Meeting meeting) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Meeting getMeeting(Name name, Address address) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasClient(Client client) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteClient(Client target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addClient(Client client) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Client getClient(Name name) {
        return new ClientBuilder().build();
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
