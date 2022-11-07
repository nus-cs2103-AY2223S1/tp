package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.item.SupplyItem;
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
        AddTaskCommandTest.ModelStubAcceptingTaskAdded modelStub = new AddTaskCommandTest.ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = new AddTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS, validTask),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }

    @Test
    public void execute_taskAcceptedByModelHasDeadlinePastToday_addSuccessfulShowsWarning() throws CommandException {
        AddTaskCommandTest.ModelStubAcceptingTaskAdded modelStub = new AddTaskCommandTest.ModelStubAcceptingTaskAdded();
        Task validTaskWithDeadlinePastToday = new TaskBuilder()
                .withDeadline(LocalDate.of(2020, 10, 10)).build();

        CommandResult commandResult = new AddTaskCommand(validTaskWithDeadlinePastToday).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_ADD_TASK_WARNING, validTaskWithDeadlinePastToday),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTaskWithDeadlinePastToday), modelStub.tasksAdded);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
        AddTaskCommandTest.ModelStub modelStub = new AddTaskCommandTest.ModelStubWithTask(validTask);

        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_DUPLICATE_TASK, () -> addTaskCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Task buyChicken = new TaskBuilder().withTitle("Buy Chicken").build();
        Task buyOil = new TaskBuilder().withTitle("Buy Oil").build();
        AddTaskCommand addBuyChickenCommand = new AddTaskCommand(buyChicken);
        AddTaskCommand addBuyOilCommand = new AddTaskCommand(buyOil);

        // same object -> returns true
        assertTrue(addBuyChickenCommand.equals(addBuyChickenCommand));

        // same values -> returns true
        AddTaskCommand addBuyChickenCommandCopy = new AddTaskCommand(buyChicken);
        assertTrue(addBuyChickenCommand.equals(addBuyChickenCommandCopy));

        // different types -> returns false
        assertFalse(addBuyChickenCommand.equals(1));

        // null -> returns false
        assertFalse(addBuyChickenCommand.equals(null));

        // different task -> returns false
        assertFalse(addBuyChickenCommand.equals(addBuyOilCommand));
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
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskList getTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the Inventory
         */
        @Override
        public ReadOnlyInventory getInventory() {
            return null;
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonExcluding(Person person, Person excludedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSupplyItem(SupplyItem item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSupplyItem(SupplyItem item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSupplyItemExcluding(SupplyItem item, SupplyItem excludedItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSupplyItemSuppliedBy(Person supplier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<SupplyItem> supplyItemSuppliedBy(Person supplier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSupplyItem(SupplyItem item, Index targetIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSupplyItem(Index index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void increaseSupplyItem(Index targetIndex, int amount) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void decreaseSupplyItem(Index targetIndex, int amount) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void changeIncDecAmount(Index targetIndex, int amount) {
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
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<SupplyItem> getFilteredSupplyItemList() {
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task task, Index targetIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Index index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredSupplyItemList(Predicate<SupplyItem> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends AddTaskCommandTest.ModelStub {
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
    private class ModelStubAcceptingTaskAdded extends AddTaskCommandTest.ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasksAdded.contains(task);
        }

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }


}

