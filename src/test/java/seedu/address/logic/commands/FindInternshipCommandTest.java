package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
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
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindInternshipCommand}.
 */
public class FindInternshipCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindInternshipCommand findFirstCommand = new FindInternshipCommand(firstPredicate);
        FindInternshipCommand findSecondCommand = new FindInternshipCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindInternshipCommand findFirstCommandCopy = new FindInternshipCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noInternshipFound() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindInternshipCommand command = new FindInternshipCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredInternshipList());
    }

    @Test
    public void execute_multipleKeywords_multipleInternshipFound() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 9);
        NameContainsKeywordsPredicate predicate = preparePredicate("Fiona Pte Ltd");
        FindInternshipCommand command = new FindInternshipCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, HOON, IDA),
                model.getFilteredInternshipList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
