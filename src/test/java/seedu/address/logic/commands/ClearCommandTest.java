package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalMassLinkers;

import org.junit.jupiter.api.Test;

import seedu.address.model.MassLinkers;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyMassLinkers_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel,
                false, false, true, false);
    }

    @Test
    public void execute_nonEmptyMassLinkers_success() {
        Model model = new ModelManager(getTypicalMassLinkers(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalMassLinkers(), new UserPrefs());
        expectedModel.setMassLinkers(new MassLinkers());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel,
                false, false, true, false);
    }

}
