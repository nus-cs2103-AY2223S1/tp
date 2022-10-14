package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.group.testutil.TypicalGroups.ORAL_PRESENTATION;
import static seedu.address.model.group.testutil.TypicalGroups.TEAM_PROJECT;
import static seedu.address.model.group.testutil.TypicalGroups.getTypicalAddressBookWithGroups;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.FullGroupNamePredicate;

public class DisplayGroupCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    @Test
    public void execute_validGroup_success() {
        FullGroupNamePredicate predicate = new FullGroupNamePredicate(TEAM_PROJECT.getName().toString());
        DisplayGroupCommand displayGroupCommand = new DisplayGroupCommand(predicate);

        String expectedMessage = DisplayGroupCommand.MESSAGE_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredGroupList(predicate);

        assertCommandSuccess(displayGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidGroup_throwsCommandException() {
        String name = "Group Does Not Exist";
        FullGroupNamePredicate predicate = new FullGroupNamePredicate(name);
        DisplayGroupCommand displayGroupCommand = new DisplayGroupCommand(predicate);

        assertCommandFailure(displayGroupCommand, model, Messages.MESSAGE_INVALID_GROUP_NAME);
    }

    @Test
    public void equals() {
        DisplayGroupCommand displayFirstCommand = new DisplayGroupCommand(
            new FullGroupNamePredicate(TEAM_PROJECT.getName().toString()));
        DisplayGroupCommand displaySecondCommand = new DisplayGroupCommand(
            new FullGroupNamePredicate(ORAL_PRESENTATION.getName().toString()));

        // same object -> returns true
        assertTrue(displayFirstCommand.equals(displayFirstCommand));

        // same values -> returns true
        DisplayGroupCommand displayFirstCommandCopy = new DisplayGroupCommand(
            new FullGroupNamePredicate(TEAM_PROJECT.getName().toString()));
        assertTrue(displayFirstCommand.equals(displayFirstCommandCopy));

        // different types -> returns false
        assertFalse(displayFirstCommand.equals(1));

        // null -> returns false
        assertFalse(displayFirstCommand.equals(null));

        // different group -> returns false
        assertFalse(displayFirstCommand.equals(displaySecondCommand));
    }


}
