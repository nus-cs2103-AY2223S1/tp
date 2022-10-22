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

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.exceptions.CommandException;
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

public class UntagCommandTest {
    private static final String MESSAGE_SUCCESS = "Item untagged successfully";
    private static final String ERROR_ITEM_DOES_NOT_CONTAIN_TAG = "This item is not tagged with this tag";
    private static final String ERROR_NOT_FOUND_TAG = "This tag does not exist";
    private static final String ERROR_NOT_FOUND_ITEM = "The item index does not exist";



    @Test
    public void execute_untagItem_success() throws CommandException {

        final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());

        // Creating a copy of first item of model (with tag removed)
        Item editedItem = new ItemBuilder(model.getFilteredItemList().get(0)).build();
        Tag tag = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();
        editedItem.removeItemTag(tag);
        //The initial model does not have the tag in its UniqueTagList
        //model.addTag(tag);

        String expectedMessage = String.format(MESSAGE_SUCCESS);

        //Creating an expected model to compare to
        Model expectedModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());

        // Setting expected model's first item to the tagged first item correct
        expectedModel.setItem(expectedModel.getFilteredItemList().get(0), editedItem);

        //Run  tag command on original model
        UntagCommand untagItemCommand = new UntagCommand(tag.getName(), INDEX_FIRST_ITEM);

        assertCommandSuccess(untagItemCommand, model, expectedMessage, expectedModel);
        assertFalse(model.getFilteredItemList().get(0).containsTag(tag));

    }

    @Test
    public void execute_untagItemWithoutExistingTagInModel_throwsCommandException() {

        final Model model = new ModelManager(getFoodRemWithTypicalItems(), new UserPrefs());

        Tag tag = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();

        UntagCommand untagItemCommand = new UntagCommand(tag.getName(), INDEX_FIRST_ITEM);

        assertCommandFailure(untagItemCommand, model, ERROR_NOT_FOUND_TAG);

    }

    @Test
    public void execute_untagItemWithInvalidIndex_throwsCommandException() {
        final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());

        Tag tag = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();

        //model.addTag(tag);

        UntagCommand untagItemCommand = new UntagCommand(tag.getName(), INDEX_THIRD_ITEM);

        assertCommandFailure(untagItemCommand, model, ERROR_NOT_FOUND_ITEM);

    }

    @Test
    public void execute_untagItemWithoutExistingTagInItem_throwsCommandException() {
        final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());

        Tag tag = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();

        //Manually remove tag from typical FoodRem item
        model.getFilteredItemList().get(0).removeItemTag(tag);

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
