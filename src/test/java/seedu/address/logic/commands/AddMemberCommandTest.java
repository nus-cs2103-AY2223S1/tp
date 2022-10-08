package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddMemberCommand}.
 */
public class AddMemberCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 1);
        AddMemberCommand addMemberCommand = new AddMemberCommand(outOfBoundIndex);

        assertCommandFailure(addMemberCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AddMemberCommand addMemberFirstCommand = new AddMemberCommand(INDEX_FIRST_PERSON);
        AddMemberCommand addMemberSecondCommand = new AddMemberCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(addMemberFirstCommand.equals(addMemberFirstCommand));

        // same values -> returns true
        AddMemberCommand addMemberFirstCommandCopy = new AddMemberCommand(INDEX_FIRST_PERSON);
        assertTrue(addMemberFirstCommand.equals(addMemberFirstCommandCopy));

        // different types -> returns false
        assertFalse(addMemberFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addMemberFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(addMemberFirstCommand.equals(addMemberSecondCommand));
    }
}
