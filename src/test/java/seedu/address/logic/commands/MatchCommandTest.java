package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.testutil.TypicalOrders;
import seedu.address.testutil.TypicalPets;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

public class MatchCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPets.getTypicalPetsAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(TypicalPets.getTypicalPetsAddressBook(), new UserPrefs());
        List<Order> orders = TypicalOrders.getTypicalOrders();

        for (Order order : orders) {
            model.addOrder(order);
            expectedModel.addOrder(order);
        }
    }

    @Test
    public void equals() {
        MatchCommand firstCommand = new MatchCommand(INDEX_FIRST);
        MatchCommand secondCommand = new MatchCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same fields -> returns true
        MatchCommand firstCommandCopy = new MatchCommand(INDEX_FIRST);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> return false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different indexes -> return false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute() {
        //Index is 1
        MatchCommand command = new MatchCommand(INDEX_FIRST);
        CommandResult commandResult = new CommandResult(MatchCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(command, model, commandResult, expectedModel);
    }
}
