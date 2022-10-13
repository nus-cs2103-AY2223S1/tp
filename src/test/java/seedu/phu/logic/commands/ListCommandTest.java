package seedu.phu.logic.commands;

import static seedu.phu.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.phu.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.phu.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternshipBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.phu.model.Model;
import seedu.phu.model.ModelManager;
import seedu.phu.model.UserPrefs;
import seedu.phu.model.internship.ComparableCategory;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFilteredNotReverse_showsEverything() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        ListCommand newCommand = new ListCommand(ComparableCategory.NULL, false);
        assertCommandSuccess(newCommand, model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFilteredReverse_showsEverything() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        ListCommand newCommand = new ListCommand(ComparableCategory.NULL, true);
        expectedModel.reverseList();
        assertCommandSuccess(newCommand, model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
