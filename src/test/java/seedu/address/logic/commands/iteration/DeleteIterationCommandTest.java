package seedu.address.logic.commands.iteration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIterations.FINALISED;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Contains unit tests for {@code DeleteIterationCommandTest}.
 */
public class DeleteIterationCommandTest {
    @Test
    public void constructor_nullIteration_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteIterationCommand(null));
    }

    @Test
    public void execute_modelNoSelectedCommission_throwsCommandError() {
        DeleteIterationCommand deleteIterationCommand = new DeleteIterationCommand(INDEX_FIRST);
        assertThrows(CommandException.class, Messages.MESSAGE_NO_ACTIVE_COMMISSION, () ->
                deleteIterationCommand.execute(new ModelStubWithoutCommission(), null));
    }

    @Test
    public void execute_commissionWithNoIterations_throwsCommandException() {
        CommissionStubWithIteration commissionStub = new CommissionStubWithIteration();
        DeleteIterationCommand deleteIterationCommand = new DeleteIterationCommand(INDEX_FIRST);
        ModelStubWithCommission modelStub = new ModelStubWithCommission(commissionStub);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ITERATION_DISPLAYED_INDEX, () ->
                        deleteIterationCommand.execute(modelStub, null));
        assertEquals(commissionStub.getIterationsAsList(),
                modelStub.getSelectedCommissionStub().getIterationsAsList());
    }

    @Test
    public void execute_modelValidIndex_deleteSuccessful() throws CommandException {
        CommissionStubWithIteration commissionStub = new CommissionStubWithIteration();
        commissionStub.addIteration(FINALISED);
        ModelStubWithCommission modelStub = new ModelStubWithCommission(commissionStub);
        CommandResult commandResult = new DeleteIterationCommand(INDEX_FIRST).execute(modelStub, null);
        assertEquals(String.format(DeleteIterationCommand.MESSAGE_DELETE_ITERATION_SUCCESS, FINALISED),
                commandResult.getFeedbackToUser());
        assertEquals(commissionStub.getIterationsAsList(),
                modelStub.getSelectedCommissionStub().getIterationsAsList());
    }

    @Test
    public void equals() {
        DeleteIterationCommand firstDeleteIterationCommand = new DeleteIterationCommand(INDEX_FIRST);
        DeleteIterationCommand secondDeleteIterationCommand = new DeleteIterationCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(firstDeleteIterationCommand.equals(firstDeleteIterationCommand));

        // same values -> returns true
        DeleteIterationCommand firstDeleteIterationCommandCopy = new DeleteIterationCommand(INDEX_FIRST);
        assertTrue(firstDeleteIterationCommand.equals(firstDeleteIterationCommandCopy));

        // different types -> returns false
        assertFalse(firstDeleteIterationCommand.equals(1));

        // null -> returns false
        assertFalse(firstDeleteIterationCommand.equals(null));

        // different values -> returns false
        assertFalse(firstDeleteIterationCommand.equals(secondDeleteIterationCommand));
    }
}
