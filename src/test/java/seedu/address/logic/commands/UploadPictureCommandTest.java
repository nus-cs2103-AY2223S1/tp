package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalToDos.getTypicalTaskBookWithToDos;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalStudents;

public class UploadPictureCommandTest {

    private Model model;

    @Test
    public void executeUploadIndexOutOfBounds_failure() {
        AddressBook ab = TypicalStudents.getTypicalAddressBook();
        int size = ab.getStudentList().size();
        model = new ModelManager(ab, getTypicalTaskBookWithToDos(), new UserPrefs());
        UploadPictureCommand command = new UploadPictureCommand(Index.fromOneBased(size + 1));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
}
