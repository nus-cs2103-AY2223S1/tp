package seedu.taassist.logic.commands;

import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.taassist.testutil.TypicalStudents.getTypicalTaAssist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.taassist.model.Model;
import seedu.taassist.model.ModelManager;
import seedu.taassist.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaAssist(), new UserPrefs());
        expectedModel = new ModelManager(model.getTaAssist(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_ALL_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_ALL_SUCCESS, expectedModel);
    }
}
