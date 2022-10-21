package seedu.phu.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.phu.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.phu.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalInternships.AMAZON;
import static seedu.phu.testutil.TypicalInternships.BYTEDANCE;
import static seedu.phu.testutil.TypicalInternships.CITADEL;
import static seedu.phu.testutil.TypicalInternships.DSTA;
import static seedu.phu.testutil.TypicalInternships.EBAY;
import static seedu.phu.testutil.TypicalInternships.FASTLY;
import static seedu.phu.testutil.TypicalInternships.GOOGLE;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternshipBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.phu.model.InternshipBook;
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
    private CommandHistory commandHistory = new CommandHistory();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
    }
    @Test
    public void equals() {
        ComparableCategory nameCategory = ComparableCategory.NAME;
        ComparableCategory dateCategory = ComparableCategory.DATE;
        ListCommand listFirstCommand = new ListCommand(nameCategory, false);
        ListCommand listSecondCommand = new ListCommand(nameCategory, true);
        ListCommand listThirdCommand = new ListCommand(dateCategory, false);
        ListCommand listFirstCommandCopy = new ListCommand(nameCategory, false);

        // same object -> returns true
        assertTrue(listFirstCommand.equals(listFirstCommand));

        // different types -> returns false
        assertFalse(listFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listFirstCommand.equals(null));

        // same categories and keywords -> returns true
        assertTrue(listFirstCommand.equals(listFirstCommandCopy));

        // different sort order
        assertFalse(listFirstCommand.equals(listSecondCommand));

        // different category -> returns false
        assertFalse(listFirstCommand.equals(listThirdCommand));
    }
    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, commandHistory, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        assertCommandSuccess(new ListCommand(), model, commandHistory, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsNotSortedAscending_showsEverything() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        ListCommand newCommand = new ListCommand(ComparableCategory.NULL, false);
        assertCommandSuccess(newCommand, model, commandHistory, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listByCompanyNameAscending_success() {
        InternshipBook expectedInternship = new InternshipBook();
        expectedInternship.addInternship(AMAZON);
        expectedInternship.addInternship(BYTEDANCE);
        expectedInternship.addInternship(CITADEL);
        expectedInternship.addInternship(DSTA);
        expectedInternship.addInternship(EBAY);
        expectedInternship.addInternship(FASTLY);
        expectedInternship.addInternship(GOOGLE);
        expectedModel = new ModelManager(expectedInternship, new UserPrefs());

        assertCommandSuccess(new ListCommand(ComparableCategory.NAME, false), model, commandHistory,
                ListCommand.MESSAGE_SUCCESS + " sorted by " + ComparableCategory.NAME, expectedModel);
    }

    @Test
    public void execute_listByCompanyNameDescending_success() {
        InternshipBook expectedInternship = new InternshipBook();
        expectedInternship.addInternship(GOOGLE);
        expectedInternship.addInternship(FASTLY);
        expectedInternship.addInternship(EBAY);
        expectedInternship.addInternship(DSTA);
        expectedInternship.addInternship(CITADEL);
        expectedInternship.addInternship(BYTEDANCE);
        expectedInternship.addInternship(AMAZON);
        expectedModel = new ModelManager(expectedInternship, new UserPrefs());

        assertCommandSuccess(new ListCommand(ComparableCategory.NAME, true), model, commandHistory,
                ListCommand.MESSAGE_SUCCESS + " sorted by " + ComparableCategory.NAME, expectedModel);
    }

    @Test
    public void execute_listByPositionAscending_success() {
        InternshipBook expectedInternship = new InternshipBook();
        expectedInternship.addInternship(FASTLY); //AI engineer
        expectedInternship.addInternship(AMAZON); //Backend Intern
        expectedInternship.addInternship(GOOGLE); //Data analyst
        expectedInternship.addInternship(CITADEL); //backend engineer
        expectedInternship.addInternship(EBAY); //data engineer
        expectedInternship.addInternship(DSTA); //frontend engineer
        expectedInternship.addInternship(BYTEDANCE); //software engineer

        expectedModel = new ModelManager(expectedInternship, new UserPrefs());

        assertCommandSuccess(new ListCommand(ComparableCategory.POSITION, false), model, commandHistory,
                ListCommand.MESSAGE_SUCCESS + " sorted by " + ComparableCategory.POSITION, expectedModel);
    }

    @Test
    public void execute_listByDateAscending_success() {
        InternshipBook expectedInternship = new InternshipBook();
        expectedInternship.addInternship(DSTA); //14-09-2022
        expectedInternship.addInternship(BYTEDANCE); //24-09-2022
        expectedInternship.addInternship(CITADEL); //24-09-2022
        expectedInternship.addInternship(EBAY); //24-09-2022
        expectedInternship.addInternship(FASTLY); //24-09-2022
        expectedInternship.addInternship(GOOGLE); //24-09-2022
        expectedInternship.addInternship(AMAZON); //11-12-2022

        expectedModel = new ModelManager(expectedInternship, new UserPrefs());

        assertCommandSuccess(new ListCommand(ComparableCategory.DATE, false), model, commandHistory,
                ListCommand.MESSAGE_SUCCESS + " sorted by " + ComparableCategory.DATE, expectedModel);
    }
}
