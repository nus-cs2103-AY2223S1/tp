package seedu.classify.logic.commands;

import static seedu.classify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.classify.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.classify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.classify.testutil.TypicalStudents.getTypicalStudentRecord;

import org.junit.jupiter.api.Test;

import seedu.classify.model.Model;
import seedu.classify.model.ModelManager;
import seedu.classify.model.UserPrefs;

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
