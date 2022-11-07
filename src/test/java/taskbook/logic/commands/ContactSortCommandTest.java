package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.contacts.ContactSortAddedChronologicalCommand;
import taskbook.logic.commands.contacts.ContactSortCommand;
import taskbook.logic.commands.contacts.ContactSortNameAlphabeticalCommand;
import taskbook.logic.commands.contacts.ContactSortNameReverseAlphabeticalCommand;
import taskbook.model.Model;
import taskbook.model.ModelManager;
import taskbook.model.UserPrefs;
import taskbook.testutil.TypicalTaskBook;

public class ContactSortCommandTest {

    @Test
    public void equals() {
        ContactSortAddedChronologicalCommand contactSortFirstCommand = new ContactSortAddedChronologicalCommand();
        ContactSortNameAlphabeticalCommand contactSortSecondCommand = new ContactSortNameAlphabeticalCommand();
        // same object -> returns true
        assertTrue(contactSortFirstCommand.equals(contactSortFirstCommand));

        // same command type -> returns true
        ContactSortAddedChronologicalCommand contactSortFirstCommandCopy = new ContactSortAddedChronologicalCommand();
        assertTrue(contactSortFirstCommand.equals(contactSortFirstCommandCopy));

        // different object types -> returns false
        assertFalse(contactSortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(contactSortFirstCommand.equals(null));

        // different sort type -> returns false
        assertFalse(contactSortFirstCommand.equals(contactSortSecondCommand));
    }

    @Test
    public void execute_contactSortAddedChronological_commandSuccess() {
        ContactSortAddedChronologicalCommand command = new ContactSortAddedChronologicalCommand();
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        model.updateSortedPersonList((p1, p2) -> -1);
        String expectedMessage = String.format(ContactSortCommand.MESSAGE_SORT_TASK_SUCCESS
                + ContactSortAddedChronologicalCommand.MESSAGE_SORT_TYPE);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_contactSortNameAlphabetical_commandSuccess() {
        ContactSortNameAlphabeticalCommand command = new ContactSortNameAlphabeticalCommand();
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        expectedModel.updateSortedPersonList((p1, p2) -> p1.compareByNameAlphabeticalTo(p2));
        String expectedMessage = String.format(ContactSortCommand.MESSAGE_SORT_TASK_SUCCESS
                + ContactSortNameAlphabeticalCommand.MESSAGE_SORT_TYPE);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
    //NOTE: The following tests merely test to see if the commands execute, but do not test the actual ordering!
    @Test
    public void execute_contactSortNameReverseAlphabetical_commandSuccess() {
        ContactSortNameReverseAlphabeticalCommand command = new ContactSortNameReverseAlphabeticalCommand();
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        expectedModel.updateSortedPersonList((p1, p2) -> p1.compareByNameReverseAlphabeticalTo(p2));
        String expectedMessage = String.format(ContactSortCommand.MESSAGE_SORT_TASK_SUCCESS
                + ContactSortNameReverseAlphabeticalCommand.MESSAGE_SORT_TYPE);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
