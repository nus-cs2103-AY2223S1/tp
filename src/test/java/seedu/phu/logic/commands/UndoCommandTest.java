package seedu.phu.logic.commands;

import static seedu.phu.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.phu.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.phu.logic.commands.CommandTestUtil.deleteFirstInternship;
import static seedu.phu.logic.commands.CommandTestUtil.findFirstInternship;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternshipBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.phu.model.Model;
import seedu.phu.model.ModelManager;
import seedu.phu.model.UserPrefs;

public class UndoCommandTest {
    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
        commandHistory = new CommandHistory();
    }

    @Test
    public void execute_modifyCommandExecutedBefore_success() {
        // set up the model
        deleteFirstInternship(model, commandHistory);

        // set up the expected model
        deleteFirstInternship(expectedModel);

        expectedModel.undoInternshipBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory,
                String.format(UndoCommand.MESSAGE_SUCCESS, "delete 1"), expectedModel);
    }

    @Test
    public void execute_multipleModifyCommandsExecutedBefore_success() {
        // set up the model
        deleteFirstInternship(model, commandHistory);
        deleteFirstInternship(model, commandHistory);

        // set up the expected model
        deleteFirstInternship(expectedModel);
        deleteFirstInternship(expectedModel);

        expectedModel.undoInternshipBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory,
                String.format(UndoCommand.MESSAGE_SUCCESS, "delete 1"), expectedModel);

        expectedModel.undoInternshipBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory,
                String.format(UndoCommand.MESSAGE_SUCCESS, "delete 1"), expectedModel);
    }

    @Test
    public void execute_noModifyCommandExecutedBefore_throwsCommandException() {
        findFirstInternship(model);
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
    }

    @Test
    public void execute_noCommandExecutedBefore_throwsCommandException() {
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
    }
}
