package seedu.address.logic.commands;

import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.BuyerCommandTestUtil.showPropertyAtIndex;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ListPropertiesCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getBuyerBook(), model.getPropertyBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPropertiesCommand(), model, ListPropertiesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPropertyAtIndex(model, INDEX_FIRST_ITEM);
        assertCommandSuccess(new ListPropertiesCommand(), model, ListPropertiesCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
