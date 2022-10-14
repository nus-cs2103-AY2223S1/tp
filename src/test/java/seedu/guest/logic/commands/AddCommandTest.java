package seedu.guest.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.guest.commons.core.GuiSettings;
import seedu.guest.logic.commands.exceptions.CommandException;
import seedu.guest.model.GuestBook;
import seedu.guest.model.Model;
import seedu.guest.model.ReadOnlyGuestBook;
import seedu.guest.model.ReadOnlyUserPrefs;
import seedu.guest.model.guest.Guest;
import seedu.guest.testutil.GuestBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_guestAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingGuestAdded modelStub = new ModelStubAcceptingGuestAdded();
        Guest validGuest = new GuestBuilder().build();


        CommandResult commandResult = new AddCommand(validGuest).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validGuest), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validGuest), modelStub.guestsAdded);
    }

    @Test
    public void execute_duplicateGuest_throwsCommandException() {
        Guest validGuest = new GuestBuilder().build();
        AddCommand addCommand = new AddCommand(validGuest);
        ModelStub modelStub = new ModelStubWithGuest(validGuest);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_GUEST, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Guest alice = new GuestBuilder().withName("Alice").build();
        Guest bob = new GuestBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different guest -> returns false
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
        public Path getGuestBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuestBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGuest(Guest guest) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuestBook(ReadOnlyGuestBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyGuestBook getGuestBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGuest(Guest guest) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGuest(Guest target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuest(Guest target, Guest editedGuest) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Guest> getFilteredGuestList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGuestList(Predicate<Guest> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single guest.
     */
    private class ModelStubWithGuest extends ModelStub {
        private final Guest guest;

        ModelStubWithGuest(Guest guest) {
            requireNonNull(guest);
            this.guest = guest;
        }

        @Override
        public boolean hasGuest(Guest guest) {
            requireNonNull(guest);
            return this.guest.isSameGuest(guest);
        }
    }

    /**
     * A Model stub that always accept the guest being added.
     */
    private class ModelStubAcceptingGuestAdded extends ModelStub {
        final ArrayList<Guest> guestsAdded = new ArrayList<>();

        @Override
        public boolean hasGuest(Guest guest) {
            requireNonNull(guest);
            return guestsAdded.stream().anyMatch(guest::isSameGuest);
        }

        @Override
        public void addGuest(Guest guest) {
            requireNonNull(guest);
            guestsAdded.add(guest);
        }

        @Override
        public ReadOnlyGuestBook getGuestBook() {
            return new GuestBook();
        }
    }

}
