package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.PersonCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.ALICE;
import static seedu.address.testutil.TypicalInternships.BENSON;
import static seedu.address.testutil.TypicalInternships.CARL;
import static seedu.address.testutil.TypicalInternships.DANIEL;
import static seedu.address.testutil.TypicalInternships.ELLE;
import static seedu.address.testutil.TypicalInternships.FIONA;
import static seedu.address.testutil.TypicalInternships.GEORGE;
import static seedu.address.testutil.TypicalInternships.HOON;
import static seedu.address.testutil.TypicalInternships.IDA;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.Internship;


/**
 * Contains integration tests (interaction with the Model) for {@code SortInternshipCommand}.
 */
public class SortInternshipCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_sortByCompanyName_success() {
        String expectedMessage = String.format(SortInternshipCommand.MESSAGE_SUCCESS,
                SortInternshipCommand.Criteria.COMPANY_NAME.getCriteriaName());
        SortInternshipCommand command = new SortInternshipCommand(SortInternshipCommand.Criteria.COMPANY_NAME);
        expectedModel.sortInternshipList(Internship.compareByCompanyName());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, HOON, IDA),
                expectedModel.getFilteredInternshipList());
    }

    @Test
    public void execute_sortByInterviewDate_success() {
        String expectedMessage = String.format(SortInternshipCommand.MESSAGE_SUCCESS,
                SortInternshipCommand.Criteria.INTERVIEW_DATE.getCriteriaName());
        SortInternshipCommand command = new SortInternshipCommand(SortInternshipCommand.Criteria.INTERVIEW_DATE);
        expectedModel.sortInternshipList(Internship.compareByInterviewDate());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, GEORGE, ALICE, BENSON, CARL, HOON, FIONA, DANIEL, IDA),
                expectedModel.getFilteredInternshipList());
    }

    @Test
    public void execute_sortByInternshipStatus_success() {
        String expectedMessage = String.format(SortInternshipCommand.MESSAGE_SUCCESS,
                SortInternshipCommand.Criteria.INTERNSHIP_STATUS.getCriteriaName());
        SortInternshipCommand command = new SortInternshipCommand(SortInternshipCommand.Criteria.INTERNSHIP_STATUS);
        expectedModel.sortInternshipList(Internship.compareByInternshipStatus());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, ALICE, CARL, BENSON, DANIEL, IDA, ELLE, GEORGE, HOON),
                expectedModel.getFilteredInternshipList());
    }
}
