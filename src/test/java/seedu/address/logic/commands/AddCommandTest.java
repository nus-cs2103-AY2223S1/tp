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
import seedu.address.model.ReadOnlyTaskPanel;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.task.Task;
import seedu.address.model.teammate.Teammate;
import seedu.address.testutil.TeammateBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullTeammate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_teammateAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTeammateAdded modelStub = new ModelStubAcceptingTeammateAdded();
        Teammate validTeammate = new TeammateBuilder().build();

        CommandResult commandResult = new AddCommand(validTeammate).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validTeammate), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTeammate), modelStub.teammatesAdded);
    }

    @Test
    public void execute_duplicateTeammate_throwsCommandException() {
        Teammate validTeammate = new TeammateBuilder().build();
        AddCommand addCommand = new AddCommand(validTeammate);
        ModelStub modelStub = new ModelStubWithTeammate(validTeammate);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_TEAMMATE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Teammate alice = new TeammateBuilder().withName("Alice").build();
        Teammate bob = new TeammateBuilder().withName("Bob").build();
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

        // different teammate -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all the methods failing.
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
        public void addTeammate(Teammate teammate) {
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
        public boolean hasTeammate(Teammate teammate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTeammate(Teammate target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTeammate(Teammate target, Teammate editedTeammate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Teammate> getFilteredTeammateList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTeammateList(Predicate<Teammate> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFilteredTaskList(ObservableList<Task> newTasks) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskPanel getTaskPanel() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskPanel(ReadOnlyTaskPanel taskPanel) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task deletedTask) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single teammate.
     */
    private class ModelStubWithTeammate extends ModelStub {
        private final Teammate teammate;

        ModelStubWithTeammate(Teammate teammate) {
            requireNonNull(teammate);
            this.teammate = teammate;
        }

        @Override
        public boolean hasTeammate(Teammate teammate) {
            requireNonNull(teammate);
            return this.teammate.isSameTeammate(teammate);
        }
    }

    /**
     * A Model stub that always accept the teammate being added.
     */
    private class ModelStubAcceptingTeammateAdded extends ModelStub {
        final ArrayList<Teammate> teammatesAdded = new ArrayList<>();

        @Override
        public boolean hasTeammate(Teammate teammate) {
            requireNonNull(teammate);
            return teammatesAdded.stream().anyMatch(teammate::isSameTeammate);
        }

        @Override
        public void addTeammate(Teammate teammate) {
            requireNonNull(teammate);
            teammatesAdded.add(teammate);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
