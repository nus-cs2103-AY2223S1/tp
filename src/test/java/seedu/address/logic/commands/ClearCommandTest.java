package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalProfNus;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ProfNus;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyProfNus_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        CommandResult expectedMessage = new CommandResult(ClearCommand.MESSAGE_SUCCESS, false, false, false, false,
                false, false, true, false, false);

        assertCommandSuccess(new ClearCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonEmptyProfNus_success() {
        Model model = new ModelManager(getTypicalProfNus(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalProfNus(), new UserPrefs());
        expectedModel.setProfNus(new ProfNus());
        CommandResult expectedMessage = new CommandResult(ClearCommand.MESSAGE_SUCCESS, false, false, false, false,
                false, false, true, false, false);

        assertCommandSuccess(new ClearCommand(), model, expectedMessage, expectedModel);
    }

}
