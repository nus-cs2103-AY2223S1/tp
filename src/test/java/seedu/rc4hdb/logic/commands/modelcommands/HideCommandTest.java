package seedu.rc4hdb.logic.commands.modelcommands;

import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.UserPrefs;

public class HideCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalResidentBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getResidentBook(), new UserPrefs());
    }

    @Test
    public void execute_hideCommandWithNonEmptyList_hideNonEmptyListInModel() {
        expectedModel.setObservableFields(List.of("room", "gender"));
        assertCommandSuccess(new HideCommand(List.of("room", "gender")), model,
                HideCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_showCommandWithEmptyList_overwritesNonEmptyListInModel() {
        model.setObservableFields(List.of("room", "gender"));

        // already empty during initialisation but added to make test case explicit
        expectedModel.setObservableFields(List.of());

        assertCommandSuccess(new HideCommand(List.of()), model, HideCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_showCommandWithNonEmptyList_overwritesEmptyListInModel() {
        // already empty during initialisation but added to make test case explicit
        model.setObservableFields(List.of());

        expectedModel.setObservableFields(List.of("room", "gender"));

        assertCommandSuccess(new HideCommand(List.of("room", "gender")), model,
                HideCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
