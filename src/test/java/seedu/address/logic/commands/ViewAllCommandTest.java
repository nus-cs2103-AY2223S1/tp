package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentRecord;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewAll Command.
 */
public class ViewAllCommandTest {

    private Model model = new ModelManager(getTypicalStudentRecord(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getStudentRecord(), new UserPrefs());

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewAllCommand(), model, ViewAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ViewAllCommand(), model, ViewAllCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
