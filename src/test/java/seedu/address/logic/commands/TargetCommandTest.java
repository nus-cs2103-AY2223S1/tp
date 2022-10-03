package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Calorie;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code TargetCommand}.
 */
public class TargetCommandTest {
    private static final Calorie FIVE_CALORIE = new Calorie("5");
    private static final Calorie TEN_CALORIE = new Calorie("10");
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_validTargetValueSuccess() {
        TargetCommand targetCommand = new TargetCommand(FIVE_CALORIE);
        String expectedMessage = String.format(TargetCommand.MESSAGE_TARGET_SET_SUCCESS, FIVE_CALORIE);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setCalorieTarget(FIVE_CALORIE);
        assertCommandSuccess(targetCommand, model, expectedMessage, expectedModel);

    }

    @Test
    void testEquals() {
        // same target -> true
        TargetCommand targetCommand = new TargetCommand(FIVE_CALORIE);
        TargetCommand targetCommand1 = new TargetCommand(FIVE_CALORIE);
        assertTrue(targetCommand.equals(targetCommand1));

        // different target -> false
        TargetCommand targetCommand2 = new TargetCommand(TEN_CALORIE);
        assertFalse(targetCommand.equals(targetCommand2));

    }
}
