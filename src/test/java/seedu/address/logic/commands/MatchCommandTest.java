package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.testutil.TypicalOrders;
import seedu.address.testutil.TypicalPets;

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
        MatchCommand command = new MatchCommand(INDEX_FIRST);
        CommandResult commandResult = new CommandResult(MatchCommand.MESSAGE_SUCCESS,
                false,
                false,
                true,
                ListCommand.LIST_PET,
                false,
                null,
                false,
                null,
                null);
        assertCommandSuccess(command, model, commandResult, expectedModel);
    }

    @Test
    public void execute_largeIndex_throwsCommandException() {
        MatchCommand command = new MatchCommand(Index.fromOneBased(Integer.MAX_VALUE));
        assertThrows(CommandException.class, () -> command.execute(model));
    }
}
