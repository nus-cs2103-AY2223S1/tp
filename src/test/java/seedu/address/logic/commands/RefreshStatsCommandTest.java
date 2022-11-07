package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSupplyItems.getTypicalInventory;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RefreshStatsCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalTaskList(), getTypicalInventory());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalTaskList(), getTypicalInventory());
    }

    @Test
    public void execute_listsAreAllUnFiltered_showsEverything() {
        assertCommandSuccess(new RefreshStatsCommand(), model, RefreshStatsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
