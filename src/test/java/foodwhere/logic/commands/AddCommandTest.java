package foodwhere.logic.commands;

import static foodwhere.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import foodwhere.model.stall.Stall;
import org.junit.jupiter.api.Test;

import foodwhere.commons.core.GuiSettings;
import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.model.AddressBook;
import foodwhere.model.Model;
import foodwhere.model.ReadOnlyAddressBook;
import foodwhere.model.ReadOnlyUserPrefs;
import foodwhere.testutil.PersonBuilder;
import javafx.collections.ObservableList;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Stall validStall = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validStall).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validStall), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStall), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Stall validStall = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validStall);
        ModelStub modelStub = new ModelStubWithPerson(validStall);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Stall alice = new PersonBuilder().withName("Alice").build();
        Stall bob = new PersonBuilder().withName("Bob").build();
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

        // different stall -> returns false
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
        public void addPerson(Stall stall) {
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
        public boolean hasPerson(Stall stall) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Stall target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Stall target, Stall editedStall) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Stall> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Stall> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single stall.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Stall stall;

        ModelStubWithPerson(Stall stall) {
            requireNonNull(stall);
            this.stall = stall;
        }

        @Override
        public boolean hasPerson(Stall stall) {
            requireNonNull(stall);
            return this.stall.isSamePerson(stall);
        }
    }

    /**
     * A Model stub that always accept the stall being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Stall> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Stall stall) {
            requireNonNull(stall);
            return personsAdded.stream().anyMatch(stall::isSamePerson);
        }

        @Override
        public void addPerson(Stall stall) {
            requireNonNull(stall);
            personsAdded.add(stall);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
