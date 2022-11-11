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
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TaskList;
import seedu.address.model.task.Task;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Task validTask = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Task validTask = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validTask);
        ModelStub modelStub = new ModelStubWithPerson(validTask);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Task alice = new PersonBuilder().withName("Alice").build();
        Task bob = new PersonBuilder().withName("Bob").build();
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

        // different task -> returns false
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
        public Path getArchivedTaskListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void archivedTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyTaskList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskList getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskList getArchivedTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTaskInArchives(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredArchivedTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getObservableArchivedTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public String getFilterStatus() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredArchivedTaskList(Predicate<Task> predicate) {

        }

        @Override
        public void setArchivedTaskList(ReadOnlyTaskList addressBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setArchivedTaskListFilePath(Path archivedTaskBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getArchivedTasks() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilterStatus(String filter) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void updateFilterStatus(String filter, boolean isNewFilterSet) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Task task;

        ModelStubWithPerson(Task task) {
            requireNonNull(task);
            this.task = task;
        }

        @Override
        public boolean hasPerson(Task task) {
            requireNonNull(task);
            return this.task.isSameTask(task);
        }
    }

    /**
     * A Model stub that always accept the task being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        private String filterStatus = "";

        @Override
        public boolean hasPerson(Task task) {
            requireNonNull(task);
            return tasksAdded.stream().anyMatch(task::isSameTask);
        }

        @Override
        public void addPerson(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public void updateFilterStatus(String filter) {
            requireNonNull(filter);
            if (this.filterStatus.equalsIgnoreCase("Showing all tasks") || this.filterStatus.equals("")) {
                this.filterStatus = filter;
            } else {
                this.filterStatus += ", " + filter;
            }
        }

        @Override
        public void updateFilterStatus(String filter, boolean isNewFilterSet) {
            requireNonNull(filter);
            if (isNewFilterSet) {
                this.filterStatus = filter;
            } else {
                this.filterStatus += ", " + filter;
            }
        }

        @Override
        public ReadOnlyTaskList getAddressBook() {
            return new TaskList();
        }
    }

}
