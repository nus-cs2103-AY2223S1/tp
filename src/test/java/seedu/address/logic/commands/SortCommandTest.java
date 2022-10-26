package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalTeachersPet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand.Order;
import seedu.address.logic.commands.SortCommand.Type;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalTeachersPet(), new UserPrefs());

    @Test
    public void execute_validSort_success() {
        SortCommand sortCommand = new SortCommand(Type.NAME, Order.ASC);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getTeachersPet(), new UserPrefs());
        expectedModel.sortPersons(SortCommand.generateComparator(Type.NAME, Order.ASC));
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SortCommand sortCommand1 = new SortCommand(Type.NAME, Order.ASC);
        SortCommand sortCommand2 = new SortCommand(Type.NAME, Order.DESC);

        // same object -> returns true
        assertTrue(sortCommand1.equals(sortCommand1));

        // same value -> returns true
        assertTrue(sortCommand1.equals(new SortCommand(Type.NAME, Order.ASC)));

        // different types -> returns false
        assertFalse(sortCommand1.equals(1));

        // null -> returns false
        assertFalse(sortCommand1.equals(null));

        // different values -> returns false
        assertFalse(sortCommand1.equals(sortCommand2));
    }
}
