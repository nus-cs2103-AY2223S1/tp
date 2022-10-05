package tracko.logic.commands;

import static tracko.logic.commands.CommandTestUtil.assertCommandFailure;
import static tracko.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tracko.logic.commands.order.AddOrderCommand;
import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.UserPrefs;
import tracko.model.order.Order;
import tracko.model.person.Person;
import tracko.testutil.OrderBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddOrderCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());
    }

    @Test
    public void execute_newOrder_success() {
        Order validOrder = new OrderBuilder().build();

        Model expectedModel = new ModelManager(model.getTrackO(), new UserPrefs());
        expectedModel.addOrder(validOrder);

        AddOrderCommand command = new AddOrderCommand(validOrder);
        command.setAwaitingInput(false);
        assertCommandSuccess(command, model,
                String.format(AddOrderCommand.MESSAGE_SUCCESS, validOrder), expectedModel);
    }

//    @Test
//    public void execute_duplicatePerson_throwsCommandException() {
//        Person personInList = model.getAddressBook().getPersonList().get(0);
//        assertCommandFailure(new AddOrderCommand(personInList), model, AddOrderCommand.MESSAGE_DUPLICATE_PERSON);
//    }

}
