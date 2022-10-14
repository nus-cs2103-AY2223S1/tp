package bookface.logic.commands;

import static bookface.logic.commands.CommandTestUtil.assertCommandSuccess;
import static bookface.testutil.TypicalPersons.getTypicalBookFaceData;

import org.junit.jupiter.api.Test;

import bookface.model.BookFace;
import bookface.model.Model;
import bookface.model.ModelManager;
import bookface.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyBookFace_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyBookFace_success() {
        Model model = new ModelManager(getTypicalBookFaceData(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalBookFaceData(), new UserPrefs());
        expectedModel.setBookFace(new BookFace());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
