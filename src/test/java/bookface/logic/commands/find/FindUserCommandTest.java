package bookface.logic.commands.find;

import static bookface.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static bookface.logic.commands.CommandTestUtil.assertCommandSuccess;
import static bookface.testutil.TestUtil.preparePredicateToCheckPersonForPartialWordIgnoreCase;
import static bookface.testutil.TypicalPersons.CARL;
import static bookface.testutil.TypicalPersons.ELLE;
import static bookface.testutil.TypicalPersons.FIONA;
import static bookface.testutil.TypicalPersons.getTypicalBookFaceData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import bookface.model.Model;
import bookface.model.ModelManager;
import bookface.model.ObjectContainsKeywordsPredicate;
import bookface.model.UserPrefs;
import bookface.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code FindUserCommand}.
 */
public class FindUserCommandTest {
    private final Model model = new ModelManager(getTypicalBookFaceData(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalBookFaceData(), new UserPrefs());

    @Test
    public void equals() {
        ObjectContainsKeywordsPredicate<Person, String> firstPredicate =
                preparePredicateToCheckPersonForPartialWordIgnoreCase(Collections.singletonList("first"));
        ObjectContainsKeywordsPredicate<Person, String> secondPredicate =
                preparePredicateToCheckPersonForPartialWordIgnoreCase(Collections.singletonList("second"));

        FindUserCommand findFirstCommand = new FindUserCommand(firstPredicate);
        FindUserCommand findSecondCommand = new FindUserCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindUserCommand findFirstCommandCopy = new FindUserCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different person -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ObjectContainsKeywordsPredicate<Person, String> predicate =
                preparePredicateToCheckPersonForPartialWordIgnoreCase(" ");
        FindUserCommand command = new FindUserCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ObjectContainsKeywordsPredicate<Person, String> predicate =
                preparePredicateToCheckPersonForPartialWordIgnoreCase("Kurz Elle Kunz");
        FindUserCommand command = new FindUserCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }
}
