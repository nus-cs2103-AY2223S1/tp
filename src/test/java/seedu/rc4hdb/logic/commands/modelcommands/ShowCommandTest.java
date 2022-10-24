package seedu.rc4hdb.logic.commands.modelcommands;

import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.resident.fields.ResidentFields;

public class ShowCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalResidentBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getResidentBook(), new UserPrefs());
    }

    @Test
    public void execute_showCommand_returnsModelWithCorrectHiddenFields() {
        List<String> hiddenFields = new ArrayList<>(ResidentFields.LOWERCASE_FIELDS);
        hiddenFields.removeAll(List.of("name", "phone"));
        expectedModel.setVisibleFields(List.of("name", "phone"));
        expectedModel.setHiddenFields(hiddenFields);
        assertCommandSuccess(new ShowCommand(List.of("name", "phone")), model,
                ShowCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_showCommandWithOneVisibleField_returnsLastColumnReachedMessage() {
        List<String> hiddenFields = new ArrayList<>(ResidentFields.LOWERCASE_FIELDS);
        hiddenFields.removeAll(List.of("name"));
        expectedModel.setVisibleFields(List.of("name"));
        expectedModel.setHiddenFields(hiddenFields);
        assertCommandSuccess(new ShowCommand(List.of("name")), model,
                ShowCommand.MESSAGE_SUCCESS + ShowCommand.LAST_COLUMN_REACHED, expectedModel);
    }

    @Test
    public void execute_showCommandWithNoVisibleFields_throwsCommandException() {
        assertCommandFailure(new ShowCommand(List.of()), model, ShowCommand.CANNOT_SHOW_NO_COLUMNS);
    }
}
