package seedu.foodrem.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_VEGETABLES;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.testutil.TypicalFoodRem.getTypicalFoodRem;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.generalcommands.ResetCommand;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.TagBuilder;
import seedu.foodrem.testutil.TypicalTags;

public class DeleteTagCommandTest {
    private static final String EXPECTED_ERROR_NOT_FOUND = "This tag does not exist in the FoodRem";
    private static final String EXPECTED_FORMAT_SUCCESS = "Tag deleted: %1$s";

    private final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());

    @Test
    public void execute_deleteTag_success() {
        Tag tagToDelete = model.getFilteredTagList().get(INDEX_FIRST_ITEM.getZeroBased());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(tagToDelete);

        String expectedMessage = String.format(EXPECTED_FORMAT_SUCCESS, tagToDelete);

        ModelManager expectedModel = new ModelManager(model.getFoodRem(), new UserPrefs());
        expectedModel.deleteTag(tagToDelete);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);

        for (Item item : model.getCurrentList()) {
            assertFalse(item.getTagSet().contains(tagToDelete));
        }
    }

    @Test
    public void execute_tagDeletedFromItems_success() {
        Tag tagToDelete = TypicalTags.VEGETABLES;
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(tagToDelete);

        // True before deletion
        for (Item item : model.getCurrentList()) {
            assertTrue(item.getTagSet().contains(tagToDelete));
        }

        String expectedMessage = String.format(EXPECTED_FORMAT_SUCCESS, tagToDelete);

        ModelManager expectedModel = new ModelManager(model.getFoodRem(), new UserPrefs());
        expectedModel.deleteTag(tagToDelete);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);

        // Tag should be deleted from all items
        for (Item item : model.getCurrentList()) {
            assertFalse(item.getTagSet().contains(tagToDelete));
        }
    }

    @Test
    public void execute_tagNotFound_throwsCommandException() {
        Tag tagToDelete = new TagBuilder().withTagName("NOT_FOUND").build();
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(tagToDelete);

        assertCommandFailure(deleteTagCommand, model, EXPECTED_ERROR_NOT_FOUND);
    }

    @Test
    public void equals() {
        Tag vegetableTag = TypicalTags.VEGETABLES;
        Tag fruitsTag = TypicalTags.FRUITS;

        DeleteTagCommand deleteVegetableCommand = new DeleteTagCommand(vegetableTag);
        DeleteTagCommand deleteFruitsCommand = new DeleteTagCommand(fruitsTag);

        // same object -> returns true
        assertEquals(deleteVegetableCommand, deleteVegetableCommand);

        // same values -> returns true
        Tag vegetableTagCopy = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();
        DeleteTagCommand deleteVegetableCommandCopy = new DeleteTagCommand(vegetableTagCopy);
        assertEquals(deleteVegetableCommand, deleteVegetableCommandCopy);

        // different types -> returns false
        assertNotEquals(deleteVegetableCommand, new ResetCommand());

        // null -> returns false
        assertNotEquals(deleteVegetableCommand, null);

        // different item -> returns false
        assertNotEquals(deleteVegetableCommand, deleteFruitsCommand);
    }
}
