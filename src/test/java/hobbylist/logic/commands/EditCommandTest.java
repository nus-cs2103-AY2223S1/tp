package hobbylist.logic.commands;

import static hobbylist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hobbylist.commons.core.Messages;
import hobbylist.commons.core.index.Index;
import hobbylist.logic.commands.EditCommand.EditActivityDescriptor;
import hobbylist.model.HobbyList;
import hobbylist.model.Model;
import hobbylist.model.ModelManager;
import hobbylist.model.UserPrefs;
import hobbylist.model.activity.Activity;
import hobbylist.testutil.ActivityBuilder;
import hobbylist.testutil.EditActivityDescriptorBuilder;
import hobbylist.testutil.TypicalActivities;
import hobbylist.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Activity editedActivity = new ActivityBuilder().build();
        EditCommand.EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder(editedActivity).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ACTIVITY_SUCCESS, editedActivity);

        Model expectedModel = new ModelManager(new HobbyList(model.getHobbyList()), new UserPrefs());
        expectedModel.setActivity(model.getFilteredActivityList().get(0), editedActivity);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastActivity = Index.fromOneBased(model.getFilteredActivityList().size());
        Activity lastActivity = model.getFilteredActivityList().get(indexLastActivity.getZeroBased());

        ActivityBuilder activityInList = new ActivityBuilder(lastActivity);
        Activity editedActivity = activityInList.withName(CommandTestUtil.VALID_NAME_BOXING)
                .withTags(CommandTestUtil.VALID_TAG_ENTERTAINMENT).build();

        EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOXING)
                .withTags(CommandTestUtil.VALID_TAG_ENTERTAINMENT).build();
        EditCommand editCommand = new EditCommand(indexLastActivity, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ACTIVITY_SUCCESS, editedActivity);

        Model expectedModel = new ModelManager(new HobbyList(model.getHobbyList()), new UserPrefs());
        expectedModel.setActivity(lastActivity, editedActivity);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY,
                new EditCommand.EditActivityDescriptor());
        Activity editedActivity = model.getFilteredActivityList()
                .get(TypicalIndexes.INDEX_FIRST_ACTIVITY.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ACTIVITY_NO_CHANGE, editedActivity);

        Model expectedModel = new ModelManager(new HobbyList(model.getHobbyList()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showActivityAtIndex(model, TypicalIndexes.INDEX_FIRST_ACTIVITY);

        Activity activityInFilteredList = model.getFilteredActivityList()
                .get(TypicalIndexes.INDEX_FIRST_ACTIVITY.getZeroBased());
        Activity editedActivity = new ActivityBuilder(activityInFilteredList)
                .withName(CommandTestUtil.VALID_NAME_BOXING).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY,
                new EditActivityDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOXING).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ACTIVITY_SUCCESS, editedActivity);

        Model expectedModel = new ModelManager(new HobbyList(model.getHobbyList()), new UserPrefs());
        expectedModel.setActivity(model.getFilteredActivityList().get(0), editedActivity);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateActivityUnfilteredList_failure() {
        Activity firstActivity = model.getFilteredActivityList()
                .get(TypicalIndexes.INDEX_FIRST_ACTIVITY.getZeroBased());
        EditCommand.EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder(firstActivity).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_SECOND_ACTIVITY, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ACTIVITY);
    }

    @Test
    public void execute_duplicateActivityFilteredList_failure() {
        CommandTestUtil.showActivityAtIndex(model, TypicalIndexes.INDEX_FIRST_ACTIVITY);

        // edit activity in filtered list into a duplicate in HobbyList
        Activity activityInList = model.getHobbyList().getActivityList()
                .get(TypicalIndexes.INDEX_SECOND_ACTIVITY.getZeroBased());
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY,
                new EditActivityDescriptorBuilder(activityInList).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ACTIVITY);
    }

    @Test
    public void execute_invalidActivityIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredActivityList().size() + 1);
        EditCommand.EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOXING).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of HobbyList
     */
    @Test
    public void execute_invalidActivityIndexFilteredList_failure() {
        CommandTestUtil.showActivityAtIndex(model, TypicalIndexes.INDEX_FIRST_ACTIVITY);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_ACTIVITY;
        // ensures that outOfBoundIndex is still in bounds of HobbyList list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHobbyList().getActivityList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditActivityDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOXING).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
    }

    @Test
    public void setCommandWord_validWord_success() {
        EditCommand.setCommandWord("test");
        assertEquals(EditCommand.getCommandWord(), "test");
        EditCommand.setCommandWord("edit");
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY,
                CommandTestUtil.DESC_ANIME);

        // same values -> returns true
        EditCommand.EditActivityDescriptor copyDescriptor = new EditCommand
                .EditActivityDescriptor(CommandTestUtil.DESC_ANIME);
        EditCommand commandWithSameValues = new EditCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_SECOND_ACTIVITY,
                CommandTestUtil.DESC_ANIME)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY,
                CommandTestUtil.DESC_BOXING)));
    }

}
