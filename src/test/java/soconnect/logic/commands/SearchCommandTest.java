package soconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static soconnect.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static soconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static soconnect.logic.parser.CliSyntax.INDICATOR_NAME;
import static soconnect.testutil.Assert.assertThrows;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.UserPrefs;
import soconnect.model.person.search.ContactContainsAnyKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class SearchCommandTest {
    private final Model model = new ModelManager(getTypicalSoConnect(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalSoConnect(), new UserPrefs());

//    @Test
//    public void constructor_nullSearch_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> new SearchCommand(null));
//    }
//
//    @Test
//    public void equals() {
//        ContactContainsAnyKeywordsPredicate firstPredicate = preparePredicate(INDICATOR_NAME, "first");
//        ContactContainsAnyKeywordsPredicate secondPredicate = preparePredicate(INDICATOR_NAME, "second");
//
//        SearchCommand searchFirstCommand = new SearchCommand(firstPredicate);
//        SearchCommand searchSecondCommand = new SearchCommand(secondPredicate);
//
//        // same object -> returns true
//        assertEquals(searchFirstCommand, searchFirstCommand);
//        assertEquals(searchSecondCommand, searchSecondCommand);
//
//        // same values -> returns true
//        SearchCommand searchFirstCommandCopy = new SearchCommand(firstPredicate);
//        assertEquals(searchFirstCommand, searchFirstCommandCopy);
//
//        SearchCommand searchSecondCommandCopy = new SearchCommand(secondPredicate);
//        assertEquals(searchSecondCommand, searchSecondCommandCopy);
//
//        // different types -> returns false
//        assertNotEquals(1, searchFirstCommand);
//        assertNotEquals("third", searchSecondCommand);
//
//        // null -> returns false
//        assertNotEquals(null, searchFirstCommand);
//        assertNotEquals(null, searchSecondCommand);
//
//        // different person -> returns false
//        assertNotEquals(searchFirstCommand, searchSecondCommand);
//    }
//
//    @Test
//    public void execute_zeroPrefixAndZeroKeywords_noPersonFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
//        ContactContainsAnyKeywordsPredicate predicate = preparePredicate(" ", " ");
//        SearchCommand command = new SearchCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
//    }
//
//    @Test
//    public void execute_zeroPrefixAndMultipleKeywords_noPersonFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
//        ContactContainsAnyKeywordsPredicate predicate = preparePredicate(" ",
//                "123, Jurong West Ave 6, #08-111");
//        SearchCommand command = new SearchCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
//    }
//
//    /**
//     * Parses {@code userInput} into a {@code ContactContainsAnyKeywordsPredicate}.
//     */
//    private ContactContainsAnyKeywordsPredicate preparePredicate(String prefix, String searchInput) {
//        return new ContactContainsAnyKeywordsPredicate(Collections.singletonList(prefix),
//                List.of(Collections.singletonList(searchInput)));
//    }
}
