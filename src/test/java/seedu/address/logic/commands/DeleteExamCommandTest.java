package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteExamCommand}.
 */
public class DeleteExamCommandTest {
//    private Model model = new ModelManager()

    @Test
    public void equals() {
        DeleteExamCommand deleteFirstExamCommand = new DeleteExamCommand(FIRST_INDEX);
        DeleteExamCommand deleteSecondExamCommand = new DeleteExamCommand(SECOND_INDEX);

        // same object -> returns true
        assertTrue(deleteFirstExamCommand.equals(deleteFirstExamCommand));

        // same values -> returns true
        DeleteExamCommand deleteFirstExamCommandCopy = new DeleteExamCommand(FIRST_INDEX);
        assertTrue(deleteFirstExamCommand.equals(deleteFirstExamCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstExamCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstExamCommand.equals(null));

        // different exam -> returns false
        assertFalse(deleteFirstExamCommand.equals(deleteSecondExamCommand));
    }
}
