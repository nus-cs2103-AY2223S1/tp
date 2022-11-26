package seedu.workbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.commons.core.Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW;
import static seedu.workbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.workbook.testutil.TypicalInternships.ALICE;
import static seedu.workbook.testutil.TypicalInternships.BENSON;
import static seedu.workbook.testutil.TypicalInternships.CARL;
import static seedu.workbook.testutil.TypicalInternships.DANIEL;
import static seedu.workbook.testutil.TypicalInternships.ELLE;
import static seedu.workbook.testutil.TypicalInternships.FIONA;
import static seedu.workbook.testutil.TypicalInternships.GEORGE;
import static seedu.workbook.testutil.TypicalInternships.getTypicalWorkBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.workbook.model.Model;
import seedu.workbook.model.ModelManager;
import seedu.workbook.model.UserPrefs;
import seedu.workbook.model.internship.CompanyContainsKeywordsPredicate;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.internship.InternshipComparator;
import seedu.workbook.model.internship.RoleContainsKeywordsPredicate;
import seedu.workbook.model.internship.StageContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalWorkBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalWorkBook(), new UserPrefs());

    @Test
    public void equals() {
        CompanyContainsKeywordsPredicate firstPredicate =
                new CompanyContainsKeywordsPredicate(Collections.singletonList("first"));
        CompanyContainsKeywordsPredicate secondPredicate =
                new CompanyContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different internship -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }


    @Test
    public void execute_multipleKeywords_multipleInternshipsFoundForCompany() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 1);
        Predicate<Internship> predicate = preparePredicateForCompany("Carl Kurz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List<Internship> expectedInternshipList = Arrays.asList(CARL);
        Collections.sort(expectedInternshipList, new InternshipComparator());
        assertEquals(expectedInternshipList, model.getFilteredInternshipList());
    }

    @Test
    public void execute_multipleKeywords_multipleInternshipsFoundForRole() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 7);
        Predicate<Internship> predicate = preparePredicateForRole("Software Engineer");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List<Internship> expectedInternshipList = Arrays.asList(CARL, ELLE, FIONA, ALICE, BENSON, DANIEL, GEORGE);
        Collections.sort(expectedInternshipList, new InternshipComparator());
        assertEquals(expectedInternshipList, model.getFilteredInternshipList());
    }

    @Test
    public void execute_multipleKeywords_multipleInternshipsFoundForStage() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 2);
        Predicate<Internship> predicate = preparePredicateForStage("Technical Interview");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List<Internship> expectedInternshipList = Arrays.asList(ALICE, DANIEL);
        Collections.sort(expectedInternshipList, new InternshipComparator());
        assertEquals(expectedInternshipList, model.getFilteredInternshipList());
    }

    /**
     * Parses {@code userInput} into a {@code CompanyContainsKeywordsPredicate}.
     */
    private Predicate<Internship> preparePredicateForCompany(String userInput) {
        return new CompanyContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code RoleContainsKeywordsPredicate}.
     */
    private Predicate<Internship> preparePredicateForRole(String userInput) {
        return new RoleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code StageContainsKeywordsPredicate}.
     */
    private Predicate<Internship> preparePredicateForStage(String userInput) {
        return new StageContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
