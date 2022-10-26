package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.foodrem.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.foodrem.commons.core.GuiSettings;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.model.ReadOnlyUserPrefs;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.ItemBuilder;
import seedu.foodrem.viewmodels.ItemWithMessage;

public class NewCommandTest {
    private static final String EXPECTED_SUCCESS_MESSAGE = "New item added as follows:";
    private static final String EXPECTED_FAILURE_DUPLICATE_ITEM = "This item already exists in FoodRem";

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NewCommand(null));
    }

    @Test
    public void execute_itemAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();
        Item validItem = new ItemBuilder().build();

        CommandResult<ItemWithMessage> commandResult = new NewCommand(validItem).execute(modelStub);
        assertEquals(new ItemWithMessage(validItem, EXPECTED_SUCCESS_MESSAGE), commandResult.getOutput());
        assertEquals(List.of(validItem), modelStub.itemsAdded);
    }

    @Test
    public void execute_duplicateItem_throwsCommandException() {
        Item validItem = new ItemBuilder().build();
        NewCommand newCommand = new NewCommand(validItem);
        ModelStub modelStub = new ModelStubWithItem(validItem);

        assertThrows(CommandException.class, EXPECTED_FAILURE_DUPLICATE_ITEM, () -> newCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Item potatoes = new ItemBuilder().withItemName("Potatoes").build();
        Item cucumbers = new ItemBuilder().withItemName("Cucumbers").build();
        NewCommand addPotatoCommand = new NewCommand(potatoes);
        NewCommand addCucumberCommand = new NewCommand(cucumbers);

        // same object -> returns true
        assertEquals(addPotatoCommand, addPotatoCommand);
        // same values -> returns true
        NewCommand addPotatoCommandCopy = new NewCommand(potatoes);
        assertEquals(addPotatoCommand, addPotatoCommandCopy);
        // different types -> returns false
        assertNotEquals(1, addPotatoCommand);
        // null -> returns false
        assertNotEquals(null, addPotatoCommand);
        // different item -> returns false
        assertNotEquals(addPotatoCommand, addCucumberCommand);
    }

    /**
     * A default model stub that have all the methods failing.
     */
    public static class ModelStub implements Model {
        @Override
        public boolean hasTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTag(Tag target, Tag editedTag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tag> getFilteredTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedItemList(Comparator<Item> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTagList(Predicate<Tag> predicate) {
            throw new AssertionError("This method should not be called.");
        }

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
        public Path getFoodRemFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFoodRemFilePath(Path foodRemFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFoodRem getFoodRem() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFoodRem(ReadOnlyFoodRem newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasItem(Item item) {
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
        public ObservableList<Item> getCurrentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItemList(Predicate<Item> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single item.
     */
    private static class ModelStubWithItem extends ModelStub {
        private final Item item;

        ModelStubWithItem(Item item) {
            requireNonNull(item);
            this.item = item;
        }

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return this.item.isSameItem(item);
        }
    }

    /**
     * A Model stub that always accept the item being added.
     */
    private static class ModelStubAcceptingItemAdded extends ModelStub {
        final ArrayList<Item> itemsAdded = new ArrayList<>();

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return itemsAdded.stream().anyMatch(item::isSameItem);
        }

        @Override
        public void addItem(Item item) {
            requireNonNull(item);
            itemsAdded.add(item);
        }

        @Override
        public ReadOnlyFoodRem getFoodRem() {
            return new FoodRem();
        }
    }
}
