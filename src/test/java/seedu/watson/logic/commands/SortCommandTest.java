package seedu.watson.logic.commands;

import static seedu.watson.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.watson.testutil.TypicalStudents.getTypicalDatabase;

import org.junit.jupiter.api.Test;

import seedu.watson.model.Database;
import seedu.watson.model.Model;
import seedu.watson.model.ModelManager;
import seedu.watson.model.UserPrefs;


public class SortCommandTest {
    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        SortCommand sortCommand = new SortCommand(true, "ENGLISH");

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.sortListByGrade(true, "ENGLISH");

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
}
