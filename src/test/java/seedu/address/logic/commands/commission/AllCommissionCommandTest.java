package seedu.address.logic.commands.commission;

import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AllCommissionCommand;
import seedu.address.logic.commands.ListCommissionCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class AllCommissionCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listCommissionIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommissionCommand(), model, ListCommissionCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        model.selectCustomer(model.getSortedFilteredCustomerList().get(1));
        assertCommandSuccess(new AllCommissionCommand(), model, AllCommissionCommand.MESSAGE_SUCCESS, expectedModel);
        assertNull(model.getSelectedCustomer().getValue());
    }
}
