package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;
import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import modtrekt.commons.core.GuiSettings;
import modtrekt.model.Model;
import modtrekt.model.ReadOnlyModuleList;
import modtrekt.model.ReadOnlyTaskBook;
import modtrekt.model.ReadOnlyUserPrefs;
import modtrekt.model.TaskBook;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;
import modtrekt.model.task.Task;
import modtrekt.testutil.TaskBuilder;

public class AddTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    //    @Test
    //    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
    //        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
    //        Task validTask = new TaskBuilder().build();
    //
    //        CommandResult commandResult = new AddTaskCommand(validTask).execute(modelStub);
    //
    //        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
    //        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    //    }

    @Test
    public void equals() {
        Task t1 = new TaskBuilder().withDescription("Go to the gym").build();
        Task t2 = new TaskBuilder().withDescription("Attend lecture").build();
        AddTaskCommand addTask1Command = new AddTaskCommand(t1);
        AddTaskCommand addTask2Command = new AddTaskCommand(t2);

        // same object -> returns true
        assertTrue(addTask1Command.equals(addTask1Command));

        // same values -> returns true
        AddTaskCommand addTask1CommandCopy = new AddTaskCommand(t1);
        assertTrue(addTask1Command.equals(addTask1CommandCopy));

        // different types -> returns false
        assertFalse(addTask1Command.equals(1));

        // null -> returns false
        assertFalse(addTask1Command.equals(null));

        // different person -> returns false
        assertFalse(addTask1Command.equals(addTask2Command));
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
        public Path getModuleListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleListFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(Module person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleList(ReadOnlyModuleList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyModuleList getModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(Module person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModuleWithModCode(ModCode code) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateModuleRemoveTask(Task t) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateModuleAddTask(Task t) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTasksOfModule(Module target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(Module target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Module parseModuleFromCode(ModCode code) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModule(Module target, Module editedModule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTaskBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskBookFilePath(Path taskBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskBook(ReadOnlyTaskBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskBook getTaskBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentModule(ModCode code) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ModCode getCurrentModule() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithTask extends ModelStub {
        private final Task task;

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
        }
    }

    /**
     * A Model stub that always accept the task being added.
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public ReadOnlyTaskBook getTaskBook() {
            return new TaskBook();
        }
    }

}
