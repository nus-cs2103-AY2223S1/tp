package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.item.Item;
import seedu.address.testutil.ItemBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();
        Item validItem = new ItemBuilder().build();

        CommandResult commandResult = new AddCommand(validItem).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validItem), commandResult.getFeedbackToUser());
        assertEquals(List.of(validItem), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicateItem_throwsCommandException() {
        Item validItem = new ItemBuilder().build();
        AddCommand addCommand = new AddCommand(validItem);
        ModelStub modelStub = new ModelStubWithItem(validItem);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Item potatoes = new ItemBuilder().withItemName("Potatoes").build();
        Item cucumbers = new ItemBuilder().withItemName("Cucumbers").build();
        AddCommand addAliceCommand = new AddCommand(potatoes);
        AddCommand addBobCommand = new AddCommand(cucumbers);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(potatoes);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private static class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
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
        public void addItem(Item person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasItem(Item person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteItem(Item target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItem(Item target, Item editedItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Item> getFilteredItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItemList(Predicate<Item> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private static class ModelStubWithItem extends ModelStub {
        private final Item person;

        ModelStubWithItem(Item person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasItem(Item person) {
            requireNonNull(person);
            return this.person.isSameItem(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private static class ModelStubAcceptingItemAdded extends ModelStub {
        final ArrayList<Item> personsAdded = new ArrayList<>();

        @Override
        public boolean hasItem(Item person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSameItem);
        }

        @Override
        public void addItem(Item person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
