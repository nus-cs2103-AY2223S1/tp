package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

// TODO: Add implementation for tests
class AddTaskCommandTest {

    private static final String PLACEHOLDER_TASK_NAME = "Test Task";
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    @Test
    public void equals() {
    }
}
