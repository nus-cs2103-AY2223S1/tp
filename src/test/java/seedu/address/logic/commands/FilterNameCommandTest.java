package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicatePatient;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FilterNameCommandTest {
    private Model model = new ModelManager(getTypicalPersonsAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPersonsAddressBook(), new UserPrefs());
    @Test
    public void equals() {
        NameContainsKeywordsPredicatePatient firstPredicate =
                new NameContainsKeywordsPredicatePatient(Collections.singletonList("first"));
        NameContainsKeywordsPredicatePatient secondPredicate =
                new NameContainsKeywordsPredicatePatient(Collections.singletonList("second"));

        FilterNameCommand findFirstCommand = new FilterNameCommand(firstPredicate);
        FilterNameCommand findSecondCommand = new FilterNameCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FilterNameCommand findFirstCommandCopy = new FilterNameCommand(firstPredicate);
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
        NameContainsKeywordsPredicatePatient predicate = preparePredicate(" ");
        FilterNameCommand command = new FilterNameCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPatientList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicatePatient predicate = preparePredicate("Kurz Elle Kunz");
        FilterNameCommand command = new FilterNameCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPatientList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicatePatient}.
     */
    private NameContainsKeywordsPredicatePatient preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicatePatient(Arrays.asList(userInput.split("\\s+")));
    }
}
