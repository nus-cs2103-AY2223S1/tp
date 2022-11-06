package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RenameCommand}.
 */
public class RenameCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final String firstNewName = "brandNewName";
    private final String secondNewName = "brandNewName2";

    @Test
    public void execute_nullFilePath_nullPointerException() {
        assertThrows(NullPointerException.class, () -> new RenameCommand(null));
    }


    @Test
    public void equals() {
        RenameCommand showFirstCommand = new RenameCommand(firstNewName);
        RenameCommand showSecondCommand = new RenameCommand(secondNewName);

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        RenameCommand showFirstCommandCopy = new RenameCommand(firstNewName);
        assertTrue(showFirstCommand.equals(showFirstCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }
}
