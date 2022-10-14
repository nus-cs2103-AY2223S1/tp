package seedu.address.logic.commands.iteration;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.AMY;
import static seedu.address.testutil.TypicalIterations.ADD_COLOR;
import static seedu.address.testutil.TypicalIterations.FINALISED;
import static seedu.address.testutil.TypicalIterations.REMOVE_CHARACTER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.ObservableObject;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CommissionStub;
import seedu.address.model.ModelStub;
import seedu.address.model.commission.Commission;
import seedu.address.model.commission.Title;
import seedu.address.model.iteration.Iteration;
import seedu.address.model.iteration.UniqueIterationList;

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

    /**
     * A Model stub that does not have a commission.
     */
    private static class ModelStubWithoutCommission extends ModelStub {
        @Override
        public boolean hasSelectedCommission() {
            return false;
        }
    }

    /**
     * A Model stub that contains a single commission.
     */
    private static class ModelStubWithCommission extends ModelStub {
        private final ObservableObject<CommissionStubWithIteration> commission;

        ModelStubWithCommission(CommissionStubWithIteration commission) {
            requireNonNull(commission);
            this.commission = new ObservableObject<>(commission);
        }

        @Override
        public boolean hasSelectedCommission() {
            return true;
        }

        @Override
        public ObservableObject<Commission> getSelectedCommission() {
            return new ObservableObject<>(commission.getValue());
        }
    }

    private static class CommissionStubWithIteration extends CommissionStub {
        static final String COMMISSION_TITLE = "Mosquito";
        final ArrayList<Iteration> iterationsAdded = new ArrayList<>();

        CommissionStubWithIteration() {
            super(new seedu.address.testutil.CommissionBuilder()
                    .withTitle(COMMISSION_TITLE).toCommissionBuilder(), AMY);
        }

        @Override
        public Title getTitle() {
            return new Title(COMMISSION_TITLE);
        }

        @Override
        public boolean hasIteration(Iteration toAdd) {
            return iterationsAdded.stream().anyMatch(toAdd::isSameIteration);
        }

        @Override
        public void addIteration(Iteration toAdd) {
            iterationsAdded.add(toAdd);
        }

        @Override
        public UniqueIterationList getIterations() {
            UniqueIterationList result = new UniqueIterationList();
            result.setIterations(iterationsAdded);
            return result;
        }

        List<Iteration> getIterationsAsList() {
            return iterationsAdded;
        }
    }
}
