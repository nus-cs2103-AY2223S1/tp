package seedu.classify.logic.commands;

import static seedu.classify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.classify.testutil.TypicalStudents.getTypicalStudentRecord;

import org.junit.jupiter.api.Test;

import seedu.classify.model.Model;
import seedu.classify.model.ModelManager;
import seedu.classify.model.StudentRecord;
import seedu.classify.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyStudentRecord_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyStudentRecord_success() {
        Model model = new ModelManager(getTypicalStudentRecord(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalStudentRecord(), new UserPrefs());
        expectedModel.setStudentRecord(new StudentRecord());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
