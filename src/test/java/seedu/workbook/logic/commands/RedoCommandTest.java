package seedu.workbook.logic.commands;

import static seedu.workbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.workbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.workbook.logic.commands.CommandTestUtil.deleteFirstInternship;
import static seedu.workbook.testutil.TypicalInternships.getTypicalWorkBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.workbook.model.Model;
import seedu.workbook.model.ModelManager;
import seedu.workbook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RedoCommand.
 */
public class RedoCommandTest {

    private final Model model = new ModelManager(getTypicalWorkBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalWorkBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        // set up of both models' undo/redo history
        deleteFirstInternship(model);
        deleteFirstInternship(model);
        model.undoWorkBook();
        model.undoWorkBook();

        deleteFirstInternship(expectedModel);
        deleteFirstInternship(expectedModel);
        expectedModel.undoWorkBook();
        expectedModel.undoWorkBook();
    }

    @Test
    public void execute() {
        // multiple redoable states in model
        expectedModel.redoWorkBook();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // single redoable state in model
        expectedModel.redoWorkBook();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // no redoable state in model
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_FAILURE);
    }
}
