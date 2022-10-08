package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AddressContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindAddressCommand}.
 */
public class FindAddressCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        AddressContainsKeywordsPredicate firstPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("first"));
        AddressContainsKeywordsPredicate secondPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("second"));

        FindAddressCommand findFirstAddressCommand = new FindAddressCommand(firstPredicate);
        FindAddressCommand findSecondAddressCommand = new FindAddressCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstAddressCommand, findFirstAddressCommand);

        // same values -> returns true
        FindAddressCommand findFirstCommandCopy = new FindAddressCommand(firstPredicate);
        assertEquals(findFirstAddressCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstAddressCommand);

        // null -> returns false
        assertNotEquals(null, findFirstAddressCommand);

        // different person -> returns false
        assertNotEquals(findFirstAddressCommand, findSecondAddressCommand);
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        AddressContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindAddressCommand command = new FindAddressCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        AddressContainsKeywordsPredicate predicate = preparePredicate("michegan street");
        FindAddressCommand command = new FindAddressCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, ELLE, GEORGE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private AddressContainsKeywordsPredicate preparePredicate(String userInput) {
        return new AddressContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
