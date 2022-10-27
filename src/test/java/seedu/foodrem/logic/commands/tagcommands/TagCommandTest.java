package seedu.foodrem.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_VEGETABLES;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.testutil.TypicalFoodRem.getFoodRemWithTypicalItemsWithoutTags;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.CommandTestUtil;
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

public class TagCommandTest {
    private static final String EXPECTED_SUCCESS_MESSAGE = "Item tagged successfully. Updated item:";
    private static final String ERROR_DUPLICATE = "This item has already been tagged with this tag";
    private static final String ERROR_NOT_FOUND_TAG = "This tag does not exist";
    private static final String ERROR_NOT_FOUND_ITEM = "The item index does not exist";

    @Test
    public void execute_tagItem_success() {
        final Model model = new ModelManager(getFoodRemWithTypicalItemsWithoutTags(), new UserPrefs());

        // Creating a copy of first item of model and adding a vegetable tag
        Item editedItem = new ItemBuilder(model.getCurrentList().get(0))
                .withTags(CommandTestUtil.VALID_TAG_NAME_VEGETABLES).build();

        Tag tag = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();

        // Creating an expected model to compare to
        Model expectedModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());

        // Setting expected model's first item to the tagged first item correct
        expectedModel.addTag(tag);
        expectedModel.setItem(expectedModel.getCurrentList().get(0), editedItem);

        // Run tag command on original model
        model.addTag(tag);
        TagCommand tagItemCommand = new TagCommand(tag.getName(), INDEX_FIRST_ITEM);

        assertCommandSuccess(tagItemCommand, model,
                new ItemWithMessage(editedItem, EXPECTED_SUCCESS_MESSAGE), expectedModel);
        assertTrue(model.getCurrentList().get(0).getTagSet().contains(tag));
    }

    @Test
    public void execute_tagItemWithoutExistingTagInModel_throwsCommandException() {
        final Model model = new ModelManager(getFoodRemWithTypicalItemsWithoutTags(), new UserPrefs());
        Tag tag = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();

        TagCommand tagItemCommand = new TagCommand(tag.getName(), INDEX_FIRST_ITEM);
        assertCommandFailure(tagItemCommand, model, ERROR_NOT_FOUND_TAG);
    }

    @Test
    public void execute_itemIndexNotFound_throwsCommandException() {
        final Model model = new ModelManager(getFoodRemWithTypicalItemsWithoutTags(), new UserPrefs());
        Tag tag = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();
        model.addTag(tag);

        TagCommand tagItemCommand = new TagCommand(tag.getName(), INDEX_THIRD_ITEM);
        assertCommandFailure(tagItemCommand, model, ERROR_NOT_FOUND_ITEM);
    }

    @Test
    public void execute_duplicateTagInItem_throwsCommandException() {
        final Model model = new ModelManager(getFoodRemWithTypicalItemsWithoutTags(), new UserPrefs());
        Tag tag = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();

        model.addTag(tag);
        Item item = model.getCurrentList().get(0);
        Set<Tag> tagSet = item.getTagSet();
        tagSet.add(tag);
        model.setItem(item, Item.createItemWithTags(item, tagSet));

        TagCommand tagItemCommand = new TagCommand(tag.getName(), INDEX_FIRST_ITEM);
        assertCommandFailure(tagItemCommand, model, ERROR_DUPLICATE);
    }

    @Test
    public void equals() {
        Tag vegetableTag = TypicalTags.VEGETABLES;
        Tag fruitsTag = TypicalTags.FRUITS;

        TagCommand tagItemWithVegetableCommand = new TagCommand(vegetableTag.getName(), INDEX_FIRST_ITEM);
        TagCommand tagItemWithFruitsCommand = new TagCommand(fruitsTag.getName(), INDEX_FIRST_ITEM);

        // same object -> returns true
        assertEquals(tagItemWithVegetableCommand, tagItemWithVegetableCommand);
        // same values -> returns true
        Tag vegetableTagCopy = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();
        TagCommand tagItemWithVegetableCommandCopy = new TagCommand(vegetableTagCopy.getName(), INDEX_FIRST_ITEM);
        assertEquals(tagItemWithVegetableCommand, tagItemWithVegetableCommandCopy);
        // different types -> returns false
        assertNotEquals(tagItemWithFruitsCommand, new ResetCommand());
        // null -> returns false
        assertNotEquals(tagItemWithFruitsCommand, null);
        // different item -> returns false
        assertNotEquals(tagItemWithVegetableCommand, tagItemWithFruitsCommand);
    }
}
