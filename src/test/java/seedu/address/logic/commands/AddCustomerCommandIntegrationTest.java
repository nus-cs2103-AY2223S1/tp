package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.customer.Customer;
import seedu.address.testutil.CustomerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCustomerCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());
    }

    @Test
    public void execute_newCustomer_success() {
        Customer validCustomer = new CustomerBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addCustomer(validCustomer);

        assertCommandSuccess(new AddCustomerCommand(validCustomer), model,
                String.format(AddCustomerCommand.MESSAGE_SUCCESS, validCustomer), expectedModel);
    }

    @Test
    public void execute_duplicateCustomer_throwsCommandException() {
        Customer customerInList = model.getAddressBook().getCustomerList().get(0);
        assertCommandFailure(new AddCustomerCommand(customerInList),
                model, null, AddCustomerCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

}
