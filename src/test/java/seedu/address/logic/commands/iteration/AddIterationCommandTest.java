package seedu.address.logic.commands.iteration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIterations.ADD_COLOR;
import static seedu.address.testutil.TypicalIterations.FINALISED;
import static seedu.address.testutil.TypicalIterations.REMOVE_CHARACTER;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Contains unit tests for {@code AddIterationCommandTest}.
 */
public class AddIterationCommandTest {

    @Test
    public void constructor_nullIteration_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddIterationCommand(null));
    }

    @Test
    public void execute_modelNoSelectedCommission_throwsCommandException() {
        AddIterationCommand addIterationCommand = new AddIterationCommand(FINALISED);
        assertThrows(CommandException.class, Messages.MESSAGE_NO_ACTIVE_COMMISSION, () ->
                addIterationCommand.execute(new ModelStubWithoutCommission()));
    }

    @Test
    public void execute_validIterationAcceptedByModel_addSuccessful() throws CommandException {
        CommissionStubWithIteration selectedCommission = new CommissionStubWithIteration();
        ModelStubWithCommission modelStub = new ModelStubWithCommission(selectedCommission);

        CommandResult commandResult = new AddIterationCommand(ADD_COLOR).execute(modelStub);

        assertEquals(String.format(AddIterationCommand.MESSAGE_ADD_ITERATION_SUCCESS,
                        ADD_COLOR, selectedCommission.getTitle()),
                commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(ADD_COLOR), selectedCommission.getIterationsAsList());
    }

    @Test
    public void execute_duplicateIteration_throwsCommandException() {
        CommissionStubWithIteration selectedCommission = new CommissionStubWithIteration();
        selectedCommission.addIteration(REMOVE_CHARACTER);
        ModelStubWithCommission modelStub = new ModelStubWithCommission(selectedCommission);

        AddIterationCommand addIterationCommand = new AddIterationCommand(REMOVE_CHARACTER);
        assertThrows(CommandException.class,
                String.format(AddIterationCommand.MESSAGE_DUPLICATE_ITERATION,
                        selectedCommission.getTitle()), () -> addIterationCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddIterationCommand firstAddIterationCommand = new AddIterationCommand(FINALISED);
        AddIterationCommand secondAddIterationCommand = new AddIterationCommand(ADD_COLOR);

        // same object -> returns true
        assertTrue(firstAddIterationCommand.equals(firstAddIterationCommand));

        // same values -> returns true
        AddIterationCommand firstAddIterationCommandCopy = new AddIterationCommand(FINALISED);
        assertTrue(firstAddIterationCommand.equals(firstAddIterationCommandCopy));

        // different types -> returns false
        assertNotEquals(1, firstAddIterationCommand);

        // null -> returns false
        assertNotEquals(null, firstAddIterationCommand);

        // different fields -> returns false
        assertNotEquals(firstAddIterationCommand, secondAddIterationCommand);
    }
}
