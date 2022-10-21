package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.logic.commands.CommandTestUtil.assertCommandFailure;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.logic.commands.CommandTestUtil.showTutorAtIndex;
import static tuthub.logic.commands.ViewCommand.MESSAGE_VIEW_TUTOR_SUCCESS;
import static tuthub.testutil.TypicalIndexes.INDEX_FIRST_TUTOR;
import static tuthub.testutil.TypicalIndexes.INDEX_SECOND_TUTOR;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import org.junit.jupiter.api.Test;

import tuthub.commons.core.Messages;
import tuthub.commons.core.index.Index;
import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.Tutor;

public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalTuthub(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Tutor tutorToView = model.getFilteredTutorList().get(INDEX_FIRST_TUTOR.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_TUTOR);

        String expectedMessage = String.format(MESSAGE_VIEW_TUTOR_SUCCESS, tutorToView);

        ModelManager expectedModel = new ModelManager(model.getTuthub(), new UserPrefs());
        expectedModel.setTutorToView(tutorToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTutorAtIndex(model, INDEX_FIRST_TUTOR);

        Tutor tutorToView = model.getFilteredTutorList().get(INDEX_FIRST_TUTOR.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_TUTOR);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_TUTOR_SUCCESS, tutorToView);

        Model expectedModel = new ModelManager(model.getTuthub(), new UserPrefs());
        showTutorAtIndex(expectedModel, INDEX_FIRST_TUTOR);
        expectedModel.setTutorToView(tutorToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTutorAtIndex(model, INDEX_FIRST_TUTOR);

        Index outOfBoundIndex = INDEX_SECOND_TUTOR;
        // ensures that outOfBoundIndex is still in bounds of tuthub list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTuthub().getTutorList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
    }
}
