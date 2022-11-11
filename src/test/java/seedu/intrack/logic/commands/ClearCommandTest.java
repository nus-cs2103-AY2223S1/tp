package seedu.intrack.logic.commands;

import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import org.junit.jupiter.api.Test;

import seedu.intrack.model.InTrack;
import seedu.intrack.model.Model;
import seedu.intrack.model.ModelManager;
import seedu.intrack.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyInTrack_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyInTrack_success() {
        Model model = new ModelManager(getTypicalInTrack(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalInTrack(), new UserPrefs());
        expectedModel.setInTrack(new InTrack());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
