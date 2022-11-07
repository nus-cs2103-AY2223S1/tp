package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalTeachersPet;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalTeachersPet(), new UserPrefs());

    @Test
    public void execute_success() {
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_SUCCESS;
        // history of two commands added: the first one is an arbitrary command, the second is the undo command
        model.updateTeachersPetHistory();
        model.updateTeachersPetHistory();
        ModelManager expectedModel = new ModelManager(getTypicalTeachersPet(), new UserPrefs());
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        assertTrue(new UndoCommand().equals(new UndoCommand()));
    }
}
