package seedu.phu.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.commons.core.Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW;
import static seedu.phu.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.phu.testutil.TypicalInternships.AMAZON;
import static seedu.phu.testutil.TypicalInternships.BYTEDANCE;
import static seedu.phu.testutil.TypicalInternships.CITADEL;
import static seedu.phu.testutil.TypicalInternships.DSTA;
import static seedu.phu.testutil.TypicalInternships.EBAY;
import static seedu.phu.testutil.TypicalInternships.FASTLY;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternshipBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.phu.commons.core.keyword.Keyword;
import seedu.phu.commons.core.keyword.KeywordList;
import seedu.phu.model.Model;
import seedu.phu.model.ModelManager;
import seedu.phu.model.UserPrefs;
import seedu.phu.model.internship.ContainsKeywordsPredicate;
import seedu.phu.model.internship.FindableCategory;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        KeywordList firstKeywords = prepareKeywords("optiver", "jane");
        KeywordList secondKeywords = prepareKeywords("jane", "optiver");
        KeywordList thirdKeywords = prepareKeywords("hrt", "jane");
        KeywordList fourthKeywords = prepareKeywords("optiver", "jane", "jane");

        ContainsKeywordsPredicate firstPredicate =
                new ContainsKeywordsPredicate(firstKeywords, FindableCategory.COMPANY_NAME);
        ContainsKeywordsPredicate secondPredicate =
                new ContainsKeywordsPredicate(firstKeywords, FindableCategory.DATE);
        ContainsKeywordsPredicate thirdPredicate =
                new ContainsKeywordsPredicate(secondKeywords, FindableCategory.COMPANY_NAME);
        ContainsKeywordsPredicate fourthPredicate =
                new ContainsKeywordsPredicate(thirdKeywords, FindableCategory.COMPANY_NAME);
        ContainsKeywordsPredicate fifthPredicate =
                new ContainsKeywordsPredicate(fourthKeywords, FindableCategory.COMPANY_NAME);

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);
        FindCommand findThirdCommand = new FindCommand(thirdPredicate);
        FindCommand findFourthCommand = new FindCommand(fourthPredicate);
        FindCommand findFifthCommand = new FindCommand(fifthPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same categories and keywords -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different category -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // same category and keywords but with different order -> return true
        assertTrue(findFirstCommand.equals(findThirdCommand));

        //different keywords -> return false
        assertFalse(findFirstCommand.equals(findFourthCommand));

        //same keywords with duplicate -> return true
        assertTrue(findFirstCommand.equals(findFifthCommand));
    }

    @Test
    public void execute_companyNameCategory_multipleMatches() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 3);
        ContainsKeywordsPredicate predicate =
                preparePredicate(FindableCategory.COMPANY_NAME, "Citadel", "Ebay", "Fastly");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CITADEL, EBAY, FASTLY), model.getFilteredInternshipList());
    }

    @Test
    public void execute_applicationProcessCategory_multipleMatches() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 2);
        ContainsKeywordsPredicate predicate =
                preparePredicate(FindableCategory.APPLICATION_PROCESS, "interview", "assessment");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BYTEDANCE, DSTA), model.getFilteredInternshipList());
    }

    @Test
    public void execute_positionCategory_multipleMatches() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 5);
        ContainsKeywordsPredicate predicate =
                preparePredicate(FindableCategory.POSITION, "engin");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BYTEDANCE, CITADEL, DSTA, EBAY, FASTLY), model.getFilteredInternshipList());
    }

    @Test
    public void execute_dateCategory_singleMatch() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 1);
        ContainsKeywordsPredicate predicate =
                preparePredicate(FindableCategory.DATE, "11-12-2022", "12-10-2022");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AMAZON), model.getFilteredInternshipList());
    }

    @Test
    public void execute_tagsCategory_singleMatch() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 1);
        ContainsKeywordsPredicate predicate =
                preparePredicate(FindableCategory.TAGS, "owes", "random", "sugiyem");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BYTEDANCE), model.getFilteredInternshipList());
    }

    /**
     * Parses {@code keywords} into a {@code ContainsKeywordsPredicate}.
     */
    private ContainsKeywordsPredicate preparePredicate(FindableCategory category, String ...keywords) {
        KeywordList keywordList = prepareKeywords(keywords);
        return new ContainsKeywordsPredicate(keywordList, category);
    }

    private KeywordList prepareKeywords(String ...keywords) {
        KeywordList keywordList = new KeywordList();

        for (String k : keywords) {
            keywordList.addKeyword(new Keyword(k));
        }

        return keywordList;
    }
}
