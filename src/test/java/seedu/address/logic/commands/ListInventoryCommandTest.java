package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showSupplyItemAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SUPPLY_ITEM;
import static seedu.address.testutil.TypicalSupplyItems.getTypicalInventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;

public class ListInventoryCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs(),
                new TaskList(), getTypicalInventory());
        expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                new TaskList(), model.getInventory());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListInventoryCommand(), model, ListInventoryCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showSupplyItemAtIndex(model, INDEX_FIRST_SUPPLY_ITEM);
        assertCommandSuccess(new ListInventoryCommand(), model, ListInventoryCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
