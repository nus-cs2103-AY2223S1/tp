package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ELEMENT;
import static seedu.address.testutil.TypicalMeetings.MEETING1;
import static seedu.address.testutil.TypicalMeetings.getTypicalMyInsuRec;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.MyInsuRec;
import seedu.address.model.ReadOnlyMyInsuRec;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.UniqueClientList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.product.Product;
import seedu.address.testutil.MeetingBuilder;

public class AddMeetingCommandTest {

    private Model model = new ModelManager(getTypicalMyInsuRec(), new UserPrefs());

    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null, null, null, null, null));
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Meeting meetingToAdd = MEETING1;
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FIRST_ELEMENT, meetingToAdd);

        assertCommandFailure(addMeetingCommand, model, addMeetingCommand.MESSAGE_CONFLICTING_MEETING);
    }

    @Test
    public void equals() {
        Meeting meeting1 = new MeetingBuilder().withDescription("meeting1").build();
        Meeting meeting2 = new MeetingBuilder().withDescription("meeting2").build();
        AddMeetingCommand addMeeting1Command = new AddMeetingCommand(INDEX_FIRST_ELEMENT, meeting1);
        AddMeetingCommand addMeeting2Command = new AddMeetingCommand(INDEX_FIRST_ELEMENT, meeting2);

        // same object -> returns true
        assertTrue(addMeeting1Command.equals(addMeeting1Command));

        // same values -> returns true
        AddMeetingCommand addAliceCommandCopy = new AddMeetingCommand(INDEX_FIRST_ELEMENT, meeting1);
        assertTrue(addMeeting1Command.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addMeeting1Command.equals(1));

        // null -> returns false
        assertFalse(addMeeting1Command.equals(null));

        // different Client -> returns false
        assertFalse(addMeeting1Command.equals(addMeeting2Command));
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
        public Path getMyInsuRecFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMyInsuRecFilePath(Path myInsuRecFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMyInsuRec(ReadOnlyMyInsuRec myInsuRec) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMyInsuRec getMyInsuRec() {
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
        public void setClient(Client target, Client editedClient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSpecificMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeeting(Meeting target, Meeting editedMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProduct(Product product) {
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

        @Override
        public ObservableList<Meeting> getFilteredMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getDetailedClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDetailedClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getDetailedMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDetailedMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Product> getFilteredProductList() {
            throw new AssertionError("This method should not be called.");
        }

        public void updateFilteredProductList(Predicate<Product> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the {@code Meeting} being added.
     */
    private class ModelStubAcceptingMeetingAdded extends AddMeetingCommandTest.ModelStub {
        final ArrayList<Meeting> meetings = new ArrayList<>();
        final UniqueClientList clients = new UniqueClientList();

        private ModelStubAcceptingMeetingAdded() {
            clients.add(ALICE);
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            return clients.asUnmodifiableObservableList();
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetings.stream().anyMatch(meeting::willConflict);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetings.add(meeting);
        }

        @Override
        public ReadOnlyMyInsuRec getMyInsuRec() {
            return new MyInsuRec();
        }
    }
}
