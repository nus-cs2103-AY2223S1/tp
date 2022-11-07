package seedu.phu.logic.commands;

import static seedu.phu.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternshipBook;

import org.junit.jupiter.api.Test;

import seedu.phu.model.InternshipBook;
import seedu.phu.model.Model;
import seedu.phu.model.ModelManager;
import seedu.phu.model.UserPrefs;

public class ClearCommandTest {
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_emptyInternshipBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.commitInternshipBookChange();

        assertCommandSuccess(new ClearCommand(), model, commandHistory, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyInternshipBook_success() {
        Model model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel.setInternshipBook(new InternshipBook());
        expectedModel.commitInternshipBookChange();

        assertCommandSuccess(new ClearCommand(), model, commandHistory, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
