package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.ModuleBuilder;

/**
 * Unit testing for AddModuleCommandTest
 */
public class AddModuleCommandTest {
    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddModuleCommand(null));
    }

    @Test
    public void execute_stubAlreadyContainsModule_throwsCommandException() {
        Module module = new ModuleBuilder().build();
        AddModuleCommand addModuleCommand = new AddModuleCommand(module);
        StubAlreadyContainsModule modStub = new StubAlreadyContainsModule(module);
        assertThrows(CommandException.class, () -> addModuleCommand.execute(modStub));
    }

    @Test
    public void execute_stubCanTakeInModule_success() throws CommandException {
        StubCanTakeInModule stubCanTakeInModule = new StubCanTakeInModule();
        Module moduleBeingAdded = new ModuleBuilder().build();
        CommandResult commandResult = new AddModuleCommand(moduleBeingAdded)
                .execute(stubCanTakeInModule);
        assertEquals(AddModuleCommand.MODULE_ADDED_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(List.of(moduleBeingAdded), stubCanTakeInModule.modules);
    }

    @Test
    public void testEquals() {
        Module module = new ModuleBuilder().build();
        Module secondModule = new ModuleBuilder().withModuleCode("CS1101S").build();
        AddModuleCommand addModuleCommand = new AddModuleCommand(module);
        AddModuleCommand addModuleCommandCopy = new AddModuleCommand(module);
        AddModuleCommand differentAddModuleCommand = new AddModuleCommand(secondModule);

        //Same AddModuleCommand object (Itself)
        assertTrue(addModuleCommand.equals(addModuleCommand));

        //Different AddModuleCommand but same module code
        assertTrue(addModuleCommand.equals(addModuleCommandCopy));

        //Different AddModuleCommand objects but different module code
        assertFalse(addModuleCommand.equals(differentAddModuleCommand));

        //Different Object types
        assertFalse(addModuleCommand.equals(182817));

        //Null value
        assertFalse(addModuleCommand.equals(null));
    }

    private class ModuleModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This should never be called");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook addressBook) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This should never be called");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void addModule(Module module) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This should never be called");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public boolean hasTaskWithModule(Module module) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void replaceTask(Task target, Task editedTask, boolean isSameTask) throws DuplicateTaskException {
            throw new AssertionError("This should never be called");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This should never be called");
        }

        @Override
        public boolean hasModule(Module module) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void deleteModule(Module target) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void replaceModule(Module target, Module editedModule) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void sortTaskList(Criteria criteria) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void unlinkTasksFromExam(Exam exam) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public boolean hasExam(Exam exam) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void deleteExam(Exam target) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public boolean hasExamWithModule(Module module) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void addExam(Exam exam) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void replaceExam(Exam target, Exam editedExam, boolean isSameExam) throws DuplicateExamException {
            throw new AssertionError("This should never be called");
        }

        @Override
        public ObservableList<Exam> getFilteredExamList() {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void updateFilteredExamList(Predicate<Exam> predicate) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void updateExamFieldForTask(Exam previousExam, Exam newExam) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public boolean isExamLinkedToTask(Exam examToEdit) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void updateModuleFieldForTask(Module previousModule, Module newModule) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void updateModuleFieldForExam(Module previousModule, Module newModule) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void deleteTasksWithModule(Module module) {
            throw new AssertionError("This should never be called");
        }

        @Override
        public void deleteExamsWithModule(Module module) {
            throw new AssertionError("This should never be called");
        }
    }

    private class StubAlreadyContainsModule extends ModuleModelStub {
        private final Module stubModule;
        public StubAlreadyContainsModule(Module module) {
            requireNonNull(module);
            stubModule = module;
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return stubModule.isSameModule(module);
        }
    }

    private class StubCanTakeInModule extends ModuleModelStub {
        private final ArrayList<Module> modules = new ArrayList<>();

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return modules.stream().anyMatch(module::isSameModule);
        }

        @Override
        public void addModule(Module moduleAdded) {
            requireNonNull(moduleAdded);
            modules.add(moduleAdded);
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
        }
    }
}
