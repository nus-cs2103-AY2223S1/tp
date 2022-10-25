package jeryl.fyp.logic.commands;

import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jeryl.fyp.logic.commands.CommandTestUtil.showStudentAtIndex;
import static jeryl.fyp.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static jeryl.fyp.testutil.TypicalStudents.getTypicalFypManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeryl.fyp.model.Model;
import jeryl.fyp.model.ModelManager;
import jeryl.fyp.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortProjectStatusCommand.
 */
public class SortProjectStatusCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFypManager(), new UserPrefs());
        expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        model.sortFilteredStudentListByProjectStatus();
        expectedModel.sortFilteredStudentListByProjectStatus();
    }

    @Test
    public void execute_sortedListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new SortProjectStatusCommand(), model,
                SortProjectStatusCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortedListIsFiltered_showsEverything() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        assertCommandSuccess(new SortProjectStatusCommand(), model,
                SortProjectStatusCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
