package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.logic.commands.EditCommand.EditEateryDescriptor;
import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.ModelManager;
import eatwhere.foodguide.model.UserPrefs;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.testutil.EateryBuilder;
import eatwhere.foodguide.testutil.EditEateryDescriptorBuilder;
import eatwhere.foodguide.testutil.TypicalEateries;
import eatwhere.foodguide.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Eatery editedEatery = new EateryBuilder().build();
        EditCommand.EditEateryDescriptor descriptor = new EditEateryDescriptorBuilder(editedEatery).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_EATERY, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EATERY_SUCCESS, editedEatery);

        Model expectedModel = new ModelManager(new FoodGuide(model.getFoodGuide()), new UserPrefs());
        expectedModel.setEatery(model.getFilteredEateryList().get(0), editedEatery);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEatery = Index.fromOneBased(model.getFilteredEateryList().size());
        Eatery lastEatery = model.getFilteredEateryList().get(indexLastEatery.getZeroBased());

        EateryBuilder eateryInList = new EateryBuilder(lastEatery);
        Eatery editedEatery = eateryInList.withName(CommandTestUtil.VALID_NAME_BOB)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();

        EditCommand.EditEateryDescriptor descriptor = new EditEateryDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOB)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastEatery, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EATERY_SUCCESS, editedEatery);

        Model expectedModel = new ModelManager(new FoodGuide(model.getFoodGuide()), new UserPrefs());
        expectedModel.setEatery(lastEatery, editedEatery);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_EATERY,
                new EditCommand.EditEateryDescriptor());
        Eatery editedEatery = model.getFilteredEateryList().get(TypicalIndexes.INDEX_FIRST_EATERY.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EATERY_SUCCESS, editedEatery);

        Model expectedModel = new ModelManager(new FoodGuide(model.getFoodGuide()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showEateryAtIndex(model, TypicalIndexes.INDEX_FIRST_EATERY);

        Eatery eateryInFilteredList = model.getFilteredEateryList()
                .get(TypicalIndexes.INDEX_FIRST_EATERY.getZeroBased());
        Eatery editedEatery = new EateryBuilder(eateryInFilteredList).withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_EATERY,
                new EditEateryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EATERY_SUCCESS, editedEatery);

        Model expectedModel = new ModelManager(new FoodGuide(model.getFoodGuide()), new UserPrefs());
        expectedModel.setEatery(model.getFilteredEateryList().get(0), editedEatery);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEateryUnfilteredList_failure() {
        Eatery firstEatery = model.getFilteredEateryList().get(TypicalIndexes.INDEX_FIRST_EATERY.getZeroBased());
        EditEateryDescriptor descriptor = new EditEateryDescriptorBuilder(firstEatery).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_SECOND_EATERY, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EATERY);
    }

    @Test
    public void execute_duplicateEateryFilteredList_failure() {
        CommandTestUtil.showEateryAtIndex(model, TypicalIndexes.INDEX_FIRST_EATERY);

        // edit eatery in filtered list into a duplicate in food guide
        Eatery eateryInList = model.getFoodGuide().getEateryList()
                .get(TypicalIndexes.INDEX_SECOND_EATERY.getZeroBased());
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_EATERY,
                new EditEateryDescriptorBuilder(eateryInList).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EATERY);
    }

    @Test
    public void execute_invalidEateryIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEateryList().size() + 1);
        EditCommand.EditEateryDescriptor descriptor = new EditEateryDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EATERY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of food guide
     */
    @Test
    public void execute_invalidEateryIndexFilteredList_failure() {
        CommandTestUtil.showEateryAtIndex(model, TypicalIndexes.INDEX_FIRST_EATERY);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_EATERY;
        // ensures that outOfBoundIndex is still in bounds of food guide list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFoodGuide().getEateryList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditEateryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EATERY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand =
                new EditCommand(TypicalIndexes.INDEX_FIRST_EATERY, CommandTestUtil.DESC_AMY);

        // same values -> returns true
        EditCommand.EditEateryDescriptor copyDescriptor = new EditCommand
                .EditEateryDescriptor(CommandTestUtil.DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(TypicalIndexes.INDEX_FIRST_EATERY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new EditCommand(TypicalIndexes.INDEX_SECOND_EATERY, CommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(
                new EditCommand(TypicalIndexes.INDEX_FIRST_EATERY, CommandTestUtil.DESC_BOB)));
    }

}
