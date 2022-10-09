package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEAL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Food;
import seedu.address.testutil.EditFoodDescriptorBuilder;
import seedu.address.testutil.FoodBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
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
        Index indexLastPerson = Index.fromOneBased(model.getFilteredFoodList().size());
        Food lastFood = model.getFilteredFoodList().get(indexLastPerson.getZeroBased());

        FoodBuilder personInList = new FoodBuilder(lastFood);
        Food editedFood = personInList.withName(VALID_NAME_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_NAME_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setFood(lastFood, editedFood);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MEAL, new EditFoodDescriptor());
        Food editedFood = model.getFilteredFoodList().get(INDEX_FIRST_MEAL.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_MEAL);

        Food foodInFilteredList = model.getFilteredFoodList().get(INDEX_FIRST_MEAL.getZeroBased());
        Food editedFood = new FoodBuilder(foodInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MEAL,
                new EditFoodDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setFood(model.getFilteredFoodList().get(0), editedFood);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Food firstFood = model.getFilteredFoodList().get(INDEX_FIRST_MEAL.getZeroBased());
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder(firstFood).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_MEAL, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FOOD);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_MEAL);

        // edit person in filtered list into a duplicate in address book
        Food foodInList = model.getAddressBook().getFoodList().get(INDEX_SECOND_MEAL.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MEAL,
                new EditFoodDescriptorBuilder(foodInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FOOD);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFoodList().size() + 1);
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_MEAL);
        Index outOfBoundIndex = INDEX_SECOND_MEAL;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getFoodList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditFoodDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_MEAL, DESC_AMY);

        // same values -> returns true
        EditFoodDescriptor copyDescriptor = new EditFoodDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_MEAL, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_MEAL, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_MEAL, DESC_BOB)));
    }

}
