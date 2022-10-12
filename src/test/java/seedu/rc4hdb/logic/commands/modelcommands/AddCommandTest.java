package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.rc4hdb.commons.core.GuiSettings;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ReadOnlyUserPrefs;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.testutil.ResidentBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullResident_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_residentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingResidentAdded modelStub = new ModelStubAcceptingResidentAdded();
        Resident validResident = new ResidentBuilder().build();

        CommandResult commandResult = new AddCommand(validResident).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validResident), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validResident), modelStub.residentsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Resident validResident = new ResidentBuilder().build();
        AddCommand addCommand = new AddCommand(validResident);
        ModelStub modelStub = new ModelStubWithResident(validResident);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_RESIDENT, ()
                -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Resident alice = new ResidentBuilder().withName("Alice").build();
        Resident bob = new ResidentBuilder().withName("Bob").build();
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

        // different person -> returns false
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
        public Path getResidentBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setResidentBookFilePath(Path residentBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addResident(Resident resident) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setResidentBook(ReadOnlyResidentBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyResidentBook getResidentBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasResident(Resident resident) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteResident(Resident target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setResident(Resident target, Resident editedResident) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Resident> getFilteredResidentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredResidentList(Predicate<Resident> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<String> getObservableFields() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setObservableFields(List<String> observableFields) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single resident.
     */
    private class ModelStubWithResident extends ModelStub {
        private final Resident resident;

        ModelStubWithResident(Resident resident) {
            requireNonNull(resident);
            this.resident = resident;
        }

        @Override
        public boolean hasResident(Resident resident) {
            requireNonNull(resident);
            return this.resident.isSameResident(resident);
        }
    }

    /**
     * A Model stub that always accept the resident being added.
     */
    private class ModelStubAcceptingResidentAdded extends ModelStub {
        final ArrayList<Resident> residentsAdded = new ArrayList<>();

        @Override
        public boolean hasResident(Resident resident) {
            requireNonNull(resident);
            return residentsAdded.stream().anyMatch(resident::isSameResident);
        }

        @Override
        public void addResident(Resident resident) {
            requireNonNull(resident);
            residentsAdded.add(resident);
        }

        @Override
        public ReadOnlyResidentBook getResidentBook() {
            return new ResidentBook();
        }
    }

}
