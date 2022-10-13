package seedu.phu.logic.commands;

import static seedu.phu.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.phu.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.phu.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalInternships.ALICE;
import static seedu.phu.testutil.TypicalInternships.BENSON;
import static seedu.phu.testutil.TypicalInternships.CARL;
import static seedu.phu.testutil.TypicalInternships.DANIEL;
import static seedu.phu.testutil.TypicalInternships.ELLE;
import static seedu.phu.testutil.TypicalInternships.FIONA;
import static seedu.phu.testutil.TypicalInternships.GEORGE;
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
    public void execute_listIsNotSortedAscending_showsEverything() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        ListCommand newCommand = new ListCommand(ComparableCategory.NULL, false);
        assertCommandSuccess(newCommand, model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsNotSortedDescending_showsEverything() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        ListCommand newCommand = new ListCommand(ComparableCategory.NULL, true);
        expectedModel.reverseList();
        assertCommandSuccess(newCommand, model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listByCompanyNameAscending_success() {
        InternshipBook expectedInternship = new InternshipBook();
        expectedInternship.addInternship(ALICE);
        expectedInternship.addInternship(BENSON);
        expectedInternship.addInternship(CARL);
        expectedInternship.addInternship(DANIEL);
        expectedInternship.addInternship(ELLE);
        expectedInternship.addInternship(FIONA);
        expectedInternship.addInternship(GEORGE);
        expectedModel = new ModelManager(expectedInternship, new UserPrefs());

        assertCommandSuccess(new ListCommand(ComparableCategory.NAME, false), model,
                ListCommand.MESSAGE_SUCCESS + " sorted by " + ComparableCategory.NAME, expectedModel);
    }

    @Test
    public void execute_listByCompanyNameDescending_success() {
        InternshipBook expectedInternship = new InternshipBook();
        expectedInternship.addInternship(GEORGE);
        expectedInternship.addInternship(FIONA);
        expectedInternship.addInternship(ELLE);
        expectedInternship.addInternship(DANIEL);
        expectedInternship.addInternship(CARL);
        expectedInternship.addInternship(BENSON);
        expectedInternship.addInternship(ALICE);
        expectedModel = new ModelManager(expectedInternship, new UserPrefs());

        assertCommandSuccess(new ListCommand(ComparableCategory.NAME, true), model,
                ListCommand.MESSAGE_SUCCESS + " sorted by " + ComparableCategory.NAME, expectedModel);
    }

    @Test
    public void execute_listByPositionAscending_success() {
        InternshipBook expectedInternship = new InternshipBook();
        expectedInternship.addInternship(FIONA); //AI engineer
        expectedInternship.addInternship(ALICE); //Backend Intern
        expectedInternship.addInternship(GEORGE); //Data analyst
        expectedInternship.addInternship(CARL); //backend engineer
        expectedInternship.addInternship(ELLE); //data engineer
        expectedInternship.addInternship(DANIEL); //frontend engineer
        expectedInternship.addInternship(BENSON); //software engineer

        expectedModel = new ModelManager(expectedInternship, new UserPrefs());

        assertCommandSuccess(new ListCommand(ComparableCategory.POSITION, false), model,
                ListCommand.MESSAGE_SUCCESS + " sorted by " + ComparableCategory.POSITION, expectedModel);
    }

    @Test
    public void execute_listByDateAscending_success() {
        InternshipBook expectedInternship = new InternshipBook();
        expectedInternship.addInternship(DANIEL); //14-09-2022
        expectedInternship.addInternship(BENSON); //24-09-2022
        expectedInternship.addInternship(CARL); //24-09-2022
        expectedInternship.addInternship(ELLE); //24-09-2022
        expectedInternship.addInternship(FIONA); //24-09-2022
        expectedInternship.addInternship(GEORGE); //24-09-2022
        expectedInternship.addInternship(ALICE); //11-12-2022

        expectedModel = new ModelManager(expectedInternship, new UserPrefs());

        assertCommandSuccess(new ListCommand(ComparableCategory.DATE, false), model,
                ListCommand.MESSAGE_SUCCESS + " sorted by " + ComparableCategory.DATE, expectedModel);
    }
}
