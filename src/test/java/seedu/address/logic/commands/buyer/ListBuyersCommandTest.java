package seedu.address.logic.commands.buyer;

import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.showBuyerAtIndex;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListBuyersCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getBuyerBook(), model.getPropertyBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListBuyersCommand(), model, ListBuyersCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showBuyerAtIndex(model, INDEX_FIRST_ITEM);
        assertCommandSuccess(new ListBuyersCommand(), model, ListBuyersCommand.MESSAGE_SUCCESS, expectedModel);
    }
}

