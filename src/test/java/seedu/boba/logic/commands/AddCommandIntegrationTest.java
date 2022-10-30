package seedu.boba.logic.commands;

import static seedu.boba.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.UserPrefs;
import seedu.boba.model.customer.Customer;
import seedu.boba.testutil.CustomerBuilder;

/**
 * Contains integration tests (interaction with the BobaBotModel) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private BobaBotModel bobaBotModel;

    @BeforeEach
    public void setUp() {
        bobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Customer validCustomer = new CustomerBuilder().build();

        BobaBotModel expectedBobaBotModel = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
        expectedBobaBotModel.addPerson(validCustomer);

        assertCommandSuccess(new AddCommand(validCustomer), bobaBotModel,
                String.format(AddCommand.MESSAGE_SUCCESS, validCustomer), expectedBobaBotModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Customer customerInList = bobaBotModel.getBobaBot().getPersonList().get(0);
        assertCommandFailure(new AddCommand(customerInList), bobaBotModel, AddCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

}
