package seedu.workbook.logic.commands;

import static seedu.workbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.workbook.testutil.TypicalInternships.getTypicalWorkBook;

import org.junit.jupiter.api.Test;

import seedu.workbook.model.Model;
import seedu.workbook.model.ModelManager;
import seedu.workbook.model.UserPrefs;
import seedu.workbook.model.WorkBook;

public class ClearCommandTest {

    @Test
    public void execute_emptyWorkBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.commitWorkBook();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyWorkBook_success() {
        Model model = new ModelManager(getTypicalWorkBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalWorkBook(), new UserPrefs());
        expectedModel.setWorkBook(new WorkBook());
        expectedModel.commitWorkBook();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
