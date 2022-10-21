package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalApplicants.BENSON;
import static seedu.address.testutil.TypicalApplicants.CARL;
import static seedu.address.testutil.TypicalApplicants.DANIEL;
import static seedu.address.testutil.TypicalApplicants.ELLE;
import static seedu.address.testutil.TypicalApplicants.FIONA;
import static seedu.address.testutil.TypicalApplicants.GEORGE;
import static seedu.address.testutil.TypicalApplicants.getTypicalTrackAScholar;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;

public class SortCommandTest {

    private Comparator<Applicant> compareName = Applicant.sortByName();
    private Comparator<Applicant> compareScholarship = Applicant.sortByScholarship();
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
