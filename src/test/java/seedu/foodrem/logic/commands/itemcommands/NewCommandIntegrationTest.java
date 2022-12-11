package seedu.foodrem.logic.commands.itemcommands;

import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_NAME_CARROTS;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.testutil.TypicalFoodRem.getTypicalFoodRem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.testutil.ItemBuilder;
import seedu.foodrem.viewmodels.ItemWithMessage;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class NewCommandIntegrationTest {
    private static final String EXPECTED_SUCCESS_MESSAGE = "New item added as follows:";
    private static final String EXPECTED_FAILURE_DUPLICATE_ITEM = "This item already exists in FoodRem";

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodRem(), new UserPrefs());
    }

    @Test
    public void execute_newItem_success() {
        Item validItem = new ItemBuilder().withItemName(VALID_ITEM_NAME_CARROTS).build();

        Model expectedModel = new ModelManager(model.getFoodRem(), new UserPrefs());
        expectedModel.addItem(validItem);

        assertCommandSuccess(new NewCommand(validItem), model,
                new ItemWithMessage(validItem, EXPECTED_SUCCESS_MESSAGE), expectedModel);
    }

    @Test
    public void execute_duplicateItem_throwsCommandException() {
        Item itemInList = model.getFoodRem().getItemList().get(0);
        assertCommandFailure(new NewCommand(itemInList), model, EXPECTED_FAILURE_DUPLICATE_ITEM);
    }
}
