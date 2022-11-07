package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;

class GroupCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupCommand(null));
    }

    @Test
    public void execute_invalidGroup_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Group invalidGroup = new Group("invalidGroup");

        GroupCommand command = new GroupCommand(invalidGroup);

        assertCommandFailure(command, model, GroupCommand.MESSAGE_SHOW_GROUP_FAIL);
    }

    @Test
    public void execute_group_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Group groupToDisplay = new Group("friends");
        CommandResult expectedCommandResult = new CommandResult(GroupCommand.MESSAGE_SHOWING_GROUP, true,
                groupToDisplay);

        assertCommandSuccess(new GroupCommand(groupToDisplay), model, expectedCommandResult, expectedModel);
    }

    @Test
    void equals() {
        Group group1 = new Group("test1");
        Group group2 = new Group("test2");

        GroupCommand groupCommand1 = new GroupCommand(group1);
        GroupCommand groupCommand2 = new GroupCommand(group2);

        // same object -> returns true
        assertTrue(groupCommand1.equals(groupCommand1));

        // same values -> returns true
        GroupCommand groupCommand1Copy = new GroupCommand(group1);
        assertTrue(groupCommand1.equals(groupCommand1Copy));

        // different types -> returns false
        assertFalse(groupCommand1.equals(1));

        // null -> returns false
        assertFalse(groupCommand1.equals(null));

        // different person -> returns false
        assertFalse(groupCommand1.equals(groupCommand2));
    }
}
