package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_TASK;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_NOT_FOUND;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Criteria;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.exceptions.DuplicateExamException;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandTest {
    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelstub = new ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();
        CommandResult commandResult = new AddTaskCommand(validTask).execute(modelstub);
        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelstub.tasksAdded);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
        ModelStub modelStub = new ModelStubWithTask(validTask);
        assertThrows(CommandException.class, MESSAGE_DUPLICATE_TASK, () -> addTaskCommand.execute(modelStub));
    }

    @Test
    public void execute_moduleNotFound_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
        ModelStubWithNoModules modelStub = new ModelStubWithNoModules();
        assertThrows(CommandException.class, MESSAGE_MODULE_NOT_FOUND, () -> addTaskCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Task task1 = new TaskBuilder().withTaskDescription("Task 1").build();
        Task task2 = new TaskBuilder().withTaskDescription("Task 2").build();
        AddTaskCommand addTask1Command = new AddTaskCommand(task1);
        AddTaskCommand addTask2Command = new AddTaskCommand(task2);
        //same object -> returns true
        assertTrue(addTask1Command.equals(addTask1Command));
        //same values -> returns true
        AddTaskCommand addTask1CommandCopy = new AddTaskCommand(task1);
        assertTrue(addTask1Command.equals(addTask1CommandCopy));
        // different types -> returns false
        assertFalse(addTask1Command.equals(1));
        // null -> returns false
        assertFalse(addTask1Command.equals(null));
        // different task -> returns false
        assertFalse(addTask1Command.equals(addTask2Command));
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
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not even be called.");
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
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasModule(Module module) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortTaskList(Criteria criteria) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unlinkTasksFromExam(Exam exam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExam(Exam exam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExam(Exam target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExamWithModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExam(Exam exam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void replaceExam(Exam target, Exam editedExam, boolean isSameExam) throws DuplicateExamException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Exam> getFilteredExamList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExamList(Predicate<Exam> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateExamFieldForTask(Exam previousExam, Exam newExam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isExamLinkedToTask(Exam examToEdit) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateModuleFieldForTask(Module previousModule, Module newModule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateModuleFieldForExam(Module previousModule, Module newModule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTasksWithModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExamsWithModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
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
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(Module target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTaskWithModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void replaceTask(Task task, Task editedTask, boolean isSameTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void replaceModule(Module module, Module editedModule) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends ModelStub {
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

        @Override
        public boolean hasModule(Module module) {
            return true;
        }
    }

    /**
     * A Model stub that always accept the task being added.
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasksAdded.stream().anyMatch(task::isSameTask);
        }

        @Override
        public boolean hasModule(Module module) {
            return true;
        }

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }
    }

    /**
     * A Model stub with no modules.
     */
    private class ModelStubWithNoModules extends ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasksAdded.stream().anyMatch(task::isSameTask);
        }

        @Override
        public boolean hasModule(Module module) {
            return false;
        }
    }
}
