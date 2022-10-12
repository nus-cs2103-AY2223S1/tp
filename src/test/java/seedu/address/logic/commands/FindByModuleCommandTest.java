package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.JACKSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ModuleContainsKeywordPredicate;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the model) for {@code FindByModuleCommand}.
 */
public class FindByModuleCommandTest {
    List<Person> testTAList = Arrays.asList(IDA, HOON, JACKSON);

    private AddressBook getTestTAAddressBook() {
        AddressBook testTAAddressBook = new AddressBook();
        for (Person person : testTAList) {
            testTAAddressBook.addPerson(person);
        }
        return testTAAddressBook;
    }

    private Model model = new ModelManager(getTestTAAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTestTAAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ModuleContainsKeywordPredicate firstPredicate =
                new ModuleContainsKeywordPredicate(Collections.singletonList("first"));
        ModuleContainsKeywordPredicate secondPredicate =
                new ModuleContainsKeywordPredicate(Collections.singletonList("second"));

        FindByModuleCommand findByModuleFirstCommand = new FindByModuleCommand(firstPredicate);
        FindByModuleCommand findByModuleSecondCommand = new FindByModuleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findByModuleFirstCommand.equals(findByModuleFirstCommand));

        // same values -> returns true
        FindByModuleCommand findByModuleFirstCommandCopy = new FindByModuleCommand(firstPredicate);
        assertTrue(findByModuleFirstCommand.equals(findByModuleFirstCommandCopy));

        // different types -> returns false
        assertFalse(findByModuleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findByModuleFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findByModuleFirstCommand.equals(findByModuleSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ModuleContainsKeywordPredicate predicate = preparePredicate(" ");
        FindByModuleCommand command = new FindByModuleCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        ModuleContainsKeywordPredicate predicate = preparePredicate("cs2105 cs2103t");
        FindByModuleCommand command = new FindByModuleCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HOON, JACKSON), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code ModuleContainsKeywordsPredicate}.
     */
    private ModuleContainsKeywordPredicate preparePredicate(String userInput) {
        return new ModuleContainsKeywordPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
