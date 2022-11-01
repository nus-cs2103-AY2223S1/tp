package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePersonCommand}.
 */
// TODO: Add implementation for tests
public class DeletePersonCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
    }

    @Test
    public void execute_validIndexFilteredList_success() {
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
    }

    @Test
    public void equals() {
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
