package seedu.boba.logic.commands;

import static seedu.boba.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.boba.model.Model;
import seedu.boba.model.ModelManager;
import seedu.boba.model.UserPrefs;
import seedu.boba.model.customer.Customer;
import seedu.boba.testutil.CustomerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalBobaBot(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Customer validCustomer = new CustomerBuilder().build();

        Model expectedModel = new ModelManager(model.getBobaBot(), new UserPrefs());
        expectedModel.addPerson(validCustomer);

        assertCommandSuccess(new AddCommand(validCustomer), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validCustomer), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Customer customerInList = model.getBobaBot().getPersonList().get(0);
        assertCommandFailure(new AddCommand(customerInList), model, AddCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

}
