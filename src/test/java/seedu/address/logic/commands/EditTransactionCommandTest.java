package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalClients.getTypicalJeeqTracker;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditTransactionCommandTest {

    private Model model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());

}
