package seedu.address.logic.commands.getcommands;

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
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NextOfKinPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code GetNextOfKinCommand}.
 */
public class GetNextOfKinCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NextOfKinPredicate firstPredicate =
                new NextOfKinPredicate(Collections.singletonList("first"));
        NextOfKinPredicate secondPredicate =
                new NextOfKinPredicate(Collections.singletonList("second"));

        GetNextOfKinCommand getFirstNextOfKinCommand = new GetNextOfKinCommand(firstPredicate);
        GetNextOfKinCommand getSecondNextOfKinCommand = new GetNextOfKinCommand(secondPredicate);

        // same object -> returns true
        assertTrue(getFirstNextOfKinCommand.equals(getFirstNextOfKinCommand));

        // same values -> returns true
        GetNextOfKinCommand getFirstNextOfKinCommandCopy = new GetNextOfKinCommand(firstPredicate);
        assertTrue(getFirstNextOfKinCommand.equals(getFirstNextOfKinCommandCopy));

        // different types -> returns false
        assertFalse(getFirstNextOfKinCommand.equals(1));

        // null -> returns false
        assertFalse(getFirstNextOfKinCommand.equals(null));

        // different person -> returns false
        assertFalse(getFirstNextOfKinCommand.equals(getSecondNextOfKinCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NextOfKinPredicate predicate = preparePredicate(" ");
        GetNextOfKinCommand command = new GetNextOfKinCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NextOfKinPredicate predicate = preparePredicate("Kurz Elle Kunz");
        GetNextOfKinCommand command = new GetNextOfKinCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NextOfKinPredicate}.
     */
    private NextOfKinPredicate preparePredicate(String userInput) {
        return new NextOfKinPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
