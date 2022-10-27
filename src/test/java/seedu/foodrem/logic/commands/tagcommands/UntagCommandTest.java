package seedu.foodrem.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_VEGETABLES;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.testutil.TypicalFoodRem.getFoodRemWithTypicalItems;
import static seedu.foodrem.testutil.TypicalFoodRem.getTypicalFoodRem;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.generalcommands.ResetCommand;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.ItemBuilder;
import seedu.foodrem.testutil.TagBuilder;
import seedu.foodrem.testutil.TypicalTags;
import seedu.foodrem.viewmodels.ItemWithMessage;

public class UntagCommandTest {
    private static final String EXPECTED_SUCCESS_MESSAGE = "Item untagged successfully. Updated item:";
    private static final String ERROR_ITEM_DOES_NOT_CONTAIN_TAG = "This item has not been tagged with this tag";
    private static final String ERROR_NOT_FOUND_TAG = "This tag does not exist";
    private static final String ERROR_NOT_FOUND_ITEM = "The item index does not exist";

    @Test
    public void execute_untagItem_success() {
        final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());

        // Creating a copy of first item of model (with tag removed)
        Item editedItem = new ItemBuilder(model.getCurrentList().get(0)).build();
        Tag tag = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();
        Set<Tag> tagSet = editedItem.getTagSet();
        tagSet.remove(tag);
        editedItem = Item.createItemWithTags(editedItem, tagSet);

        // The initial model does not have the tag in its UniqueTagList

        // Creating an expected model to compare to
        Model expectedModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());

        // Setting expected model's first item to the tagged first item correct
        expectedModel.setItem(expectedModel.getCurrentList().get(0), editedItem);

        // Run tag command on original model
        UntagCommand untagItemCommand = new UntagCommand(tag.getName(), INDEX_FIRST_ITEM);

        assertCommandSuccess(untagItemCommand, model,
                new ItemWithMessage(editedItem, EXPECTED_SUCCESS_MESSAGE), expectedModel);
        assertFalse(model.getCurrentList().get(0).getTagSet().contains(tag));
    }

    @Test
    public void execute_untagItemWithoutExistingTagInModel_throwsCommandException() {
        final Model model = new ModelManager(getFoodRemWithTypicalItems(), new UserPrefs());
        Tag tag = new TagBuilder().withTagName("NON EXISTENT").build();

        UntagCommand untagItemCommand = new UntagCommand(tag.getName(), INDEX_FIRST_ITEM);
        assertCommandFailure(untagItemCommand, model, ERROR_NOT_FOUND_TAG);
    }

    @Test
    public void execute_untagItemWithInvalidIndex_throwsCommandException() {
        final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());
        Tag tag = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();

        UntagCommand untagItemCommand = new UntagCommand(tag.getName(), INDEX_THIRD_ITEM);
        assertCommandFailure(untagItemCommand, model, ERROR_NOT_FOUND_ITEM);
    }

    @Test
    public void execute_untagItemWithoutExistingTagInItem_throwsCommandException() {
        final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());
        Tag tag = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();

        // Manually remove tag from typical FoodRem item
        Item item = model.getCurrentList().get(0);
        Set<Tag> tagSet = item.getTagSet();
        tagSet.remove(tag);
        model.setItem(item, Item.createItemWithTags(item, tagSet));

        UntagCommand untagItemCommand = new UntagCommand(tag.getName(), INDEX_FIRST_ITEM);
        assertCommandFailure(untagItemCommand, model, ERROR_ITEM_DOES_NOT_CONTAIN_TAG);
    }

    @Test
    public void equals() {
        Tag vegetableTag = TypicalTags.VEGETABLES;
        Tag fruitsTag = TypicalTags.FRUITS;

        UntagCommand untagItemWithVegetableCommand = new UntagCommand(vegetableTag.getName(), INDEX_FIRST_ITEM);
        UntagCommand untagItemWithFruitsCommand = new UntagCommand(fruitsTag.getName(), INDEX_FIRST_ITEM);

        // same object -> returns true
        assertEquals(untagItemWithVegetableCommand, untagItemWithVegetableCommand);
        // same values -> returns true
        Tag vegetableTagCopy = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();
        UntagCommand untagItemWithVegetableCommandCopy = new UntagCommand(vegetableTagCopy.getName(), INDEX_FIRST_ITEM);
        assertEquals(untagItemWithVegetableCommand, untagItemWithVegetableCommandCopy);
        // different types -> returns false
        assertNotEquals(untagItemWithFruitsCommand, new ResetCommand());
        // null -> returns false
        assertNotEquals(untagItemWithFruitsCommand, null);
        // different item -> returns false
        assertNotEquals(untagItemWithVegetableCommand, untagItemWithFruitsCommand);
    }
}
