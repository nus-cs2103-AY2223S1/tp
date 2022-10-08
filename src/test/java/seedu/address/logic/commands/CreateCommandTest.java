package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.getTypicalJeeqTracker;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;
import static seedu.address.testutil.TypicalPoc.ALICE;
import static seedu.address.testutil.TypicalPoc.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class CreateCommandTest {

    private final Model model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());

    @Test
    public void constructor_nullPoc_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(INDEX_FIRST_COMPANY, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(null, ALICE));
    }

    @Test
    public void execute_addPoc_success() throws Exception {

    }

    @Test
    public void execute_duplicatePoc_failure() throws CommandException {

    }

    @Test
    public void execute_invalidCompanyIndex_failure() throws CommandException {

    }

    @Test
    public void equals() {
        CreateCommand createCommand = new CreateCommand(INDEX_FIRST_COMPANY, ALICE);

        // same values -> returns true
        CreateCommand createCommandCopy = new CreateCommand(INDEX_FIRST_COMPANY, ALICE);
        assertTrue(createCommand.equals(createCommandCopy));

        // same object -> returns true
        assertTrue(createCommand.equals(createCommand));

        // null -> returns false
        assertFalse(createCommand.equals(null));

        // different types -> returns false
        assertFalse(createCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(createCommand.equals(new CreateCommand(INDEX_SECOND_COMPANY, ALICE)));

        // different Poc -> returns false
        assertFalse(createCommand.equals(new CreateCommand(INDEX_FIRST_COMPANY, BOB)));
    }

}
