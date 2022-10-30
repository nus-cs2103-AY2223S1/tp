package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
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
import seedu.address.testutil.ClientBuilder;

public class AddClientCommandTest {

    @Test
    public void constructor_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddClientCommand(null));
    }

    @Test
    public void execute_clientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingClientAdded modelStub = new ModelStubAcceptingClientAdded();
        Client validClient = new ClientBuilder().build();

        CommandResult commandResult = new AddClientCommand(validClient).execute(modelStub);

        assertEquals(String.format(AddClientCommand.MESSAGE_SUCCESS, validClient), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validClient), modelStub.clientsAdded);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client validClient = new ClientBuilder().build();
        AddClientCommand addClientCommand = new AddClientCommand(validClient);
        ModelStub modelStub = new ModelStubWithClient(validClient);

        assertThrows(CommandException.class,
                AddClientCommand.MESSAGE_DUPLICATE_CLIENT, (
                ) -> addClientCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Client alice = new ClientBuilder().withName("Alice").build();
        Client bob = new ClientBuilder().withName("Bob").build();
        AddClientCommand addAliceCommand = new AddClientCommand(alice);
        AddClientCommand addBobCommand = new AddClientCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddClientCommand addAliceCommandCopy = new AddClientCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different client -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
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
            throw new AssertionError("This method should not be called.");
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
            throw new AssertionError("This method should not be called.");
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
            throw new AssertionError("This method should not be called.");
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

    /**
     * A Model stub that contains a single client.
     */
    private class ModelStubWithClient extends ModelStub {
        private final Client client;

        ModelStubWithClient(Client client) {
            requireNonNull(client);
            this.client = client;
        }

        @Override
        public boolean hasClient(Client client) {
            requireNonNull(client);
            return this.client.isSameClient(client);
        }
    }

    /**
     * A Model stub that always accept the client being added.
     */
    private class ModelStubAcceptingClientAdded extends ModelStub {
        final ArrayList<Client> clientsAdded = new ArrayList<>();

        @Override
        public boolean hasClient(Client client) {
            requireNonNull(client);
            return clientsAdded.stream().anyMatch(client::isSameClient);
        }

        @Override
        public void addClient(Client client) {
            requireNonNull(client);
            clientsAdded.add(client);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
