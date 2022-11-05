package swift.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swift.commons.core.GuiSettings;
import swift.commons.core.index.Index;
import swift.logic.commands.exceptions.CommandException;
import swift.model.Model;
import swift.model.ReadOnlyAddressBook;
import swift.model.ReadOnlyUserPrefs;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.Person;
import swift.model.task.Task;
import swift.testutil.PersonBuilder;
import swift.testutil.TaskBuilder;

public class AddTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = new AddTaskCommand(validTask, new HashSet<>(Arrays.asList(Index.fromOneBased(1))))
                .execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
        assertEquals(Arrays.asList(new PersonTaskBridge(
                UUID.fromString(PersonBuilder.DEFAULT_UUID),
                modelStub.tasksAdded.get(0).getId())), modelStub.bridgesAdded);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        AddTaskCommand addCommand = new AddTaskCommand(validTask, new HashSet<>());
        ModelStub modelStub = new ModelStubWithTask(validTask);

        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_DUPLICATE_TASK, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_invalidContactIndex_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        AddTaskCommand addCommand = new AddTaskCommand(validTask, new HashSet<>(Arrays.asList(Index.fromOneBased(2))));
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();

        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_INVALID_CONTACT_INDEX, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Task buyMilk = new TaskBuilder().withTaskName("Buy Milk").build();
        Task cs2103t = new TaskBuilder().withTaskName("CS2103T").build();
        AddTaskCommand addBuyMilkCommand = new AddTaskCommand(buyMilk, new HashSet<>());
        AddTaskCommand addCs2103tCommand = new AddTaskCommand(cs2103t, new HashSet<>());

        // same object -> returns true
        assertTrue(addBuyMilkCommand.equals(addBuyMilkCommand));

        // same values -> returns true
        AddTaskCommand addBuyMilkCommandCopy = new AddTaskCommand(buyMilk, new HashSet<>());
        assertTrue(addBuyMilkCommand.equals(addBuyMilkCommandCopy));

        // different types -> returns false
        assertFalse(addBuyMilkCommand.equals(1));

        // null -> returns false
        assertFalse(addBuyMilkCommand.equals(null));

        // different task -> returns false
        assertFalse(addBuyMilkCommand.equals(addCs2103tCommand));
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
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
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
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getUnfilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
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
        public boolean hasTask(Task task) {
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
        public boolean hasBridge(PersonTaskBridge bridge) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBridge(Person person, Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBridge(PersonTaskBridge bridge) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBridge(PersonTaskBridge bridge) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void hotUpdateAssociatedContacts() {
        }

        @Override
        public void updateFilteredBridgeList(Predicate<PersonTaskBridge> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<PersonTaskBridge> getFilteredBridgeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<PersonTaskBridge> getUnfilteredBridgeList() {
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
            return this.task.equals(task);
        }
    }

    /**
     * A Model stub that always accept the task being added.
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();
        final ArrayList<PersonTaskBridge> bridgesAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasksAdded.stream().anyMatch(task::equals);
        }

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public void addBridge(Person person, Task task) {
            requireNonNull(person);
            requireNonNull(task);
            bridgesAdded.add(new PersonTaskBridge(person.getId(), task.getId()));
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableArrayList(new PersonBuilder().build());
        }
    }
}
