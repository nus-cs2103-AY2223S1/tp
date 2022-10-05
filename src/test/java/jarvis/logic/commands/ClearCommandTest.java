package jarvis.logic.commands;

import static jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jarvis.testutil.TypicalPersons.getTypicalAddressBook;

import jarvis.logic.commands.student.ClearCommand;
import jarvis.model.StudentBook;
import org.junit.jupiter.api.Test;

import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setStudentBook(new StudentBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
