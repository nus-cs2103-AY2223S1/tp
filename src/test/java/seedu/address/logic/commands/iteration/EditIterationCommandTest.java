package seedu.address.logic.commands.iteration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CAT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_COLOR;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FINALISE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_IMAGEPATH_COLOR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import java.nio.file.Path;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommissionCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.iteration.EditIterationCommand.EditIterationDescriptor;
import seedu.address.model.Model;
import seedu.address.model.iteration.Iteration;
import seedu.address.storage.Storage;
import seedu.address.testutil.EditIterationDescriptorBuilder;
import seedu.address.testutil.IterationBuilder;
import seedu.address.testutil.TypicalIterations;

/**
 * Contains tests for {@code EditIterationCommandTest}.
 */
public class EditIterationCommandTest {
    private final Model model = getSetUpModelManager();
    private final Storage storage = new StorageWithImageStub();

    @Test
    public void execute_allFieldsSpecifiedSame_success() throws CommandException {
        Iteration iterationToEdit =
                model.getSelectedCommission().getValue().getIterationList().get(INDEX_FIRST.getZeroBased());
        Iteration editedIteration = new IterationBuilder(iterationToEdit).build();
        EditIterationDescriptor descriptor =
                new EditIterationDescriptorBuilder(editedIteration).build();
        EditIterationCommand editIterationCommand = new EditIterationCommand(INDEX_FIRST, descriptor);
        String expectedMessage = String.format(EditIterationCommand.MESSAGE_EDIT_ITERATION_SUCCESS, editedIteration);
        CommandResult commandResult = editIterationCommand.execute(model, storage);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(editedIteration,
                model.getSelectedCommission().getValue().getIterationList().get(INDEX_FIRST.getZeroBased()));
    }

    @Test
    public void execute_allFieldsSpecifiedChanged_success() throws CommandException {
        Iteration editedIteration = new IterationBuilder(TypicalIterations.ADD_COLOR)
                .withImagePath(VALID_ITERATION_IMAGEPATH_COLOR).build();
        EditIterationDescriptor descriptor =
                new EditIterationDescriptorBuilder(editedIteration).build();
        EditIterationCommand editIterationCommand = new EditIterationCommand(INDEX_FIRST, descriptor);
        String expectedMessage = String.format(EditIterationCommand.MESSAGE_EDIT_ITERATION_SUCCESS, editedIteration);
        CommandResult commandResult = editIterationCommand.execute(model, storage);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(editedIteration,
                model.getSelectedCommission().getValue().getIterationList().get(INDEX_FIRST.getZeroBased()));
    }

    @Test
    public void execute_someFieldsSpecified_success() throws CommandException {
        LocalDate editedDate = LocalDate.of(2022, 10, 25);
        Iteration iterationToEdit =
                model.getSelectedCommission().getValue().getIterationList().get(INDEX_FIRST.getZeroBased());
        IterationBuilder iterationInList = new IterationBuilder(iterationToEdit);
        Iteration expectedEditedIteration = iterationInList.withDate(editedDate).build();

        EditIterationDescriptor descriptor = new EditIterationDescriptorBuilder().withDate(editedDate).build();
        EditIterationCommand editIterationCommand = new EditIterationCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditIterationCommand.MESSAGE_EDIT_ITERATION_SUCCESS,
                expectedEditedIteration);

        CommandResult commandResult = editIterationCommand.execute(model, storage);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedEditedIteration,
                model.getSelectedCommission().getValue().getIterationList().get(INDEX_FIRST.getZeroBased()));
    }

    @Test
    public void execute_noFieldSpecified_success() throws CommandException {
        Iteration iterationToEdit =
                model.getSelectedCommission().getValue().getIterationList().get(INDEX_FIRST.getZeroBased());
        EditIterationCommand editIterationCommand = new EditIterationCommand(INDEX_FIRST,
                new EditIterationDescriptor());
        String expectedMessage = String.format(EditIterationCommand.MESSAGE_EDIT_ITERATION_SUCCESS, iterationToEdit);
        CommandResult commandResult = editIterationCommand.execute(model, storage);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(iterationToEdit,
                model.getSelectedCommission().getValue().getIterationList().get(INDEX_FIRST.getZeroBased()));
    }

    @Test
    public void execute_noSelectedCommission_failure() {
        EditIterationCommand editIterationCommand = new EditIterationCommand(INDEX_FIRST, DESC_FINALISE);
        assertThrows(CommandException.class, Messages.MESSAGE_NO_ACTIVE_COMMISSION, () ->
                editIterationCommand.execute(new ModelStubWithoutCommission(), storage));
    }

    @Test
    public void execute_invalidImagePath_failure() {
        EditIterationDescriptor editIterationDescriptor =
                new EditIterationDescriptorBuilder().withPath(Path.of(System.getProperty("user.dir"))).build();
        EditIterationCommand editIterationCommand = new EditIterationCommand(INDEX_FIRST, editIterationDescriptor);
        assertThrows(CommandException.class, () -> editIterationCommand.execute(model, storage));
    }

    @Test
    public void execute_duplicateIteration_failure() {
        ObservableList<Iteration> iterations = model.getSelectedCommission().getValue().getIterationList();
        Iteration iterationToDuplicate = iterations.get(INDEX_FIRST.getZeroBased());
        Iteration iterationToEdit = iterations.get(INDEX_SECOND.getZeroBased());
        EditIterationDescriptor descriptor = new EditIterationDescriptorBuilder(iterationToDuplicate).build();
        EditIterationCommand editIterationCommand = new EditIterationCommand(INDEX_SECOND, descriptor);

        assertThrows(CommandException.class,
                EditIterationCommand.MESSAGE_DUPLICATE_ITERATION, () -> editIterationCommand.execute(model, storage));
        assertEquals(iterationToEdit, iterations.get(INDEX_SECOND.getZeroBased()));
    }

    @Test
    public void execute_invalidIterationIndex_failure() {
        ObservableList<Iteration> uneditedIterations = model.getSelectedCommission().getValue().getIterationList();
        Index outOfBoundIndex = INDEX_THIRD;
        assertTrue(outOfBoundIndex.getOneBased() > uneditedIterations.size());
        EditIterationCommand editIterationCommand = new EditIterationCommand(outOfBoundIndex,
                new EditIterationDescriptorBuilder().build());
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_ITERATION_DISPLAYED_INDEX, () -> editIterationCommand
                        .execute(model, storage));
        assertEquals(uneditedIterations, model.getSelectedCommission().getValue().getIterationList());
    }

    @Test
    public void equals() {
        final EditIterationCommand standardCommand = new EditIterationCommand(INDEX_FIRST, DESC_FINALISE);

        // same values -> returns true
        EditIterationDescriptor copyDescriptor = new EditIterationDescriptor(DESC_FINALISE);
        EditIterationCommand commandWithSameValues = new EditIterationCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new EditCommissionCommand(INDEX_FIRST, DESC_CAT)));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditIterationCommand(INDEX_SECOND, DESC_FINALISE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditIterationCommand(INDEX_FIRST, DESC_COLOR)));
    }

    private static Model getSetUpModelManager() {
        CommissionStubWithIteration commissionStubWithIteration = new CommissionStubWithIteration();
        commissionStubWithIteration.addIteration(TypicalIterations.FINALISED);
        commissionStubWithIteration.addIteration(TypicalIterations.REMOVE_CHARACTER);
        ModelStubWithCommission modelStubWithCommission = new ModelStubWithCommission(commissionStubWithIteration);
        return modelStubWithCommission;
    }
}
