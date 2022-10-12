package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.keyword.Keyword;
import seedu.address.commons.core.keyword.KeywordList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContainsKeywordsPredicate;
import seedu.address.model.person.FindableCategory;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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

//    @Test
//    public void execute_zeroKeywords_noPersonFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
//        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
//        FindCommand command = new FindCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
//    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ContainsKeywordsPredicate predicate =
                preparePredicate(FindableCategory.COMPANY_NAME ,"Kurz", "Elle",  "Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    private KeywordList prepareKeywords(String ...keywords) {
        KeywordList keywordList = new KeywordList();

        for(String k : keywords) {
            keywordList.addKeyword(new Keyword(k));
        }

        return keywordList;
    }

    /**
     * Parses {@code keywords} into a {@code ContainsKeywordsPredicate}.
     */
    private ContainsKeywordsPredicate preparePredicate(FindableCategory category, String ...keywords) {
        KeywordList keywordList = prepareKeywords(keywords);
        return new ContainsKeywordsPredicate(keywordList, category);
    }
}
