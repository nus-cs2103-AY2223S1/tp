package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
//import seedu.address.logic.commands.CommandResult;
//import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskPanel;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TaskPanel;
import seedu.address.model.teammate.Teammate;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    // TODO: Update Tests
    /*
    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = new AddTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validTask), modelStub.tasksAdded);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
        ModelStub modelStub = new ModelStubWithTask(validTask);

        assertThrows(
            CommandException.class,
            String.format(AddTaskCommand.MESSAGE_DUPLICATE_TASK, validTask.getTitle()), ()
                    -> addTaskCommand.execute(modelStub)
        );
    }
     */

    @Test
    public void equals() {
        Task taskOne = new TaskBuilder().withTitle("Task 1").build();
        Task taskTwo = new TaskBuilder().withTitle("Task 2").build();
        AddTaskCommand addTaskOneCommand = new AddTaskCommand(taskOne);
        AddTaskCommand addTaskTwoCommand = new AddTaskCommand(taskTwo);

        // same object -> returns true
        assertEquals(addTaskOneCommand, addTaskOneCommand);

        // same values -> returns true
        AddTaskCommand addTaskOneCommandCopy = new AddTaskCommand(taskOne);
        assertEquals(addTaskOneCommand, addTaskOneCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addTaskOneCommand);

        // null -> returns false
        assertNotEquals(null, addTaskOneCommand);

        // different person -> returns false
        assertNotEquals(addTaskOneCommand, addTaskTwoCommand);
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private static class ModelStub implements Model {
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
        public void addPerson(Teammate teammate) {
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
        public boolean hasPerson(Teammate teammate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Teammate target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Teammate target, Teammate editedTeammate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Teammate> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Teammate> predicate) {
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
     * A Model stub that contains a single task.
     */
    private static class ModelStubWithTask extends ModelStub {
        private final Task task;

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
        }

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return this.task.isSameTask(task);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private static class ModelStubAcceptingTaskAdded extends ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasksAdded.stream().anyMatch(task::isSameTask);
        }

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public ReadOnlyTaskPanel getTaskPanel() {
            return new TaskPanel();
        }
    }

}
