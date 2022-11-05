package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListMembersCommand.
 */
// TODO: Add implementation for tests
public class ListMembersCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
        expectedModel = new ModelManager(model.getTruthTable(), new UserPrefs());
    }

    @Test
    public void execute_listMembers_success() {
        //TODO implement tests
    }
}
