package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSupplyItems.getTypicalInventory;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ListAllCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalTaskList(), getTypicalInventory());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                getTypicalTaskList(), getTypicalInventory());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAllCommand(), model, ListAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        // Simulate a situation where not all details are shown in the 3 lists.
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListAllCommand(), model, ListAllCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
