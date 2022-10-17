package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_VEGETABLES;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.logic.commands.tagcommands.DeleteTagCommand.MESSAGE_TAG_NOT_FOUND;
import static seedu.foodrem.testutil.TypicalFoodRem.getFoodRemWithTypicalItemsWithoutTags;
import static seedu.foodrem.testutil.TypicalFoodRem.getTypicalFoodRem;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;
import javafx.collections.ObservableList;
import javax.sound.midi.Soundbank;
import seedu.foodrem.commons.core.GuiSettings;
import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.logic.commands.generalcommands.ResetCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.model.ReadOnlyUserPrefs;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.EditItemDescriptorBuilder;
import seedu.foodrem.testutil.ItemBuilder;
import seedu.foodrem.testutil.TagBuilder;
import seedu.foodrem.testutil.TypicalTags;

public class TagCommandTest {
    private final Model model = new ModelManager(getFoodRemWithTypicalItemsWithoutTags(), new UserPrefs());

    @Test
    public void execute_tagItem_success() throws CommandException {

        // Creating a copy of first item of model and adding a vegetable tag
        Item editedItem = new ItemBuilder(model.getFilteredItemList().get(0))
                .withTags(CommandTestUtil.VALID_TAG_NAME_VEGETABLES).build();

        Tag tag = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();

        String expectedMessage = String.format(TagCommand.MESSAGE_SUCCESS);

        //Creating an expected model to compare to
        Model expectedModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());

        // Setting expected model's first item to the tagged first item correct
        expectedModel.addTag(tag);
        expectedModel.setItem(expectedModel.getFilteredItemList().get(0), editedItem);

        //Run  tag command on original model
        model.addTag(tag);
        TagCommand tagItemCommand = new TagCommand(tag.getName(), INDEX_FIRST_ITEM);

        assertCommandSuccess(tagItemCommand, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredItemList().get(0).containsTag(tag));

    }

    @Test
    public void execute_tagDeletedFromItems_success() {
        Tag tagToDelete = TypicalTags.VEGETABLES;
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(tagToDelete);

        // True before deletion
        for (Item item : model.getFilteredItemList()) {
            assertTrue(item.containsTag(tagToDelete));
        }

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_SUCCESS, tagToDelete);

        ModelManager expectedModel = new ModelManager(model.getFoodRem(), new UserPrefs());
        expectedModel.deleteTag(tagToDelete);


        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);

        // Tag should be deleted from all items
        for (Item item : model.getFilteredItemList()) {
            assertFalse(item.containsTag(tagToDelete));
        }
    }

    @Test
    public void execute_tagNotFound_throwsCommandException() {
        Tag tagToDelete = new TagBuilder().withTagName("NOT_FOUND").build();
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(tagToDelete);

        assertCommandFailure(deleteTagCommand, model, MESSAGE_TAG_NOT_FOUND);
    }

    @Test
    public void equals() {
        Tag vegetableTag = TypicalTags.VEGETABLES;
        Tag fruitsTag = TypicalTags.FRUITS;

        TagCommand tagItemWithVegetableCommand = new TagCommand(vegetableTag.getName(), INDEX_FIRST_ITEM);
        TagCommand tagItemWithFruitsCommand = new TagCommand(fruitsTag.getName(),INDEX_FIRST_ITEM);

        // same object -> returns true
        assertEquals(tagItemWithVegetableCommand, tagItemWithVegetableCommand);

        // same values -> returns true
        Tag vegetableTagCopy = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();
        TagCommand tagItemWithVegetableCommandCopy = new TagCommand(vegetableTagCopy.getName(),INDEX_FIRST_ITEM);
        assertEquals(tagItemWithVegetableCommand, tagItemWithVegetableCommandCopy);

        // different types -> returns false
        assertNotEquals(tagItemWithFruitsCommand, new ResetCommand());

        // null -> returns false
        assertNotEquals(tagItemWithFruitsCommand, null);

        // different item -> returns false
        assertNotEquals(tagItemWithVegetableCommand, tagItemWithFruitsCommand);
    }

}
