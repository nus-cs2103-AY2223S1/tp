package seedu.trackascholar.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.trackascholar.commons.core.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
import static seedu.trackascholar.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackascholar.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static seedu.trackascholar.testutil.TypicalApplicants.ALICE;
import static seedu.trackascholar.testutil.TypicalApplicants.BENSON;
import static seedu.trackascholar.testutil.TypicalApplicants.CARL;
import static seedu.trackascholar.testutil.TypicalApplicants.DANIEL;
import static seedu.trackascholar.testutil.TypicalApplicants.ELLE;
import static seedu.trackascholar.testutil.TypicalApplicants.FIONA;
import static seedu.trackascholar.testutil.TypicalApplicants.GEORGE;
import static seedu.trackascholar.testutil.TypicalApplicants.getTypicalTrackAScholar;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.ModelManager;
import seedu.trackascholar.model.UserPrefs;
import seedu.trackascholar.model.applicant.Applicant;

public class SortCommandTest {

    private Comparator<Applicant> compareName = Applicant.sortByName();
    private Comparator<Applicant> compareScholarship = Applicant.sortByScholarship();
    private Comparator<Applicant> compareStatus = Applicant.sortByStatus();

    private Model model = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());

    @Test
    public void execute_name_sortedByName() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 7);
        SortCommand command = new SortCommand(compareName);
        expectedModel.sortApplicants(compareName);
        expectedModel.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredApplicantList());
    }

    @Test
    public void execute_scholarship_sortedByScholarship() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 7);
        SortCommand command = new SortCommand(compareScholarship);
        expectedModel.sortApplicants(compareScholarship);
        expectedModel.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ALICE, ELLE, FIONA, GEORGE, BENSON, CARL), model.getFilteredApplicantList());
    }

    @Test
    public void execute_status_sortedByStatus() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 7);
        SortCommand command = new SortCommand(compareStatus);
        expectedModel.sortApplicants(compareStatus);
        expectedModel.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, ELLE, FIONA, BENSON, GEORGE, DANIEL), model.getFilteredApplicantList());
    }

    @Test
    public void equals() {

        SortCommand sortName = new SortCommand(compareName);
        SortCommand sortScholarship = new SortCommand(compareScholarship);

        // same object -> returns true
        assertEquals(sortName, sortName);

        // same values -> returns true
        assertEquals(sortName, new SortCommand(compareName));

        // null -> returns false
        assertNotEquals(null, sortName);

        // different sort commands -> returns false
        assertNotEquals(sortName, sortScholarship);
    }

}
