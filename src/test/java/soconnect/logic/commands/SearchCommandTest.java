package soconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static soconnect.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static soconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static soconnect.testutil.Assert.assertThrows;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import soconnect.logic.parser.ArgumentMultimap;
import soconnect.logic.parser.ArgumentTokenizer;
import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.TodoList;
import soconnect.model.UserPrefs;
import soconnect.model.person.search.ContactContainsAllKeywordsPredicate;
import soconnect.model.person.search.ContactContainsAnyKeywordsPredicate;
import soconnect.model.person.search.ContactMightBeRelevantPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class SearchCommandTest {
    private final Model model = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());
    private final ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize("n/name p/phone e/email",
                    PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

    @Test
    public void constructor_nullSearch_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SearchCommand(null, null, null));
    }

    @Test
    public void equals() {
        ContactContainsAnyKeywordsPredicate firstPredicate = new ContactContainsAnyKeywordsPredicate(argMultimap);
        ContactContainsAllKeywordsPredicate secondPredicate = new ContactContainsAllKeywordsPredicate(argMultimap);
        ContactMightBeRelevantPredicate thirdPredicate =
                new ContactMightBeRelevantPredicate(argMultimap, false);
        ContactMightBeRelevantPredicate forthPredicate =
                new ContactMightBeRelevantPredicate(argMultimap, true);

        SearchCommand searchFirstCommand = new SearchCommand(firstPredicate, thirdPredicate, forthPredicate);
        SearchCommand searchSecondCommand = new SearchCommand(secondPredicate, thirdPredicate, forthPredicate);

        // same object -> returns true
        assertEquals(searchFirstCommand, searchFirstCommand);
        assertEquals(searchSecondCommand, searchSecondCommand);

        // same values -> returns true
        SearchCommand searchFirstCommandCopy = new SearchCommand(firstPredicate, thirdPredicate, forthPredicate);
        assertEquals(searchFirstCommand, searchFirstCommandCopy);

        SearchCommand searchSecondCommandCopy = new SearchCommand(secondPredicate, thirdPredicate, forthPredicate);
        assertEquals(searchSecondCommand, searchSecondCommandCopy);

        // different types -> returns false
        assertNotEquals(1, searchFirstCommand);
        assertNotEquals("third", searchSecondCommand);

        // null -> returns false
        assertNotEquals(null, searchFirstCommand);
        assertNotEquals(null, searchSecondCommand);

        // different person -> returns false
        assertNotEquals(searchFirstCommand, searchSecondCommand);
    }

    @Test
    public void execute_zeroPrefixIrrelevantKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArgumentMultimap zeroPrefixAndKeywords = ArgumentTokenizer.tokenize("!!!");
        ContactContainsAnyKeywordsPredicate predicate = new ContactContainsAnyKeywordsPredicate(zeroPrefixAndKeywords);
        ContactMightBeRelevantPredicate alternativePredicate =
                new ContactMightBeRelevantPredicate(zeroPrefixAndKeywords, false);
        ContactMightBeRelevantPredicate leastAccuratePredicate =
                new ContactMightBeRelevantPredicate(zeroPrefixAndKeywords, true);
        SearchCommand command = new SearchCommand(predicate, alternativePredicate, leastAccuratePredicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroPrefixRelevantKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArgumentMultimap zeroPrefixAndKeywords = ArgumentTokenizer.tokenize("abcdefg");
        ContactContainsAnyKeywordsPredicate predicate = new ContactContainsAnyKeywordsPredicate(zeroPrefixAndKeywords);
        ContactMightBeRelevantPredicate alternativePredicate =
                new ContactMightBeRelevantPredicate(zeroPrefixAndKeywords, false);
        ContactMightBeRelevantPredicate leastAccuratePredicate =
                new ContactMightBeRelevantPredicate(zeroPrefixAndKeywords, true);
        SearchCommand command = new SearchCommand(predicate, alternativePredicate, leastAccuratePredicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

}
