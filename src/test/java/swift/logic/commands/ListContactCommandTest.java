package swift.logic.commands;

import static swift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swift.logic.commands.CommandTestUtil.showPersonAtIndex;
import static swift.logic.commands.ListContactCommand.MESSAGE_SUCCESS;
import static swift.testutil.TypicalPersonIndexes.INDEX_FIRST_PERSON;
import static swift.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import swift.model.Model;
import swift.model.ModelManager;
import swift.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListContactCommand.
 */
public class ListContactCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false,
                true, false, false);
        assertCommandSuccess(new ListContactCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false,
                true, false, false);
        assertCommandSuccess(new ListContactCommand(), model, expectedCommandResult, expectedModel);
    }
}
