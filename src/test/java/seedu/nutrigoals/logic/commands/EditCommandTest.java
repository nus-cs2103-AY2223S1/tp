package seedu.nutrigoals.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.DESC_BREAKFAST;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.DESC_LUNCH;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_BISCUIT_NAME;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_TAG_BREAKFAST;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.showFoodAtIndex;
import static seedu.nutrigoals.testutil.TypicalFoods.getTypicalNutriGoals;
import static seedu.nutrigoals.testutil.TypicalIndexes.INDEX_FIRST_MEAL;
import static seedu.nutrigoals.testutil.TypicalIndexes.INDEX_SECOND_MEAL;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.commons.core.Messages;
import seedu.nutrigoals.commons.core.index.Index;
import seedu.nutrigoals.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.ModelManager;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.UserPrefs;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.testutil.EditFoodDescriptorBuilder;
import seedu.nutrigoals.testutil.FoodBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalNutriGoals(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Model expectedModel = new ModelManager(new NutriGoals(model.getNutriGoals()), new UserPrefs());
        Food target = model.getFilteredFoodList().get(0);

        Food editedFood = new FoodBuilder().withDateTime(target.getDateTime().toString()).build();
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder(editedFood).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MEAL, descriptor);

        expectedModel.setFood(target, editedFood);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastFood = Index.fromOneBased(model.getFilteredFoodList().size());
        Food lastFood = model.getFilteredFoodList().get(indexLastFood.getZeroBased());

        FoodBuilder foodInList = new FoodBuilder(lastFood);
        Food editedFood = foodInList.withName(VALID_BISCUIT_NAME)
                .withTag(VALID_TAG_BREAKFAST).build();

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_BISCUIT_NAME)
                .withTags(VALID_TAG_BREAKFAST).build();
        EditCommand editCommand = new EditCommand(indexLastFood, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new NutriGoals(model.getNutriGoals()), new UserPrefs());
        expectedModel.setFood(lastFood, editedFood);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MEAL, new EditFoodDescriptor());
        Food editedFood = model.getFilteredFoodList().get(INDEX_FIRST_MEAL.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new NutriGoals(model.getNutriGoals()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showFoodAtIndex(model, INDEX_FIRST_MEAL);

        Food foodInFilteredList = model.getFilteredFoodList().get(INDEX_FIRST_MEAL.getZeroBased());
        Food editedFood = new FoodBuilder(foodInFilteredList).withName(VALID_BISCUIT_NAME).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MEAL,
                new EditFoodDescriptorBuilder().withName(VALID_BISCUIT_NAME).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new NutriGoals(model.getNutriGoals()), new UserPrefs());
        expectedModel.setFood(model.getFilteredFoodList().get(0), editedFood);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateFoodUnfilteredList_failure() {
        Food firstFood = model.getFilteredFoodList().get(INDEX_FIRST_MEAL.getZeroBased());
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder(firstFood).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_MEAL, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FOOD);
    }

    @Test
    public void execute_duplicateFoodFilteredList_failure() {
        showFoodAtIndex(model, INDEX_FIRST_MEAL);

        // edit food in filtered list into a duplicate in nutriGoals
        Food foodInList = model.getNutriGoals().getFoodList().get(INDEX_SECOND_MEAL.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MEAL,
                new EditFoodDescriptorBuilder(foodInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FOOD);
    }

    @Test
    public void execute_invalidFoodIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFoodList().size() + 1);
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_BISCUIT_NAME).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MEAL_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of nutriGoals
     */
    @Test
    public void execute_invalidFoodIndexFilteredList_failure() {
        showFoodAtIndex(model, INDEX_FIRST_MEAL);
        Index outOfBoundIndex = INDEX_SECOND_MEAL;
        // ensures that outOfBoundIndex is still in bounds of nutriGoals list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getNutriGoals().getFoodList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditFoodDescriptorBuilder().withName(VALID_BISCUIT_NAME).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MEAL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_MEAL, DESC_BREAKFAST);

        // same values -> returns true
        EditFoodDescriptor copyDescriptor = new EditFoodDescriptor(DESC_BREAKFAST);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_MEAL, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_MEAL, DESC_BREAKFAST)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_MEAL, DESC_LUNCH)));
    }

}
