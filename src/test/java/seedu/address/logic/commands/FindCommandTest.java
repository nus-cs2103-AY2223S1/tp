package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.FANG;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.LEE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonMatchesKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonMatchesKeywordsPredicate firstPredicate =
                new PersonMatchesKeywordsPredicate();
        PersonMatchesKeywordsPredicate secondPredicate =
                new PersonMatchesKeywordsPredicate();

        firstPredicate.setKeywords("first");
        secondPredicate.setKeywords("second");

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

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonMatchesKeywordsPredicate predicate = preparePredicate("  ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.filterPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedFilteredPersonList());
    }

    @Test
    public void execute_addressKeyword_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonMatchesKeywordsPredicate predicate = preparePredicate("little");
        FindCommand command = new FindCommand(predicate);
        expectedModel.filterPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, HOON, LEE), model.getSortedFilteredPersonList());
    }

    @Test
    public void execute_nameKeyword_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonMatchesKeywordsPredicate predicate = preparePredicate("lee");
        FindCommand command = new FindCommand(predicate);
        expectedModel.filterPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LEE, FANG), model.getSortedFilteredPersonList());
    }

    @Test
    public void execute_tagKeyword_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 6);
        PersonMatchesKeywordsPredicate predicate = preparePredicate("friend");
        FindCommand command = new FindCommand(predicate);
        expectedModel.filterPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, HOON, LEE, FANG), model.getSortedFilteredPersonList());
    }

    @Test
    public void execute_roleKeyword_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonMatchesKeywordsPredicate predicate = preparePredicate("engineer");
        FindCommand command = new FindCommand(predicate);
        expectedModel.filterPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, HOON, FANG), model.getSortedFilteredPersonList());
    }

    @Test
    public void execute_gitHubUserKeyword_personFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonMatchesKeywordsPredicate predicate = preparePredicate("sh4nH");
        FindCommand command = new FindCommand(predicate);
        expectedModel.filterPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HOON), model.getSortedFilteredPersonList());
    }

    @Test
    public void execute_nameSpelledWrong_personFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonMatchesKeywordsPredicate predicate = preparePredicate("lei chang");
        FindCommand command = new FindCommand(predicate);
        expectedModel.filterPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LEE, FANG), model.getSortedFilteredPersonList());
    }

    @Test
    public void execute_commonNameAndGitHubUser_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonMatchesKeywordsPredicate predicate = preparePredicate("george");
        FindCommand command = new FindCommand(predicate);
        expectedModel.filterPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE, FANG), model.getSortedFilteredPersonList());
    }


    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PersonMatchesKeywordsPredicate preparePredicate(String userInput) {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        predicate.setKeywords(userInput);
        return predicate;
    }
}
