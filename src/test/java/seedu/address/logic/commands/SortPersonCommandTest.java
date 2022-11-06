package seedu.address.logic.commands;

import static seedu.address.logic.commands.PersonCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.PersonCommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortPersonCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsSorted_showsFiltered() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        assertCommandSuccess(new SortPersonCommand(SortPersonCommand.Criteria.NAME),
                model, String.format(SortPersonCommand.MESSAGE_SUCCESS, SortPersonCommand.Criteria.NAME.getName()),
                expectedModel);
    }

    @Test
    public void execute_listIsSorted_showsEverything() {
        assertCommandSuccess(new SortPersonCommand(SortPersonCommand.Criteria.NAME),
                model, String.format(SortPersonCommand.MESSAGE_SUCCESS, SortPersonCommand.Criteria.NAME.getName()),
                expectedModel);
    }
}
