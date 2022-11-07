package seedu.hrpro.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hrpro.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.hrpro.testutil.TypicalHrPro.getTypicalHrPro;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.core.Messages;
import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.ModelManager;
import seedu.hrpro.model.UserPrefs;
import seedu.hrpro.model.project.Project;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewCommand}.
 */
public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalHrPro(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Project projectToView = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_PROJECT);

        String expectedMessage = String.format(ViewCommand
                .MESSAGE_SUCCESS, projectToView);

        ModelManager expectedModel = new ModelManager(model.getHrPro(), new UserPrefs());
        expectedModel.setFilteredStaffList(projectToView.getStaffList());

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Project projectToView = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_PROJECT);

        String expectedMessage = String.format(ViewCommand.MESSAGE_SUCCESS, projectToView);

        Model expectedModel = new ModelManager(model.getHrPro(), new UserPrefs());
        expectedModel.setFilteredStaffList(projectToView.getStaffList());
        showProjectAtIndex(expectedModel, INDEX_FIRST_PROJECT);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Index outOfBoundIndex = INDEX_SECOND_PROJECT;
        // ensures that outOfBoundIndex is still in bounds of hr pro list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHrPro().getProjectList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_PROJECT);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_PROJECT);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_PROJECT);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different project -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
