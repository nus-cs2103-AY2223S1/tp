package seedu.address.logic.commands;

import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.BuyerBook;
import seedu.address.model.PropertyBook;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyPropertyBookAndPersonBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyPropertyBookAndPersonBook_success() {
        Model model = new ModelManager(getTypicalPersonsBook(), getTypicalPropertyBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPersonsBook(), getTypicalPropertyBook(), new UserPrefs());
        expectedModel.setBuyerBook(new BuyerBook());
        expectedModel.setPropertyBook(new PropertyBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
