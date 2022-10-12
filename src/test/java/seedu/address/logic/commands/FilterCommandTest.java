package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.TagContainsKeywordsPredicate;


public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.filterPersonListWithTag(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /*@Test
    public void execute_singleKeyword_multiplePersonsFound() {
        String expected_Message = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        TagContainsKeywordsPredicate predicate = preparePredicate("owesMoney");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.filterPersonListWithTag(predicate);
        assertCommandSuccess(command, model, expected_Message, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());


    }*/

    private TagContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
