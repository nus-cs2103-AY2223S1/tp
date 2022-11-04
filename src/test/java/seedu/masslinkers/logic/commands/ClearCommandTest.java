package seedu.masslinkers.logic.commands;

import static seedu.masslinkers.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.masslinkers.testutil.TypicalStudents.getTypicalMassLinkers;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.model.MassLinkers;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.ModelManager;
import seedu.masslinkers.model.UserPrefs;

//@@author
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
