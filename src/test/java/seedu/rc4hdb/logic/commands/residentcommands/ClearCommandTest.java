package seedu.rc4hdb.logic.commands.residentcommands;

import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.VenueBook;

public class ClearCommandTest {

    @Test
    public void execute_emptyResidentBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyResidentBook_success() {
        Model model = new ModelManager(getTypicalResidentBook(), new VenueBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalResidentBook(), new VenueBook(), new UserPrefs());
        expectedModel.setResidentBook(new ResidentBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
