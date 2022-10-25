package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.logic.commands.SortCommand.TYPE;
import seedu.address.logic.commands.SortCommand.ORDER;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalTeachersPet;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalTeachersPet(), new UserPrefs());

    @Test
    public void execute_validSort_success() {
        SortCommand sortCommand = new SortCommand(TYPE.NAME, ORDER.ASC);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getTeachersPet(), new UserPrefs());
        expectedModel.sortPersons(SortCommand.generateComparator(TYPE.NAME, ORDER.ASC));
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SortCommand sortCommand1 = new SortCommand(TYPE.NAME, ORDER.ASC);
        SortCommand sortCommand2 = new SortCommand(TYPE.NAME, ORDER.DESC);

        // same object -> returns true
        assertTrue(sortCommand1.equals(sortCommand1));

        // same value -> returns true
        assertTrue(sortCommand1.equals(new SortCommand(TYPE.NAME, ORDER.ASC)));

        // different types -> returns false
        assertFalse(sortCommand1.equals(1));

        // null -> returns false
        assertFalse(sortCommand1.equals(null));

        // different values -> returns false
        assertFalse(sortCommand1.equals(sortCommand2));
    }
}
