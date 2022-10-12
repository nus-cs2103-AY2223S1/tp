package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;
import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import modtrekt.commons.core.GuiSettings;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.model.Model;
import modtrekt.model.ModuleList;
import modtrekt.model.ReadOnlyModuleList;
import modtrekt.model.ReadOnlyTaskBook;
import modtrekt.model.ReadOnlyUserPrefs;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;
import modtrekt.model.task.Task;
import modtrekt.testutil.ModuleBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingModuleAdded modelStub = new ModelStubAcceptingModuleAdded();
        Module validModule = new ModuleBuilder().build();

        CommandResult commandResult = new AddCommand(validModule).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validModule), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validModule), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module validModule = new ModuleBuilder().build();
        AddCommand addCommand = new AddCommand(validModule);
        ModelStub modelStub = new ModelStubWithModule(validModule);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_MODULE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Module alice = new ModuleBuilder().withName("Alice").build();
        Module bob = new ModuleBuilder().withName("Bob").build();
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
        public void deleteModule(Module target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTasksOfModule(Module target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateModuleAddTask(Task t) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateModuleRemoveTask(Task t) {
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
    private class ModelStubWithModule extends ModelStub {
        private final Module person;

        ModelStubWithModule(Module person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasModule(Module person) {
            requireNonNull(person);
            return this.person.isSameModule(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingModuleAdded extends ModelStub {
        final ArrayList<Module> personsAdded = new ArrayList<>();

        @Override
        public boolean hasModule(Module person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSameModule);
        }

        @Override
        public boolean hasModuleWithModCode(ModCode code) {
            requireNonNull(code);
            return personsAdded.stream().anyMatch(x -> x.isSameCodeModule(code));
        }

        @Override
        public void addModule(Module person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyModuleList getModuleList() {
            return new ModuleList();
        }
    }

}
