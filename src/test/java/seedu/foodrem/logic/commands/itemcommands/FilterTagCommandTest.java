package seedu.foodrem.logic.commands.itemcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_FRUITS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_VEGETABLES;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.testutil.TypicalFoodRem.getTypicalFoodRem;

import org.junit.jupiter.api.Test;

import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.TagBuilder;
import seedu.foodrem.viewmodels.FilterByTag;

public class FilterTagCommandTest {
    private static final String EXPECTED_ERROR_NOT_FOUND = "This tag does not exist in FoodRem";
    private static final String EXPECTED_SUCCESS_LIST_AFTER_FILTERING = "%1$d items filtered";

    private final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());
    private final Tag fruitsTag = new TagBuilder()
            .withTagName(VALID_TAG_NAME_FRUITS)
            .build();
    private final Tag vegetableTag = new TagBuilder()
            .withTagName(VALID_TAG_NAME_VEGETABLES)
            .build();

    @Test
    public void execute_success() {
        long expectedSizeVegetable = model.getCurrentList()
                .stream()
                .filter(item -> item.getTagSet().contains(vegetableTag))
                .count();

        String expectedMessageVegetable = String.format(EXPECTED_SUCCESS_LIST_AFTER_FILTERING, expectedSizeVegetable);

        FilterTagCommand filterVegetableTagCommand = new FilterTagCommand(vegetableTag);

        Model expectedModelVegetable = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        expectedModelVegetable.updateFilteredItemList(item -> item.getTagSet().contains(vegetableTag));

        assertCommandSuccess(filterVegetableTagCommand, model,
                new FilterByTag(vegetableTag, "Filtered by tag:", expectedMessageVegetable),
                             expectedModelVegetable);

        long expectedSizeNumbers = model.getCurrentList()
                .stream()
                .filter(item -> item.getTagSet().contains(fruitsTag))
                .count();

        String expectedMessageNumbers = String.format(EXPECTED_SUCCESS_LIST_AFTER_FILTERING, expectedSizeNumbers);

        FilterTagCommand filterNumbersTagCommand = new FilterTagCommand(fruitsTag);

        Model expectedModelNumbers = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        expectedModelNumbers.updateFilteredItemList(item -> item.getTagSet().contains(fruitsTag));

        assertCommandSuccess(filterNumbersTagCommand, model,
                             new FilterByTag(fruitsTag, "Filtered by tag:", expectedMessageNumbers),
                             expectedModelNumbers);
    }

    @Test
    public void test_tagToFilterNotFound_throwsException() {
        Tag tagToFilter = new TagBuilder().withTagName("NOT_FOUND").build();
        FilterTagCommand filterTagCommand = new FilterTagCommand(tagToFilter);

        assertCommandFailure(filterTagCommand, model, EXPECTED_ERROR_NOT_FOUND);
    }

    @Test
    public void testEquals() {
        FilterTagCommand filterVegetableTagCommand = new FilterTagCommand(vegetableTag);
        FilterTagCommand filterFruitsTagCommand = new FilterTagCommand(fruitsTag);
        Tag fruitsTagCopy = new TagBuilder()
                .withTagName(VALID_TAG_NAME_FRUITS)
                .build();
        FilterTagCommand filterFruitsTagCommandCopy = new FilterTagCommand(fruitsTagCopy);

        // Exactly the same
        assertEquals(filterFruitsTagCommand, filterFruitsTagCommand);
        // Same tag to filter by
        assertEquals(filterFruitsTagCommand, filterFruitsTagCommandCopy);
        // Different tag to filter by
        assertNotEquals(filterFruitsTagCommand, filterVegetableTagCommand);
        // Different object
        assertNotEquals(filterFruitsTagCommand, 1);
        // Null
        assertNotEquals(filterFruitsTagCommand, null);
    }
}
