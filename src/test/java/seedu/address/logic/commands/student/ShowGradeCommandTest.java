package seedu.address.logic.commands.student;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.student.ShowGradeCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class ShowGradeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_showGrade_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, true);
        assertCommandSuccess(new ShowGradeCommand(), model, expectedCommandResult, expectedModel);
    }
}
