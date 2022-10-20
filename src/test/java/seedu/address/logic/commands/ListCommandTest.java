package seedu.address.logic.commands;

import static seedu.address.logic.commands.Command.MESSAGE_LIST_COMMAND_PREREQUISITE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model modelShowingRecordList;
    private Model modelShowingPatientList;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        modelShowingRecordList = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelShowingPatientList = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(modelShowingRecordList.getAddressBook(), new UserPrefs());

        modelShowingRecordList.setRecordListDisplayed(true);
        expectedModel.setRecordListDisplayed(true);
    }

    @Test
    public void execute_listWhileRecordListNotDisplayed_throwCommandException() {
        assertCommandFailure(new ListCommand(), modelShowingPatientList, MESSAGE_LIST_COMMAND_PREREQUISITE);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), modelShowingRecordList, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(modelShowingRecordList, FIRST_INDEX);
        assertCommandSuccess(new ListCommand(), modelShowingRecordList, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
