package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getUnsortedByNameAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonSortField;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPersonsCommand.
 */
public class ListPersonsCommandTest {

    @Test
    public void constructor_nullSortField_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ListPersonsCommand(null));
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        PersonSortField sortFieldNoField = PersonSortField.sortByNoField();
        String expectedSuccessMessage = String.format(ListPersonsCommand.MESSAGE_SUCCESS,
                sortFieldNoField.getField());

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(new ListPersonsCommand(sortFieldNoField), model, expectedSuccessMessage, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        PersonSortField sortFieldNoField = PersonSortField.sortByNoField();
        String expectedSuccessMessage = String.format(ListPersonsCommand.MESSAGE_SUCCESS,
                sortFieldNoField.getField());

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        showPersonAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListPersonsCommand(sortFieldNoField), model, expectedSuccessMessage, expectedModel);
    }

    @Test
    public void execute_sortByName_showSortedList() {
        PersonSortField sortFieldName = PersonSortField.createSortField("n");

        Model model = new ModelManager(getUnsortedByNameAddressBook(), new UserPrefs());

        // The typical address book is sorted by name
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        CommandResult commandResult = new ListPersonsCommand(sortFieldName).execute(model);
        String expectedSuccessMessage = String.format(ListPersonsCommand.MESSAGE_SUCCESS, sortFieldName.getField());

        assertEquals(expectedSuccessMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }
}
