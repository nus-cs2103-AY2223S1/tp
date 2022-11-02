package seedu.masslinkers.logic.commands;

import static seedu.masslinkers.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.masslinkers.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.masslinkers.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.masslinkers.testutil.TypicalStudents.getTypicalMassLinkers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.ModelManager;
import seedu.masslinkers.model.UserPrefs;

//@@author
/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMassLinkers(), new UserPrefs());
        expectedModel = new ModelManager(model.getMassLinkers(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel,
                false, false, true, false);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel,
                false, false, true, false);
    }
}
