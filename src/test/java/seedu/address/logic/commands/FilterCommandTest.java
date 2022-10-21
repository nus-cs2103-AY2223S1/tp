package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalApplicants.BENSON;
import static seedu.address.testutil.TypicalApplicants.CARL;
import static seedu.address.testutil.TypicalApplicants.DANIEL;
import static seedu.address.testutil.TypicalApplicants.ELLE;
import static seedu.address.testutil.TypicalApplicants.FIONA;
import static seedu.address.testutil.TypicalApplicants.GEORGE;
import static seedu.address.testutil.TypicalApplicants.getTypicalTrackAScholar;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.ApplicationStatusPredicate;



public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());

    @Test
    public void equals() {

        ApplicationStatusPredicate firstPredicate =
                new ApplicationStatusPredicate("pending");
        ApplicationStatusPredicate secondPredicate =
                new ApplicationStatusPredicate("accepted");

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different applicant -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_pending_multipleApplicantsFiltered() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 4);
        ApplicationStatusPredicate predicate = preparePredicate("pending");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, ELLE, FIONA), model.getFilteredApplicantList());
    }

    @Test
    public void execute_accepted_twoApplicantsFiltered() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 2);
        ApplicationStatusPredicate predicate = preparePredicate("accepted");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, GEORGE), model.getFilteredApplicantList());
    }

    @Test
    public void execute_rejected_oneApplicantFiltered() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 1);
        ApplicationStatusPredicate predicate = preparePredicate("rejected");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL), model.getFilteredApplicantList());
    }

    /**
     * Parses {@code userInput} into a {@code StatusContainsKeywordPredicate}.
     */
    private ApplicationStatusPredicate preparePredicate(String userInput) {
        return new ApplicationStatusPredicate(userInput);
    }
}
