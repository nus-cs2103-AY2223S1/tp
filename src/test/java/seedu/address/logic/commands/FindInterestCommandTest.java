package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonContainsInterestPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTagCommand}.
 */
public class FindInterestCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonContainsInterestPredicate firstPredicate =
                new PersonContainsInterestPredicate(Collections.singletonList("first"));
        PersonContainsInterestPredicate secondPredicate =
                new PersonContainsInterestPredicate(Collections.singletonList("second"));

        FindInterestCommand findInterestFirstCommand = new FindInterestCommand(firstPredicate);
        FindInterestCommand findInterestSecondCommand = new FindInterestCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findInterestFirstCommand.equals(findInterestFirstCommand));

        // same values -> returns true
        FindInterestCommand findFirstCommandCopy = new FindInterestCommand(firstPredicate);
        assertTrue(findInterestFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findInterestFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findInterestFirstCommand.equals(null));

        // different tag -> returns false
        assertFalse(findInterestFirstCommand.equals(findInterestSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonContainsInterestPredicate predicate = preparePredicate(" ");
        FindInterestCommand command = new FindInterestCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsInterestPredicate predicate = preparePredicate("tennis");
        FindInterestCommand command = new FindInterestCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonContainsInterestPredicate predicate = preparePredicate("tennis netflix");
        FindInterestCommand command = new FindInterestCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleCasingKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsInterestPredicate predicate = preparePredicate("tennis TENNIS TenNiS");
        FindInterestCommand command = new FindInterestCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code PersonContainsInterestPredicate}.
     */
    private PersonContainsInterestPredicate preparePredicate(String userInput) {
        return new PersonContainsInterestPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
