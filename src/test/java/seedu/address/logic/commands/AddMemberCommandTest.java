package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddMemberCommand.MESSAGE_PERSON_NOT_EXISTS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddMemberCommand}.
 */
public class AddMemberCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidName_throwsCommandException() {
        AddMemberCommand addMemberCommand = new AddMemberCommand(new Name(INVALID_NAME));

        assertCommandFailure(addMemberCommand, model, MESSAGE_PERSON_NOT_EXISTS);
    }

    @Test
    public void equals() {
        AddMemberCommand addMemberFirstCommand = new AddMemberCommand(new Name(VALID_NAME_AMY));
        AddMemberCommand addMemberSecondCommand = new AddMemberCommand(new Name(VALID_NAME_BOB));

        // same object -> returns true
        assertTrue(addMemberFirstCommand.equals(addMemberFirstCommand));

        // same values -> returns true
        AddMemberCommand addMemberFirstCommandCopy = new AddMemberCommand(new Name(VALID_NAME_AMY));
        assertTrue(addMemberFirstCommand.equals(addMemberFirstCommandCopy));

        // different types -> returns false
        assertFalse(addMemberFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addMemberFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(addMemberFirstCommand.equals(addMemberSecondCommand));
    }
}
