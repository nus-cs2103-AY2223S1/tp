package longtimenosee.logic.commands;

import static longtimenosee.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static longtimenosee.logic.commands.CommandTestUtil.assertCommandSuccess;
import static longtimenosee.testutil.TypicalPersons.ALICE;
import static longtimenosee.testutil.TypicalPersons.CARL;
import static longtimenosee.testutil.TypicalPersons.ELLE;
import static longtimenosee.testutil.TypicalPersons.FIONA;
import static longtimenosee.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import longtimenosee.model.Model;
import longtimenosee.model.ModelManager;
import longtimenosee.model.UserPrefs;
import longtimenosee.model.person.Person;
import longtimenosee.model.person.predicate.NameContainsKeywordsPredicate;
import longtimenosee.model.person.predicate.RiskAppetiteMatchesInputPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(List.of(firstPredicate));
        FindCommand findSecondCommand = new FindCommand(List.of(secondPredicate));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(List.of(firstPredicate));
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
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        FindCommand command = new FindCommand(List.of(predicate));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(List.of(predicate));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multiplePredicates_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate firstPredicate = prepareNamePredicate("Alice");
        RiskAppetiteMatchesInputPredicate secondPredicate = prepareRiskAppetitePredicate("M");
        FindCommand command = new FindCommand(List.of(firstPredicate, secondPredicate));
        Predicate<Person> predicate = firstPredicate.and(secondPredicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(ALICE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private RiskAppetiteMatchesInputPredicate prepareRiskAppetitePredicate(String userInput) {
        return new RiskAppetiteMatchesInputPredicate(userInput);
    }
}
