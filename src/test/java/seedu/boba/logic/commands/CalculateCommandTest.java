package seedu.boba.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;

import org.junit.jupiter.api.Test;

import seedu.boba.commons.core.Messages;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.UserPrefs;

/**
 * Contains integration tests (interaction with the BobaBotModel) for {@code FindCommand}.
 */
public class CalculateCommandTest {
    private BobaBotModel bobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
    private BobaBotModel expectedBobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

    @Test
    public void equals() {
        String exp1 = "1+1";
        String exp2 = "2+2";

        CalculateCommand cmd1 = new CalculateCommand(exp1);
        CalculateCommand cmd2 = new CalculateCommand(exp2);

        // same objects -> true
        assertEquals(cmd1, cmd1);

        // same expression -> true
        assertEquals(cmd1, new CalculateCommand(exp1));

        // different expression -> false
        assertNotEquals(cmd1, cmd2);

        // null -> false
        assertNotEquals(cmd1, null);

    }

    @Test
    public void execute_validExpression_success() {
        String expectedMessage = CalculateCommand.FEEDBACK_HEADER + "1+1 = 2";
        String exp = "1+1";
        CalculateCommand calculateCommand = new CalculateCommand(exp);

        assertCommandSuccess(calculateCommand, bobaBotModel, expectedMessage, expectedBobaBotModel);
    }

    @Test
    public void execute_invalidExpression_throwsCommandException() {
        String exp = "2-+4-*1-8";
        CalculateCommand calculateCommand = new CalculateCommand(exp);

        assertCommandFailure(calculateCommand, bobaBotModel, Messages.MESSAGE_INVALID_ARITHMETIC_EXPRESSION);
    }

    @Test
    public void execute_emptyExpression_throwsException() {
        String exp = "  ";
        CalculateCommand calculateCommand = new CalculateCommand(exp);

        assertCommandFailure(calculateCommand, bobaBotModel, Messages.MESSAGE_INVALID_ARITHMETIC_EXPRESSION);
    }

    @Test
    public void execute_unsupportedOperator_fail() {
        String exp = "2^^30";
        CalculateCommand calculateCommand = new CalculateCommand(exp);

        assertCommandFailure(calculateCommand, bobaBotModel, Messages.MESSAGE_INVALID_ARITHMETIC_EXPRESSION);
    }
}

