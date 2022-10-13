package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import seedu.foodrem.testutil.TagBuilder;

public class NewTagCommandTest {
    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NewTagCommand(null));
    }

    @Test
    public void execute_tagAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTagAdded modelStub = new ModelStubAcceptingTagAdded();
        Tag validTag = new TagBuilder().build();

        CommandResult commandResult = new NewTagCommand(validTag).execute(modelStub);

        assertEquals(String.format(NewTagCommand.MESSAGE_SUCCESS, validTag), commandResult.getFeedbackToUser());
        assertEquals(List.of(validTag), modelStub.tagsAdded);
    }

    @Test
    public void execute_duplicateItem_throwsCommandException() {
        Tag validTag = new TagBuilder().build();
        NewTagCommand newTagCommand = new NewTagCommand(validTag);
        ModelStub modelStub = new ModelStubWithTag(validTag);

        assertThrows(CommandException.class,
                NewTagCommand.MESSAGE_DUPLICATE_TAG, () -> newTagCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tag fruitsTag = new TagBuilder().withTagName("fruits").build();
        Tag vegetableTag = new TagBuilder().withTagName("vegetable").build();

        NewTagCommand addFruitsTag = new NewTagCommand(fruitsTag);
        NewTagCommand addVegetableTag = new NewTagCommand(vegetableTag);

        // same object -> returns true
        assertEquals(addFruitsTag, addFruitsTag);

        // same values -> returns true
        NewTagCommand addFruitsTagCopy = new NewTagCommand(fruitsTag);
        assertEquals(addFruitsTag, addFruitsTagCopy);

        // different types -> returns false
        assertFalse(addFruitsTag.equals(1));

        // null -> returns false
        assertNotEquals(null, addFruitsTag);

        // different item -> returns false
        assertNotEquals(addFruitsTag, addVegetableTag);
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private static class ModelStub implements Model {
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
        public ObservableList<Item> getFilteredSortedItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Item> getSortedItemList() {
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
        public ObservableList<Item> getFilteredItemList() {
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
    private static class ModelStubWithTag extends ModelStub {
        private final Tag tag;

        ModelStubWithTag(Tag tag) {
            requireNonNull(tag);
            this.tag = tag;
        }

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return this.tag.equals(tag);
        }
    }

    /**
     * A Model stub that always accept the item being added.
     */
    private static class ModelStubAcceptingTagAdded extends ModelStub {
        final ArrayList<Tag> tagsAdded = new ArrayList<>();

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return tagsAdded.stream().anyMatch(tag::equals);
        }

        @Override
        public void addTag(Tag tag) {
            requireNonNull(tag);
            tagsAdded.add(tag);
        }

        @Override
        public ReadOnlyFoodRem getFoodRem() {
            return new FoodRem();
        }
    }
}
