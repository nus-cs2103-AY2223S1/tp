package seedu.trackascholar.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.commons.core.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
import static seedu.trackascholar.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackascholar.testutil.TypicalApplicants.ALICE;
import static seedu.trackascholar.testutil.TypicalApplicants.BENSON;
import static seedu.trackascholar.testutil.TypicalApplicants.CARL;
import static seedu.trackascholar.testutil.TypicalApplicants.ELLE;
import static seedu.trackascholar.testutil.TypicalApplicants.FIONA;
import static seedu.trackascholar.testutil.TypicalApplicants.GEORGE;
import static seedu.trackascholar.testutil.TypicalApplicants.getTypicalTrackAScholar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.parser.FindCommandParser;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.ModelManager;
import seedu.trackascholar.model.UserPrefs;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.NameContainsKeywordsPredicate;
import seedu.trackascholar.model.applicant.ScholarshipContainsKeywordsPredicate;
import seedu.trackascholar.model.major.MajorContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

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

        // different applicant -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        ScholarshipContainsKeywordsPredicate scholarshipPredicate = prepareScholarshipPredicate(" ");
        MajorContainsKeywordsPredicate majorsPredicate = prepareMajorPredicate(" ");

        List<Predicate<Applicant>> predicateList = new ArrayList<>();
        Collections.addAll(predicateList, namePredicate, scholarshipPredicate, majorsPredicate);
        Predicate<Applicant> predicate = FindCommandParser.combinePredicateList(predicateList);

        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleNameKeywords_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleScholarshipKeywords_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 5);
        ScholarshipContainsKeywordsPredicate predicate = prepareScholarshipPredicate("Global Merit");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, ELLE, FIONA, GEORGE), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleMajorKeywords_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 2);
        MajorContainsKeywordsPredicate predicate = prepareMajorPredicate("Computer Science Business");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleAttributeKeywords_ApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Benson Alice");
        ScholarshipContainsKeywordsPredicate scholarshipPredicate = prepareScholarshipPredicate("Merit");
        MajorContainsKeywordsPredicate majorsPredicate = prepareMajorPredicate("Mathematics");

        List<Predicate<Applicant>> predicateList = new ArrayList<>();
        Collections.addAll(predicateList, namePredicate, scholarshipPredicate, majorsPredicate);
        Predicate<Applicant> predicate = FindCommandParser.combinePredicateList(predicateList);

        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredApplicantList());
    }



    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code ScholarshipContainsKeywordsPredicate}.
     */
    private ScholarshipContainsKeywordsPredicate prepareScholarshipPredicate(String userInput) {
        return new ScholarshipContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code MajorContainsKeywordsPredicate}.
     */
    private MajorContainsKeywordsPredicate prepareMajorPredicate(String userInput) {
        return new MajorContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
