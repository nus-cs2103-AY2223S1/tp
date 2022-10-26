package seedu.foodrem.logic.commands.itemcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_NUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_VEGETABLES;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.testutil.TypicalFoodRem.getTypicalFoodRem;

import org.junit.jupiter.api.Test;

import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.TagBuilder;

public class FilterTagCommandTest {
    private static final String EXPECTED_SUCCESS_TAG_NAME = "Filtered by tag: %s\n";
    private static final String EXPECTED_SUCCESS_LIST_AFTER_FILTERING = "%1$d items after filtering!";

    private final Model model = new ModelManager(getTypicalFoodRem(), new UserPrefs());


    private final Tag numbersTag = new TagBuilder()
            .withTagName(VALID_TAG_NAME_NUMBERS)
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

        String expectedMessageVegetable = String.format(EXPECTED_SUCCESS_TAG_NAME, vegetableTag.getName())
                + String.format(EXPECTED_SUCCESS_LIST_AFTER_FILTERING, expectedSizeVegetable);

        FilterTagCommand filterVegetableTagCommand = new FilterTagCommand(vegetableTag);

        Model expectedModelVegetable = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        expectedModelVegetable.updateFilteredItemList(item -> item.getTagSet().contains(vegetableTag));

        assertCommandSuccess(filterVegetableTagCommand, model, expectedMessageVegetable, expectedModelVegetable);

        long expectedSizeNumbers = model.getCurrentList()
                .stream()
                .filter(item -> item.getTagSet().contains(numbersTag))
                .count();

        String expectedMessageNumbers = String.format(EXPECTED_SUCCESS_TAG_NAME, numbersTag.getName())
                + String.format(EXPECTED_SUCCESS_LIST_AFTER_FILTERING, expectedSizeNumbers);

        FilterTagCommand filterNumbersTagCommand = new FilterTagCommand(numbersTag);

        Model expectedModelNumbers = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        expectedModelNumbers.updateFilteredItemList(item -> item.getTagSet().contains(numbersTag));

        assertCommandSuccess(filterNumbersTagCommand, model, expectedMessageNumbers, expectedModelNumbers);
    }

    @Test
    void testEquals() {
        FilterTagCommand filterVegetableTagCommand = new FilterTagCommand(vegetableTag);
        FilterTagCommand filterNumbersTagCommand = new FilterTagCommand(numbersTag);
        Tag numberTagCopy = new TagBuilder()
                .withTagName(VALID_TAG_NAME_NUMBERS)
                .build();
        FilterTagCommand filterNumbersTagCommandCopy = new FilterTagCommand(numberTagCopy);


        // Exactly the same
        assertEquals(filterNumbersTagCommand, filterNumbersTagCommand);

        // Same tag to filter by
        assertEquals(filterNumbersTagCommand, filterNumbersTagCommandCopy);

        // Different tag to filter by
        assertNotEquals(filterNumbersTagCommand, filterVegetableTagCommand);

        // Different object
        assertNotEquals(filterNumbersTagCommand, 1);
    }
}
