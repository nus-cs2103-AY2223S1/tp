package seedu.rc4hdb.logic.commands.residentcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_RESIDENTS_LISTED_OVERVIEW;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.testutil.TypicalResidents.BENSON;
import static seedu.rc4hdb.testutil.TypicalResidents.CARL;
import static seedu.rc4hdb.testutil.TypicalResidents.ELLE;
import static seedu.rc4hdb.testutil.TypicalResidents.FIONA;
import static seedu.rc4hdb.testutil.TypicalResidents.GEORGE;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.model.resident.predicates.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalResidentBook(), new VenueBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalResidentBook(), new VenueBook(), new UserPrefs());

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

        // different resident -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noResidentFound() {
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredResidentList());
    }

    @Test
    public void execute_partialKeyword_oneResidentFound() {
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate = preparePredicate("eorge");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredResidentList());
    }

    @Test
    public void execute_partialKeyword_multipleResidentsFound() {
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate("Ku");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, FIONA), model.getFilteredResidentList());
    }

    @Test
    public void execute_multiplePartialKeyword_multipleResidentsFound() {
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Ku On");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, FIONA), model.getFilteredResidentList());
    }

    @Test
    public void execute_onePartialOneInvalidKeyword_multipleResidentsFound() {
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate("Ku Bestie");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, FIONA), model.getFilteredResidentList());
    }

    @Test
    public void execute_multipleKeywords_multipleResidentsFound() {
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredResidentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
