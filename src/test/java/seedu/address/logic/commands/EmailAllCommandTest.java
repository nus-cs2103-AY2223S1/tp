package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;

class EmailAllCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmailAllCommand(null));
    }

    @Test
    public void execute_help_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Group invalidGroup = new Group("invalidGroup");

        EmailAllCommand command = new EmailAllCommand(invalidGroup);

        assertCommandFailure(command, model, EmailAllCommand.MESSAGE_GROUP_NOT_FOUND);
    }

    @Test
    public void equals() {
        Group group1 = new Group("group1");
        Group group2 = new Group("group2");

        EmailAllCommand command1 = new EmailAllCommand(group1);
        EmailAllCommand command2 = new EmailAllCommand(group2);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same values -> returns true
        EmailAllCommand command1copy = new EmailAllCommand(group1);
        assertTrue(command1.equals(command1copy));

        // different types -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different person -> returns false
        assertFalse(command1.equals(command2));

    }

}
