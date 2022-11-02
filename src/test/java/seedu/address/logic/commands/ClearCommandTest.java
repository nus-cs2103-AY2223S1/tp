package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsHealthContact;

import org.junit.jupiter.api.Test;

import seedu.address.model.HealthContact;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyHealthContact_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyHealthContact_success() {
        Model model = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());
        expectedModel.setHealthContact(new HealthContact());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
