package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddMemberCommand}.
 */
// TODO: Add implementation for tests
public class AddMemberCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    @Test
    public void execute_invalidName_throwsCommandException() {
    }

    @Test
    public void equals() {
    }
}
