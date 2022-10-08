package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

class CountCommandTest {

    @Test
    void execute_countPersons_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(new CountCommand(), model, String.format(CountCommand.MESSAGE_COUNT,
                model.getPersonCount()), expectedModel);
    }
}