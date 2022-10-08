package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.SortField;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;
    private SortField dummySortField;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        dummySortField = SortField.sortByNoField();
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        String successMessage = String.format(ListCommand.MESSAGE_SUCCESS, dummySortField.getField());
        assertCommandSuccess(new ListCommand(dummySortField), model, successMessage, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        String successMessage = String.format(ListCommand.MESSAGE_SUCCESS, dummySortField.getField());
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(dummySortField), model, successMessage, expectedModel);
    }
}
