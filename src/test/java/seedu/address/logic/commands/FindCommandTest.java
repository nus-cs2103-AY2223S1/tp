package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FunctionalInterfaces.Changer;
import seedu.address.commons.util.FunctionalInterfaces.Retriever;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Changer<Predicate<Person>> changerStub = ((model, item) -> model.updateFilteredPersonList(item));
    private Retriever<Integer> getSizeStub = ((model) -> model.getFilteredPersonList().size());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate<Person> firstPredicate = new NameContainsKeywordsPredicate<>(
            Collections.singletonList("first"));
        NameContainsKeywordsPredicate<Person> secondPredicate = new NameContainsKeywordsPredicate<>(
            Collections.singletonList("second"));

        FindCommand<Person> findFirstCommand = new FindCommand<>(firstPredicate, changerStub, getSizeStub);
        FindCommand<Person> findSecondCommand = new FindCommand<>(secondPredicate, changerStub, getSizeStub);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand<Person> findFirstCommandCopy = new FindCommand<>(firstPredicate, changerStub, getSizeStub);
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
        String expectedMessage = String.format(FindCommand.SUCCESS_MESSAGE, 0);
        NameContainsKeywordsPredicate<Person> predicate = preparePredicate(" ");
        FindCommand<Person> command = new FindCommand<>(predicate, changerStub, getSizeStub);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(FindCommand.SUCCESS_MESSAGE, 3);
        NameContainsKeywordsPredicate<Person> predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand<Person> command = new FindCommand<>(predicate, changerStub, getSizeStub);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate<Person> preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate<Person>(Arrays.asList(userInput.split("\\s+")));
    }
}
