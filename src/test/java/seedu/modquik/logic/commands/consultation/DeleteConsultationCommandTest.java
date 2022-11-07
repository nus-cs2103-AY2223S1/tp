package seedu.modquik.logic.commands.consultation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modquik.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modquik.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modquik.testutil.TypicalConsultations.getTypicalModQuik;
import static seedu.modquik.testutil.TypicalIndexes.INDEX_FIRST_CONSULTATION;
import static seedu.modquik.testutil.TypicalIndexes.INDEX_SECOND_CONSULTATION;

import org.junit.jupiter.api.Test;

import seedu.modquik.commons.core.Messages;
import seedu.modquik.commons.core.index.Index;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelManager;
import seedu.modquik.model.ModelType;
import seedu.modquik.model.UserPrefs;
import seedu.modquik.model.consultation.Consultation;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteConsultationCommand}.
 */
public class DeleteConsultationCommandTest {
    private Model model = new ModelManager(getTypicalModQuik(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Consultation consultationToDelete = model.getFilteredConsultationList().get(INDEX_FIRST_CONSULTATION
                .getZeroBased());
        DeleteConsultationCommand deleteConsultationCommand = new DeleteConsultationCommand(INDEX_FIRST_CONSULTATION);

        String expectedMessage = String.format(DeleteConsultationCommand.MESSAGE_DELETE_CONSULTATION_SUCCESS,
                consultationToDelete);

        ModelManager expectedModel = new ModelManager(model.getModQuik(), new UserPrefs());
        expectedModel.deleteConsultation(consultationToDelete);

        assertCommandSuccess(deleteConsultationCommand, model, expectedMessage, ModelType.CONSULTATION, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredConsultationList().size() + 1);
        DeleteConsultationCommand deleteConsultationCommand = new DeleteConsultationCommand(outOfBoundIndex);

        assertCommandFailure(deleteConsultationCommand, model, Messages.MESSAGE_INVALID_CONSULTATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteConsultationCommand deleteFirstConsultationCommand =
                new DeleteConsultationCommand(INDEX_FIRST_CONSULTATION);
        DeleteConsultationCommand deleteSecondConsultationCommand =
                new DeleteConsultationCommand(INDEX_SECOND_CONSULTATION);

        // same object -> returns true
        assertTrue(deleteFirstConsultationCommand.equals(deleteFirstConsultationCommand));

        // same values -> returns true
        DeleteConsultationCommand deleteFirstConsultationCommandCopy =
                new DeleteConsultationCommand(INDEX_FIRST_CONSULTATION);
        assertTrue(deleteFirstConsultationCommand.equals(deleteFirstConsultationCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstConsultationCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstConsultationCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstConsultationCommand.equals(deleteSecondConsultationCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoConsultation(Model model) {
        model.updateFilteredConsultationList(p -> false);

        assertTrue(model.getFilteredConsultationList().isEmpty());
    }
}
